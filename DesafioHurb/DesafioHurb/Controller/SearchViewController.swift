//
//  SearchViewController.swift
//  DesafioHurb
//
//  Created by Filipo Negrao on 25/10/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import UIKit

class SearchViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, UISearchBarDelegate, UIScrollViewDelegate {

    @IBOutlet weak var label: UILabel!
    @IBOutlet weak var searchBar: UISearchBar!
    @IBOutlet weak var tableView: UITableView!
    
    private weak var controller: HotelsViewController!
    
    private var results = [String]()
    
    init(controller: HotelsViewController) {
        super.init(nibName: "SearchViewController", bundle: nil)
        self.controller = controller
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.view.backgroundColor = UIColor.white
        self.tableView.backgroundColor = UIColor.white
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.register(UITableViewCell.self, forCellReuseIdentifier: "Cell")
        
        self.searchBar.backgroundColor = UIColor.white
        self.searchBar.searchBarStyle = .minimal
        self.searchBar.tintColor = UIColor.gray
        self.searchBar.barTintColor = UIColor.gray
        self.searchBar.delegate = self
        var textField = self.searchBar.value(forKey: "searchField") as? UITextField
        textField?.textColor = UIColor.gray
    }
    
    override func viewDidAppear(_ animated: Bool) {
        self.searchBar.becomeFirstResponder()
    }
    
    // MARK: Table view data source and delegate
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.results.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        let result = self.results[indexPath.row]
        cell.textLabel?.text = result
        cell.backgroundColor = UIColor.white
        cell.textLabel?.textColor = UIColor.black
        cell.selectionStyle = .none
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        self.tableView.deselectRow(at: indexPath, animated: true)
        let result = self.results[indexPath.row]
        self.selectText(text: result)
    }
    
    func selectText(text: String ) {
        self.dismiss(animated: true) {
            self.controller.searchTerm = text
            self.controller.searchController.searchBar.text = text
            self.controller.loadData()
        }
    }
    
    // MARK: Search Bar Delegate
    
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        print(searchText)
        self.loadSuggestions(text: searchText)
    }

    
    func loadSuggestions(text: String ) {
        ApplicationService.sharedInstance.getSuggestionData(searchText: text) { (results: [String], error: String?) in
            if let error = error {
                return
            }
            self.results = results
            self.tableView.reloadData()
        }
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        if let text = self.searchBar.text {
            self.selectText(text: text)
        } else {
            self.dismiss(animated: true) {
                
            }
        }
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        self.view.endEditing(true)
    }
    

}
