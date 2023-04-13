//
//  HomeViewController.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 12/04/23.
//

import UIKit

class HomeViewController: UIViewController {
    lazy var tableView:UITableView = {
        let tableView = UITableView(frame: .zero, style: .insetGrouped)
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.isScrollEnabled = true
        tableView.separatorStyle = .singleLine
        tableView.delegate = self
        tableView.dataSource = self
        tableView.register(CardInfoTableViewCell.self, forCellReuseIdentifier: CardInfoTableViewCell.identifierCell)
        return tableView
    }()
    override func viewDidLoad() {
        super.viewDidLoad()
        self.title = "HOME"
        view.backgroundColor = .white
        navigationController?.navigationBar.prefersLargeTitles = true
        self.navigationItem.largeTitleDisplayMode = .always
        view.addSubview(tableView)
        configConstraints()
    }
    // constraints
    private func configConstraints() {
        NSLayoutConstraint.activate([
            self.tableView.topAnchor.constraint(equalTo: self.view.topAnchor),
            self.tableView.leadingAnchor.constraint(equalTo: self.view.leadingAnchor),
            self.tableView.trailingAnchor.constraint(equalTo: self.view.trailingAnchor),
            self.tableView.bottomAnchor.constraint(equalTo: self.view.bottomAnchor)
        ])
    }
}
extension HomeViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 30
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: CardInfoTableViewCell.identifierCell, for: indexPath) as! CardInfoTableViewCell
        cell.setCellData(title: "RODOU AGORA", subtitle: "\(indexPath.row)", imgURL: "")
        return cell
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 130
    }
        
}
