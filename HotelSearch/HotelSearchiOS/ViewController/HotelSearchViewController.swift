//
//  HotelSearchViewController.swift
//  HotelSearchiOS
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import UIKit

import HotelSearch

final public class HotelSearchViewController: UIViewController {
    
    // MARK: - IBOutlets
    
    @IBOutlet private(set) public weak var viewInput: UIView!
    @IBOutlet private(set) public weak var textField: UITextField!
    @IBOutlet private(set) public weak var btnSearch: UIButton!
    @IBOutlet private(set) public weak var tableView: UITableView!
    @IBOutlet private(set) public weak var spinner: UIActivityIndicatorView!
    
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
        self.textField.delegate = self
        self.textField.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
        self.setupTable()
    }
    
    func setupTable() {
        self.tableView.register(UINib(nibName: String(describing: HotelCell.self), bundle: Bundle(for: HotelCell.self)), forCellReuseIdentifier: String(describing: HotelCell.self))
        self.tableView.rowHeight = UITableView.automaticDimension
        self.tableView.estimatedRowHeight = 160
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

// MARK: - Hotel Search View

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

// MARK: - Table View Data Source

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
        let imageData = self.viewModel.imagesData[indexPath.row]
        cell.imageData = imageData
        if imageData == nil {
            self.loadImage(at: indexPath)
        }
        return cell
    }
    
}

// MARK: - Table View Delegate

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

// MARK: - Table View Prefetch

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

// MARK: - Text Field Delegate

extension HotelSearchViewController: UITextFieldDelegate {
    
    public func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        self.viewModel.searchHotel()
        return textField.resignFirstResponder()
    }
    
}
