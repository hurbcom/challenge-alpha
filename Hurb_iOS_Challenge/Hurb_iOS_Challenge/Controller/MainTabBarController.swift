//
//  MainTabBarController.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 22/05/22.
//

import UIKit

class MainTabBarController: UITabBarController {
    
    // MARK: - Properties
    let homeViewController = HomeViewController()
    let lastSeenViewController = LastSeenViewController()
    let favoritesViewController = FavoritesViewController()
    
    // MARK: - Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        configureMainTabBarControllers()
        /// Configure View.
        view.backgroundColor = .white
    }
    
    // MARK: - Helper Methods
    func configureMainTabBarControllers() {
        /// Confire TabBar ViewControllers.
        let nav1 = templateNatigationController(image: UIImage(systemName: "house.fill"), rootViewControoler: homeViewController)
        let nav2 = templateNatigationController(image: UIImage(systemName: "heart.fill"), rootViewControoler: favoritesViewController)
        let nav3 = templateNatigationController(image: UIImage(systemName: "eye.fill"), rootViewControoler: lastSeenViewController)
        
        viewControllers = [nav1, nav2, nav3]
        
        /// Costumization of the `TabBar`.
        nav1.title = "Início"
        nav2.title = "Favoritos"
        nav3.title = "Últimos vistos"
        
        tabBar.barTintColor = .tabBarWhite
        tabBar.isTranslucent = true
        tabBar.backgroundColor = .tabBarWhite
        tabBar.unselectedItemTintColor = .lightGray
    }
    
    /// Function that returns a UINavigation Controller all set.
    /// - Parameters:
    ///   - image: System Name Icon.
    ///   - rootViewControoler: The corresponding ViewController.
    /// - Returns: Set the rootViewController and the basics layout to the navigationController.
    func templateNatigationController(image: UIImage?, rootViewControoler: UIViewController) -> UINavigationController {
        let nav = UINavigationController(rootViewController: rootViewControoler)
        nav.tabBarItem.image = image
        
        let appearance = UINavigationBarAppearance()
        appearance.backgroundColor = .white
        appearance.titleTextAttributes = [.foregroundColor: UIColor.white]
        
        nav.navigationBar.standardAppearance = appearance
        nav.navigationBar.tintColor = .black
        nav.navigationBar.barStyle = .black
        nav.navigationBar.isTranslucent = false
        nav.navigationBar.backgroundColor = .white
        nav.hidesBarsOnSwipe = false
        
        configureNavigationBar(nav: nav)
        
        return nav
    }
    
    /// Function that set an additional configuration for the NavigationBar layout.
    /// - Parameter nav: correspondent UINavigationController.
    func configureNavigationBar(nav: UINavigationController) {
        let imageView = UIImageView(image: UIImage(named: "HurbNavigationBarLogo"))
        imageView.contentMode = .scaleAspectFit
        imageView.anchor(height: 35)
        nav.navigationBar.topItem?.titleView = imageView
    }
}
