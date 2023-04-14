//
//  SearchViewController.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 13/04/23.
//

import UIKit

class SearchViewController: UIViewController {
    let optionsearch = ["hotel","pacote","atividade"]
    lazy var searchBar = UISearchController()
    lazy var searchTypeSelector: UISegmentedControl = {
       let segmentedControl = UISegmentedControl(items: optionsearch)
        segmentedControl.selectedSegmentIndex = 0
        segmentedControl.addTarget(self, action: #selector(changeOption), for: .valueChanged)
        segmentedControl.translatesAutoresizingMaskIntoConstraints = false
        return segmentedControl
    }()
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .white
        view.addSubview(searchTypeSelector)
        let safeArea = view.safeAreaLayoutGuide
        title = "Buscar"
        
        searchBar = UISearchController(searchResultsController: nil)
        self.navigationItem.searchController = searchBar
        searchBar.searchResultsUpdater = self
        
        
        NSLayoutConstraint.activate([
            searchTypeSelector.topAnchor.constraint(equalTo: safeArea.topAnchor),
            searchTypeSelector.leadingAnchor.constraint(equalTo: safeArea.leadingAnchor),
            searchTypeSelector.trailingAnchor.constraint(equalTo: safeArea.trailingAnchor),
            
        ])
        
    }
    @objc func changeOption() {
        print(searchTypeSelector.selectedSegmentIndex)
        switch searchTypeSelector.selectedSegmentIndex {
        case 0:
            print("hotel")
            print(searchBar.searchBar.text)
        case 1:
            print("pacote")
            print(searchBar.searchBar.text)
        default:
            print("atividade")
            print(searchBar.searchBar.text)
        }
    }

}
extension SearchViewController: UISearchResultsUpdating {
    func updateSearchResults(for searchController: UISearchController) {
        guard let resultSearcher = searchController.searchBar.text else {return}
        if resultSearcher.count >= 3 {
            print(resultSearcher)
        }
    }
    
    
}
