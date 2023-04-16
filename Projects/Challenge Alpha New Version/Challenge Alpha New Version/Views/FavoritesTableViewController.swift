//
//  FavoritesTableViewController.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 15/04/23.
//

import Foundation
import UIKit

class FavoritesTableViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    var favorites: FavoritesListViewModel!
    lazy var tableView = UITableView(frame: .zero, style: .insetGrouped)

    override func viewWillAppear(_ animated: Bool) {
        loadItems()
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .white
        view.addSubview(tableView)
        title = "Favoritos"
        configureTableView()
        loadItems()
        setupCOnstraints()
        
    }
    
    func loadItems(){
        favorites = FavoritesListViewModel(favorites: CoreDataManager.Manager.getItemsFromCoreData())
        DispatchQueue.main.async { [self] in
            self.tableView.reloadData()
            
            print("items no CD: \(favorites!.favorites.count)")
        }
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

    func setupCOnstraints() {
        let safeArea = view.safeAreaLayoutGuide
        NSLayoutConstraint.activate([
        
        self.tableView.topAnchor.constraint(equalTo: safeArea.topAnchor),
        self.tableView.leadingAnchor.constraint(equalTo: safeArea.leadingAnchor),
        self.tableView.trailingAnchor.constraint(equalTo: safeArea.trailingAnchor),
        self.tableView.bottomAnchor.constraint(equalTo: safeArea.bottomAnchor),
        ])
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.favorites?.numberOfRowsInSection(section) ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: CardTableViewCell.cellID, for: indexPath) as! CardTableViewCell
        let data =  self.favorites.resultAt(index: indexPath.row)
        
            cell.displayFavorite(fav: data)
    return cell
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        tableView.beginUpdates()
        print(indexPath.row)
        CoreDataManager.Manager.deleteItem(onArray: self.favorites.favorites, at: indexPath.row)
        tableView.deleteRows(at: [indexPath], with: .fade)
        CoreDataManager.Manager.saveOnCoreData()
        loadItems()
        tableView.endUpdates()
    }
    
}
