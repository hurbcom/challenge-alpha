//
//  HotelSearchViewController.swift
//  HotelSearchiOS
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import UIKit

import HotelSearch

public protocol ImageDataLoaderTask {
    func cancel()
}

public protocol ImageDataLoader {
    typealias Result = Swift.Result<Data, Error>
    
    func loadImageData(from url: URL, completion: @escaping (Result) -> Void) -> ImageDataLoaderTask
}

final public class RemoteImageDataLoader: ImageDataLoader {
    private let client: HTTPClient
    
    public init(client: HTTPClient = URLSessionHTTPClient(session: .shared)) {
        self.client = client
    }
    
    public enum Error: Swift.Error {
        case connectivity
        case invalidData
    }
    
    private final class HTTPClientTaskWrapper: ImageDataLoaderTask {
        private var completion: ((ImageDataLoader.Result) -> Void)?
        
        var wrapped: HTTPClientTask?
        
        init(_ completion: @escaping (ImageDataLoader.Result) -> Void) {
            self.completion = completion
        }
        
        func complete(with result: ImageDataLoader.Result) {
            completion?(result)
        }
        
        func cancel() {
            preventFurtherCompletions()
            wrapped?.cancel()
        }
        
        private func preventFurtherCompletions() {
            completion = nil
        }
    }
    
    public func loadImageData(from url: URL, completion: @escaping (ImageDataLoader.Result) -> Void) -> ImageDataLoaderTask {
        let task = HTTPClientTaskWrapper(completion)
        task.wrapped = client.get(from: url) { [weak self] result in
            guard self != nil else { return }
            
            task.complete(with: result
                .mapError { _ in Error.connectivity }
                .flatMap { (data, response) in
                    let isValidResponse = response.statusCode == 200 && !data.isEmpty
                    return isValidResponse ? .success(data) : .failure(Error.invalidData)
                })
        }
        return task
    }
    
}

final public class HotelSearchViewModel {
    
    weak public var hotelSearchView: HotelSearchView?
    
    var text: String = ""
    
    public var imagesData = [Int: Data]()
    
    private var hotels = [Hotel]()
    private var imageLoadTasks = [Int: ImageDataLoaderTask]()
    
    private let hotelSearcher: HotelSearcher
    private let imageDataLoader: ImageDataLoader
    private let searchSuffix = "&page="
    private var currentPage = 1
    
    public init(hotelSearcher: HotelSearcher, imageDataLoader: ImageDataLoader) {
        self.hotelSearcher = hotelSearcher
        self.imageDataLoader = imageDataLoader
    }
    
    public func searchHotel() {
        let searchText = self.text + self.searchSuffix + String(describing: self.currentPage)
        self.hotelSearchView?.displayLoading(true)
        self.hotelSearcher.searchHotel(with: searchText.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) ?? searchText) { [weak self] result in
            DispatchQueue.main.async {
                guard let self = self else { return }
                switch result {
                case let .success(hotels):
                    self.hotels = hotels
                    self.hotelSearchView?.display(hotels.map { HotelViewModel(hotel: $0)})
                case let .failure(error):
                    self.hotelSearchView?.displayError(error.localizedDescription)
                }
                self.hotelSearchView?.displayLoading(false)
            }
        }
    }
    
    public func loadImage(at index: Int) {
        guard self.hotels.count > index else { return }
        let hotel = self.hotels[index]
        guard let url = hotel.image ?? hotel.gallery?.first?.url else { return }
        self.imageLoadTasks[index] = self.imageDataLoader.loadImageData(from: url) { [weak self] result in
            DispatchQueue.main.async {
                guard let self = self else { return }
                switch result {
                case let .success(data):
                    self.imagesData[index] = data
                    self.hotelSearchView?.displayImageData(data, for: index)
                case let .failure(error):
                    print(error)
                }
            }
        }
    }
    
    public func cancelImageLoad(at index: Int) {
        self.imageLoadTasks[index]?.cancel()
        self.imageLoadTasks[index] = nil
    }
    
}

final public class HotelSearchViewController: UIViewController {
    
    // MARK: - IBOutlets
    
    @IBOutlet weak var viewInput: UIView!
    @IBOutlet weak var textField: UITextField!
    @IBOutlet weak var btnSearch: UIButton!
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var spinner: UIActivityIndicatorView!
    
    // MARK: - IBActions
    
    @IBAction func btnSearchAction(_ sender: UIButton) {
        self.viewModel.searchHotel()
    }
    
    // MARK: - Properties
    
    private let viewModel: HotelSearchViewModel
    private var hotels = [HotelViewModel]() {
        didSet {
            self.tableView.reloadData()
        }
    }
    
    // MARK: - Life Cycle
    
    public init(viewModel: HotelSearchViewModel) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: Bundle(for: HotelSearchViewController.self))
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    public override func viewDidLoad() {
        super.viewDidLoad()
        self.setupUI()
    }

}

private extension HotelSearchViewController {
    
    // MARK: - Setup
    
    func setupUI() {
        self.viewInput.layer.cornerRadius = 4
        
        self.textField.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        
        self.tableView.register(UINib(nibName: String(describing: HotelCell.self), bundle: Bundle(for: HotelCell.self)), forCellReuseIdentifier: String(describing: HotelCell.self))
        self.tableView.dataSource = self
        self.tableView.prefetchDataSource = self
        self.tableView.delegate = self
        self.tableView.separatorStyle = .none
    }
    
    // MARK: - Objc methods
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        self.viewModel.text = textField.text ?? ""
    }
    
}

extension HotelSearchViewController: HotelSearchView {
    
    public func display(_ hotels: [HotelViewModel]) {
        self.hotels = hotels
    }
    
    public func displayError(_ error: String) {
        print(error)
    }
    
    public func displayLoading(_ isLoading: Bool) {
        isLoading ? self.spinner.startAnimating() : self.spinner.stopAnimating()
        self.tableView.isHidden = isLoading
    }
    
    public func displayImageData(_ data: Data, for index: Int) {
        if let cell = self.tableView.cellForRow(at: IndexPath(row: index, section: 0)) as? HotelCell, self.tableView.visibleCells.contains(cell) {
            cell.imvBackground.image = UIImage(data: data)
        }
    }
    
}

extension HotelSearchViewController: UITableViewDataSource {
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.hotels.count
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: String(describing: HotelCell.self), for: indexPath) as! HotelCell
        cell.viewModel = self.hotels[indexPath.row]
        let data = self.viewModel.imagesData[indexPath.row]
        if let data = data {
            cell.imvBackground.image = UIImage(data: data)
        } else {
            cell.imvBackground.image = nil
            self.viewModel.loadImage(at: indexPath.row)
        }
        return cell
    }
    
}

extension HotelSearchViewController: UITableViewDelegate {
    
    public func tableView(_ tableView: UITableView, didEndDisplaying cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        self.viewModel.cancelImageLoad(at: indexPath.row)
    }
    
}

extension HotelSearchViewController: UITableViewDataSourcePrefetching {
    
    public func tableView(_ tableView: UITableView, prefetchRowsAt indexPaths: [IndexPath]) {
        indexPaths.forEach {
            self.viewModel.loadImage(at: $0.row)
        }
    }
    
    public func tableView(_ tableView: UITableView, cancelPrefetchingForRowsAt indexPaths: [IndexPath]) {
        indexPaths.forEach {
            self.viewModel.cancelImageLoad(at: $0.row)
        }
    }
    
}
