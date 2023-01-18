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
        searchTextField.textPadding = UIEdgeInsets(top: 8.0, left: 8.0, bottom: 8.0, right: 8.0)
        return searchTextField
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
        view.backgroundColor = .white
    }

    private func setupLayoutConstraints() {
        view.addSubview(searchTextField)
        NSLayoutConstraint.activate([
            searchTextField.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 8.0),
            searchTextField.leadingAnchor.constraint(equalTo: view.safeAreaLayoutGuide.leadingAnchor, constant: 8.0),
            searchTextField.centerXAnchor.constraint(equalTo: view.centerXAnchor),
        ])
    }

}

extension SearchViewController {

    @objc private func searchQueryButtonTouch() {
        viewModel.loadData()
    }

}
