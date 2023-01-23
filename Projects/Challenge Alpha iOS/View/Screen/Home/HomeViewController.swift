//
//  HomeViewController.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 16/01/23.
//

import UIKit

class HomeViewController: UIViewController {

    private var requestService = HotelService()
    private var viewModel = ResultViewModel()
    private var filterModel: [String] = []
    private var filter: [String] = []
    var cityName = ""
    
    // MARK: - Properties UI
    lazy var searchBar: UISearchBar = {
        let search = UISearchBar()
        search.translatesAutoresizingMaskIntoConstraints = false
        search.placeholder = "Search..."
        search.showsCancelButton = true
        search.isAccessibilityElement = true
        search.accessibilityLabel = "Search..."
        search.searchBarStyle = .prominent
        search.delegate = self
        return search
    }()

    private lazy var tableView: UITableView = {
        let tableView = UITableView(frame: .zero, style: .plain)
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.delegate = self
        tableView.dataSource = self
        tableView.backgroundColor = .white
        tableView.register(HomeViewCell.self, forCellReuseIdentifier: HomeViewCell.identifier)
        return tableView
    }()
    
    // MARK: - init
    init() {
        super.init(nibName: nil, bundle: nil)
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.setBackground()
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        DispatchQueue.main.async {
            self.tableView.reloadData()
        }
        viewModel.searchHotel(cityName)
        setupView()
    }
}

extension HomeViewController: ViewCodableProtocol {
    func setupView() {
        buildViewHierarchy()
        setupConstraints()
        setupAdditionalConfiguration()
    }

    func buildViewHierarchy() {
        view.addSubview(searchBar)
        view.addSubview(tableView)
    }

    func setupConstraints() {
        NSLayoutConstraint.activate([
            searchBar.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
            searchBar.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            searchBar.trailingAnchor.constraint(equalTo: view.trailingAnchor),

            tableView.topAnchor.constraint(equalTo: searchBar.bottomAnchor),
            tableView.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            tableView.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            tableView.bottomAnchor.constraint(equalTo: view.bottomAnchor)
        ])
    }

    func setupAdditionalConfiguration() {

    }
}

extension HomeViewController: UISearchBarDelegate {

    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        filter = searchText.isEmpty ? filterModel : filterModel.filter { (item: String) -> Bool in
            return item.range(of: searchText, options: .caseInsensitive, range: nil, locale: nil) != nil
        }
        tableView.reloadData()
        }

    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        searchBar.resignFirstResponder()
    }

    func searchBarCancelButtonClicked(_ searchBar: UISearchBar) {
        searchBar.resignFirstResponder()
    }
    
}

extension HomeViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.hotels.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: HomeViewCell.identifier, for: indexPath) as? HomeViewCell else { return UITableViewCell() }
        let result = viewModel.hotels[indexPath.row]
        cell.homeCell.setupCell(hotel: result)
        cell.setupURL(hotel: result)
        return cell
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        let detailsVC = DetailsViewController(result: viewModel.hotels[indexPath.row])
        navigationController?.pushViewController(detailsVC, animated: true)
    }
}

extension HomeViewController: ResultViewModelProtocol {
    func reloadData() {
        tableView.reloadData()
    }
}
