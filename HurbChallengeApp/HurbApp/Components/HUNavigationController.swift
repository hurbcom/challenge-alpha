//
//  HUNavigationController.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 18/01/23.
//

import UIKit

class HUNavigationController: UINavigationController {

    init() {
        super.init(nibName: nil, bundle: nil)
        setupDefaultsConfigurations()
    }

    override init(rootViewController: UIViewController) {
        super.init(rootViewController: rootViewController)
        setupDefaultsConfigurations()
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func setupDefaultsConfigurations() {

        let standardAppearance = UINavigationBarAppearance()
        standardAppearance.backgroundColor = .white
        navigationBar.standardAppearance = standardAppearance

        let compactAppearance = UINavigationBarAppearance()
        compactAppearance.backgroundColor = .white
        navigationBar.compactAppearance = compactAppearance

        let scrollEdgeAppearance = UINavigationBarAppearance()
        scrollEdgeAppearance.backgroundColor = .white
        navigationBar.scrollEdgeAppearance = scrollEdgeAppearance

    }

}
