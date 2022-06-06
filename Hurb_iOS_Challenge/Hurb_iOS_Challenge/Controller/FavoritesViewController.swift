//
//  FavoritesViewController.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 22/05/22.
//

import UIKit

class FavoritesViewController: UIViewController {
    
    // MARK: - Properties
    private let comingSoonFavoritesLabel: UILabel = {
        let label = UILabel()
        label.text = "Em breve, a tela de `Favoritos` estará disponível.\n✌︎"
        label.textAlignment = .center
        label.font = UIFont.systemFont(ofSize: 16, weight: .bold)
        label.textColor = .darkGray
        label.tintColor = .darkGray
        label.numberOfLines = 3
        return label
    }()
    
    // MARK: - Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        setupView()
    }
}

// MARK: - CodeView
extension FavoritesViewController: CodeView {
    func buildViewHierarchy() {
        view.addSubview(comingSoonFavoritesLabel)
    }
    
    func setupConstraints() {
        comingSoonFavoritesLabel.anchor(leading: view.leadingAnchor, trailling: view.trailingAnchor)
        comingSoonFavoritesLabel.centerY(inView: view)
    }
    
    func setupAdditionalConfiguration() {
        /// Configure View.
        view.backgroundColor = .lightGray
        
        /// Configure NavigationBar.
        title = "Favoritos"
        navigationController?.navigationBar.prefersLargeTitles = true
        navigationController?.navigationItem.largeTitleDisplayMode = .always
    }
}
