//
//  SearchViewController.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 13/04/23.
//

import UIKit
import HUGraphQL

class SearchViewController: UIViewController {
    
    let optionsearch = ["hotel","pacote"]
    
    var HotelListVM: HotelsInfoListViewModel!
    var packageListVM: PackageInfoListViewModel!
    var searchPerformed: Bool = false
    
    lazy var searchButton = UIButton()
    lazy var activityIndicatorView = UIActivityIndicatorView(style: .large)
    lazy var searchBar = UISearchController()
    lazy var SearchSegmentedControl = UISegmentedControl(items: optionsearch)
    lazy var tableView = UITableView(frame: .zero, style: .insetGrouped)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .white
        view.addSubview(SearchSegmentedControl)
        view.addSubview(tableView)
        view.addSubview(searchButton)
        title = "Buscar"
        setupSegmentedControll()
        setupSearchBar()
        configureTableView()
        configConstraints()
        configSearchButton()
        
    }
    
//    MARK: - setupUI
    private func setupSearchBar() {
        searchBar = UISearchController(searchResultsController: nil)
        self.navigationItem.searchController = searchBar
        searchBar.searchResultsUpdater = self
        searchBar.searchBar.delegate = self
    }
    private func setupSegmentedControll(){
        SearchSegmentedControl.selectedSegmentIndex = 0
        SearchSegmentedControl.addTarget(self, action: #selector(changeOption), for: .valueChanged)
        SearchSegmentedControl.translatesAutoresizingMaskIntoConstraints = false

    }
    private func configSearchButton() {
        searchButton.setTitle("Buscar Hotel", for: .normal)
        searchButton.translatesAutoresizingMaskIntoConstraints = false
        searchButton.backgroundColor = .black
        searchButton.setTitleColor(.white, for: .normal)
        searchButton.layer.cornerRadius = 6
        searchButton.clipsToBounds = true
        searchButton.addTarget(self, action: #selector(searchButtonPressed), for: .touchUpInside)
    }
    private func configureTableView() {
        tableView.delegate = self
        tableView.dataSource = self
        tableView.register(CardTableViewCell.self, forCellReuseIdentifier: CardTableViewCell.cellID)
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.isScrollEnabled = true
        tableView.separatorStyle = .singleLine
        tableView.backgroundColor = .clear
    }
    
    // constraints
    private func configConstraints() {
        let safeArea = view.safeAreaLayoutGuide
        NSLayoutConstraint.activate([
            
            SearchSegmentedControl.topAnchor.constraint(equalTo: safeArea.topAnchor),
            SearchSegmentedControl.leadingAnchor.constraint(equalTo: safeArea.leadingAnchor),
            SearchSegmentedControl.trailingAnchor.constraint(equalTo: safeArea.trailingAnchor),
            
            searchButton.topAnchor.constraint(equalTo: SearchSegmentedControl.bottomAnchor, constant: 16),
            searchButton.leadingAnchor.constraint(equalTo: safeArea.leadingAnchor, constant: 32),
            searchButton.trailingAnchor.constraint(equalTo: safeArea.trailingAnchor, constant: -32),
    
            self.tableView.topAnchor.constraint(equalTo: self.searchButton.bottomAnchor),
            self.tableView.leadingAnchor.constraint(equalTo: safeArea.leadingAnchor),
            self.tableView.trailingAnchor.constraint(equalTo: safeArea.trailingAnchor),
            self.tableView.bottomAnchor.constraint(equalTo: safeArea.bottomAnchor)
        ])
    }
    
    
    
    //    MARK: - Methods

    private func searchPerformer(city:String, optionSelected:Int){
        activityIndicator(isAnimating: true)
        self.tableView.reloadData()
        switch SearchSegmentedControl.selectedSegmentIndex {
        case 0:
            
            Services.servicesNetwork.performSearchFetch(query: city) { result in
                if !result.isEmpty {
                    self.HotelListVM = HotelsInfoListViewModel(resultList: result)
                    DispatchQueue.main.async {
                        self.tableView.reloadData()
                        self.activityIndicator(isAnimating: false)
                        self.searchPerformed = true
                    }
                } else {
                    self.getAlertNotification(message: "Erro ao buscar dados")
                    self.activityIndicator(isAnimating: false)
                    self.searchPerformed = true
                }
            }
        default:
            Services.servicesNetwork.performPackageSearch(query: city) { result in
                if !result.isEmpty {
                    self.packageListVM = PackageInfoListViewModel(resultList: result)
                    DispatchQueue.main.async {
                        self.tableView.reloadData()
                        self.activityIndicator(isAnimating: false)
                        self.searchPerformed = true
                    }
                }else {
                    self.getAlertNotification(message: "Erro ao buscar dados")
                    self.activityIndicator(isAnimating: false)
                    self.searchPerformed = true
                }
            }
        }
        self.searchButton.isHidden = true
        
    }


    private func activityIndicator(isAnimating:Bool) {
        let container = UIView()
        container.frame = CGRect(x: 0, y: 0, width: 0, height: 0)
        activityIndicatorView.center = self.view.center
        view.addSubview(activityIndicatorView)
        activityIndicatorView.color = .black
        self.view.addSubview(container)
        isAnimating ? activityIndicatorView.startAnimating() : activityIndicatorView.stopAnimating()
        
        
    }
    
//    MARK: - @objc methods
    @objc private func searchButtonPressed() {
        searchBar.dismiss(animated: true)
        let text = searchBar.searchBar.text ?? ""
        if !text.isEmpty {
            searchPerformer(city:text, optionSelected:SearchSegmentedControl.selectedSegmentIndex)
        } else {
            getAlertNotification(message: "Digite uma cidade na barra de busca" )
            print("Digite uma cidade na barra de busca")
        }
    }
    
    @objc private func changeOption() {
        searchButtonPressed()
    }
}

//MARK: - Extension UISearchResultsUpdating & UISearchBarDelegate
extension SearchViewController: UISearchResultsUpdating, UISearchBarDelegate {
    func updateSearchResults(for searchController: UISearchController) {
        searchButton.isHidden = false
        guard let resultSearcher = searchController.searchBar.text else {return}
        if resultSearcher.count >= 3 {
            print(resultSearcher)
        }
    }
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
           searchButtonPressed()
        }
}

//MARK: - Extension UITableViewDelegate, UITableViewDataSource
extension SearchViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
      if  SearchSegmentedControl.selectedSegmentIndex == 0 {
            return self.HotelListVM?.numberOfRowsInSection(section) ?? 0
        } else {
            return self.packageListVM?.numberOfRowsInSection(section) ?? 0
        }
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: CardTableViewCell.cellID, for: indexPath) as! CardTableViewCell
        if SearchSegmentedControl.selectedSegmentIndex == 0 {
        if let data = self.HotelListVM?.resultAt(index: indexPath.row) {
            cell.setCellData(selectedHotel: data)

        }} else { if let data = self.packageListVM?.resultAt(index: indexPath.row) {
            cell.setCellDataPackage(package: data)
        }}

        return cell
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return SearchSegmentedControl.selectedSegmentIndex == 0 ? 250 : 300
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        if SearchSegmentedControl.selectedSegmentIndex == 0 {
            let hotel = HotelListVM.resultAt(index: indexPath.row)
            let vc = DetailsViewController()
            vc.hotel = hotel
            tableView.deselectRow(at: indexPath, animated: true)
            self.navigationController?.pushViewController(vc, animated: true)
        } else {
            let package = packageListVM.resultAt(index: indexPath.row)
            let vc = DetailsViewController()
            vc.package = package
            tableView.deselectRow(at: indexPath, animated: true)
            self.navigationController?.pushViewController(vc, animated: true)
        }
    }
        
}

extension SearchViewController {
    func getAlertNotification(message:String) {
        let alert = UIAlertController(title: message, message:"Tente novamente", preferredStyle: .alert)
        let alertDismissBtn = UIAlertAction(title: "fechar", style: .default)
        alert.addAction(alertDismissBtn)
        self.present(alert, animated: true)
    }
}

