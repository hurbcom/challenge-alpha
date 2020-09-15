//
//  HotelSearchViewController.swift
//  HotelSearchiOS
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import UIKit

import HotelSearch

final public class HotelSearchViewModel {
    
    weak public var hotelSearchView: HotelSearchView?
    
    var text: String = ""
    
    private let hotelSearcher: HotelSearcher
    private let searchSuffix = "&page="
    private var currentPage = 1
    
    public init(hotelSearcher: HotelSearcher) {
        self.hotelSearcher = hotelSearcher
    }
    
    func searchHotel() {
        let searchText = self.text + self.searchSuffix + String(describing: self.currentPage)
        self.hotelSearchView?.displayLoading(true)
        self.hotelSearcher.searchHotel(with: searchText.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) ?? searchText) { [weak self] result in
            DispatchQueue.main.async {
                guard let self = self else { return }
                switch result {
                case let .success(hotels):
                    self.hotelSearchView?.display(hotels.map { HotelViewModel(hotel: $0)})
                case let .failure(error):
                    self.hotelSearchView?.displayError(error.localizedDescription)
                }
                self.hotelSearchView?.displayLoading(false)
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
    
}

extension HotelSearchViewController: UITableViewDataSource {
    
    public func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.hotels.count
    }
    
    public func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: String(describing: HotelCell.self), for: indexPath) as! HotelCell
        cell.viewModel = self.hotels[indexPath.row]
        return cell
    }
    
}
