//
//  TabbarViewController.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 12/04/23.
//

import UIKit

class TabbarViewController: UITabBarController {
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        navigationController?.navigationBar.isHidden = true
    }
    override func viewDidLoad() {
        super.viewDidLoad()

        configureTabbar()
    }
    
    private func configureTabbar() {
        let home = UINavigationController(rootViewController: HomeViewController())
        home.tabBarItem.title = "Inicio"
        home.tabBarItem.image = UIImage(systemName: "house")
        let search = UINavigationController(rootViewController: SearchViewController())
        search.tabBarItem.title = "buscar"
        search.tabBarItem.image = UIImage(systemName: "magnifyingglass")
        let views = [home, search]
        self.setViewControllers(views, animated: false)
    }
}
