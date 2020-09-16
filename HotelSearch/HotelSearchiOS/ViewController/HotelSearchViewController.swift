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
    
    private var hotels = [[Hotel]]()
    
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
        self.cleanPreviousHotelsStates()
        self.hotelSearchView?.displayLoading(true)
        self.hotelSearcher.searchHotel(with: searchText.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) ?? searchText) { [weak self] result in
            DispatchQueue.main.async {
                guard let self = self else { return }
                switch result {
                case let .success(hotels):
                    self.mapHotels(hotels)
                case let .failure(error):
                    self.hotelSearchView?.displayError(error.localizedDescription)
                }
                self.hotelSearchView?.displayLoading(false)
            }
        }
    }
    
    private func mapHotels(_ hotels: [Hotel]) {
        let sortedHotels = hotels.sorted(by: { ($0.stars ?? -1) > ($1.stars ?? -1)})
        let mappedHotels = sortedHotels.map { hotel in return sortedHotels.filter { $0.stars == hotel.stars} }
        let mappedHotelsWithoutDuplications = NSOrderedSet(array: mappedHotels).array as! [[Hotel]]
        self.hotels = mappedHotelsWithoutDuplications
        let viewModel = self.hotels.compactMap { $0.compactMap { HotelViewModel(hotel: $0) }}
        self.hotelSearchView?.display(viewModel)
    }
    
    private func cleanPreviousHotelsStates() {
        self.hotels.removeAll()
        self.imagesData.removeAll()
        self.hotelSearchView?.display([])
    }
    
    public func loadImage(at index: Int, section: Int) -> ImageDataLoaderTask? {
        guard self.hotels.count > index else { return nil }
        guard self.imagesData[index] == nil else { return nil }
        let hotel = self.hotels[section][index]
        guard let url = hotel.image ?? hotel.gallery?.first?.url else { return nil }
        self.hotelSearchView?.displayImageLoading(true, for: index, section: section)
        return self.imageDataLoader.loadImageData(from: url) { [weak self] result in
            DispatchQueue.main.async {
                guard let self = self else { return }
                switch result {
                case let .success(data):
                    self.imagesData[index] = data
                    self.hotelSearchView?.displayImageData(data, for: index, section: section)
                case let .failure(error):
                    print(error)
                }
                self.hotelSearchView?.displayImageLoading(false, for: index, section: section)
            }
        }
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
    
    private var groupedHotels = [[HotelViewModel]]() {
        didSet {
            self.tableView.reloadData()
        }
    }
    private let viewModel: HotelSearchViewModel
    private var hotels = [[HotelViewModel]]() {
        didSet {
            self.tableView.reloadData()
        }
    }
    private var imageLoadTasks = [IndexPath: ImageDataLoaderTask]()
    
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
        self.tableView.rowHeight = UITableView.automaticDimension
        self.tableView.estimatedRowHeight = 200
        self.tableView.dataSource = self
        self.tableView.prefetchDataSource = self
        self.tableView.delegate = self
        self.tableView.separatorStyle = .none
    }
    
    // MARK: - Objc methods
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        self.viewModel.text = textField.text ?? ""
    }
    
    func loadImage(at indexPath: IndexPath) {
        self.imageLoadTasks[indexPath] = self.viewModel.loadImage(at: indexPath.row, section: indexPath.section)
    }
    
    func cancelImageLoad(at indexPath: IndexPath) {
        self.imageLoadTasks[indexPath]?.cancel()
        self.imageLoadTasks[indexPath] = nil
    }
    
}

extension HotelSearchViewController: HotelSearchView {
    
    public func display(_ hotels: [[HotelViewModel]]) {
        self.hotels = hotels
    }
    
    public func displayError(_ error: String) {
        print(error)
    }
    
    public func displayLoading(_ isLoading: Bool) {
        isLoading ? self.spinner.startAnimating() : self.spinner.stopAnimating()
        self.tableView.isHidden = isLoading
    }
    
    public func displayImageData(_ data: Data, for index: Int, section: Int) {
        self.visibleHotelCell(for: IndexPath(row: index, section: section))?.imvBackground.setImageAnimated(UIImage(data: data))
    }
    
    public func displayImageLoading(_ isLoading: Bool, for index: Int, section: Int) {
        self.visibleHotelCell(for: IndexPath(row: index, section: section))?.imageContainer.isShimmering = isLoading
    }
    
    private func visibleHotelCell(for indexPath: IndexPath) -> HotelCell? {
        guard self.tableView.indexPathsForVisibleRows?.contains(indexPath) == true else { return nil }
        if let cell = self.tableView.cellForRow(at: indexPath) as? HotelCell, self.tableView.visibleCells.contains(cell) {
            return cell
        }
        return nil
    }
    
}

extension HotelSearchViewController: UITableViewDataSource {
    
    public func numberOfSections(in tableView: UITableView) -> Int {
        return self.hotels.count
    }
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.hotels[section].count
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: String(describing: HotelCell.self), for: indexPath) as! HotelCell
        cell.viewModel = self.hotels[indexPath.section][indexPath.row]
        let data = self.viewModel.imagesData[indexPath.row]
        if let data = data {
            cell.imvBackground.setImageAnimated(UIImage(data: data))
            cell.imageContainer.isShimmering = false
        } else {
            cell.imvBackground.setImageAnimated(nil)
            cell.imageContainer.isShimmering = true
            self.loadImage(at: indexPath)
        }
        return cell
    }
    
}

extension HotelSearchViewController: UITableViewDelegate {
    
    public func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 36
    }
    
    public func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        return HeaderView(stars: self.hotels[section].first?.stars)
    }
    
    public func tableView(_ tableView: UITableView, didEndDisplaying cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        self.cancelImageLoad(at: indexPath)
    }
    
}

extension HotelSearchViewController: UITableViewDataSourcePrefetching {
    
    public func tableView(_ tableView: UITableView, prefetchRowsAt indexPaths: [IndexPath]) {
        indexPaths.forEach {
            self.loadImage(at: $0)
        }
    }
    
    public func tableView(_ tableView: UITableView, cancelPrefetchingForRowsAt indexPaths: [IndexPath]) {
        indexPaths.forEach {
            self.cancelImageLoad(at: $0)
        }
    }
    
}
