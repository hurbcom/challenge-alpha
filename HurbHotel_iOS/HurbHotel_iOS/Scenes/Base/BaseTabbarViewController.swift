//
//  BaseTabbarViewController.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 21/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

class BaseTabbarViewController: UITabBarController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let searchVC = buildTabbarItem(SearchViewController().instantiate(), title: "Pesquisar", image: "icSearch")
        let otherVC = buildTabbarItem(PackagesViewController().instantiate(), title: "Outro", image: "icHighlight")
        
        viewControllers = [
            searchVC,
            otherVC
        ]
        
        selectedIndex = 0
    }
    
    private func buildTabbarItem(_ viewController: UIViewController, title: String?, image: String?) -> UIViewController {
        let navController = UINavigationController(rootViewController: viewController)
        navController.navigationBar.prefersLargeTitles = true
        
        viewController.navigationItem.title = title
        viewController.tabBarItem.title = title
        if let image = image {
            let imageIcon = UIImage(named: image)
            viewController.tabBarItem.image = imageIcon
        }
        return navController
    }
}
