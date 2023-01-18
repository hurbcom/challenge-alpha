//
//  HomeViewController.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 16/01/23.
//

import UIKit

class HomeViewController: UIViewController {
    
    private let viewModel = ResponseHotel?.self
    
    // MARK: - Private Properties UI
    lazy var searchBar: UISearchBar = {
        let search = UISearchBar()
        search.placeholder = "Search Cities"
        search.searchBarStyle = .prominent
        search.delegate = self
        return search
    }()
    
    private lazy var tableView: UITableView = {
        let tableView = UITableView(frame: .zero, style: .plain)
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.delegate = self
        tableView.dataSource = self
        tableView.backgroundColor = .clear
        tableView.register(HomeViewCell.self, forCellReuseIdentifier: HomeViewCell.identifier)
        return tableView
    }()
    
    private lazy var searchButton: UIButton = {
        let searchButton = UIButton()
        if let image = UIImage(named: "search-img") {
            searchButton.setImage(image, for: .normal)
        }
//        searchButton.addTarget(self, action:#selector(self.), for: .touchUpInside)
        searchButton.translatesAutoresizingMaskIntoConstraints = false
        return searchButton
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.setBackground()
        self.filter = self.cursos
        setupView()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
    }
    
    var cursos = ["Swift", "Kotlin", "JavaScript", "Flutter", "React Native", "Go"]
    var filter: [String] = []
}

extension HomeViewController: ViewCodableProtocol {
    func setupView() {
        buildViewHierarchy()
        setupConstraints()
        setupAdditionalConfiguration()
    }

    func buildViewHierarchy() {
        view.addSubview(searchBar)
        searchBar.addSubview(searchButton)
        view.addSubview(tableView)
    }

    func setupConstraints() {
        NSLayoutConstraint.activate([
            searchBar.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor),
            searchBar.leadingAnchor.constraint(equalTo: view.leadingAnchor),
            searchBar.trailingAnchor.constraint(equalTo: view.trailingAnchor),
            
            searchButton.topAnchor.constraint(equalTo: view.topAnchor, constant: 111),
            searchButton.trailingAnchor.constraint(equalTo: searchBar.trailingAnchor, constant: -32),
            
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
        self.filter = []
        if searchText.isEmpty {
            self.filter = self.cursos
        } else {
            for value in cursos {
                if value.uppercased().contains(searchText.uppercased()) {
                    self.filter.append(value)
                }
            }
        }
        self.tableView.reloadData()
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        searchBar.resignFirstResponder()
    }
}

extension HomeViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.filter.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: HomeViewCell.identifier, for: indexPath) as? HomeViewCell else { return UITableViewCell() }
        cell.textLabel?.text = filter[indexPath.row]
        
        return cell
    }
}


