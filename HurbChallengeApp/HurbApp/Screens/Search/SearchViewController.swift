//
//  SearchViewController.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 17/01/23.
//

import UIKit

class SearchViewController: UIViewController {

    private let viewModel: SearchViewModelProtocol

    private let searchTextField: HUTextField = {
        let searchTextField = HUTextField()
        searchTextField.translatesAutoresizingMaskIntoConstraints = false
        searchTextField.placeholder = "Pesquise por hotel ou destino"
        searchTextField.clearButtonMode = .whileEditing
        searchTextField.leftViewMode = .always
        searchTextField.leftView = UIImageView(image: .searchIcon)
        searchTextField.textPadding = UIEdgeInsets(top: 8.0, left: 4.0, bottom: 8.0, right: 8.0)
        searchTextField.returnKeyType = .done
        return searchTextField
    }()

    private let tableView: UITableView = {
        let tableView = UITableView()
        tableView.backgroundColor = .clear
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.register(SearchItemTableCell.self, forCellReuseIdentifier: SearchItemTableCell.reuseIdentifier)
        tableView.separatorStyle = .none
        return tableView
    }()

    init(viewModel: SearchViewModelProtocol) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        setupView()
        setupLayoutConstraints()
    }

    private func setupView() {
        view.backgroundColor = .background
        searchTextField.delegate = self
        tableView.dataSource = self
    }

    private func setupLayoutConstraints() {

        view.addSubview(searchTextField)
        view.addSubview(tableView)

        NSLayoutConstraint.activate([

            searchTextField.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 8.0),
            searchTextField.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor, constant: 8.0),
            searchTextField.centerXAnchor.constraint(equalTo: view.centerXAnchor),

            tableView.topAnchor.constraint(equalTo: searchTextField.bottomAnchor, constant: 8.0),
            tableView.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor, constant: 8.0),
            tableView.centerXAnchor.constraint(equalTo: view.centerXAnchor),
            tableView.bottomAnchor.constraint(equalTo: view.safeAreaLayoutGuide.bottomAnchor, constant: 0.0)
        ])
    }

}

extension SearchViewController: SearchViewModelDelegate {

    func didSearchFailed(error: AlertMessage) {
        let alertController = UIAlertController(
            title: error.title,
            message: error.message,
            preferredStyle: .alert
        )
        present(alertController, animated: true)
    }

    func didUpdateSearchList() {
        tableView.reloadData()
    }

}

extension SearchViewController: UITextFieldDelegate {

    func textFieldDidEndEditing(_ textField: UITextField) {
        viewModel.updateTextValue(textField.text)
        viewModel.searchTextValue()
    }

    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }

}

extension SearchViewController: UITableViewDataSource {

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.searchResults.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

        if let cell = tableView.dequeueReusableCell(withIdentifier: SearchItemTableCell.reuseIdentifier, for: indexPath) as? SearchItemTableCell {
            cell.updateContentData(model: viewModel.searchResults[indexPath.row])
            return cell
        }

        return UITableViewCell()
    }

}
