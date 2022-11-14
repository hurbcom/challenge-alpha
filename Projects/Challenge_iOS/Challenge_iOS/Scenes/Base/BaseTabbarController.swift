//
//  BaseTabbarController.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 12/11/22.
//

import UIKit

class BaseTabbarController: UITabBarController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        viewControllers = [
            buildTabbarItem(SearchViewController(), title: "Pesquisar", image: "ic-search"),
            buildTabbarItem(HotelViewController(), title: "Hotel", image: "ic-hotel"),
            buildTabbarItem(PackageViewController(), title: "Pacotes", image: "ic-package")
        ]
        
        selectedIndex = 0
    }
    
    private func buildTabbarItem(_ viewController: UIViewController,
                                 title: String?,
                                 image: String?) -> UIViewController {
        let navController = UINavigationController(rootViewController: viewController)
        navController.navigationBar.prefersLargeTitles = true
        
        viewController.navigationItem.title = title
        viewController.tabBarItem.title = title
        if let image = image {
            let tabImage = UIImageView(frame: CGRect(x: 0, y: 0, width: 30, height: 30))
            tabImage.image = UIImage(named: image)
            viewController.tabBarItem.image = tabImage.image
        }
        return navController
    }
}
