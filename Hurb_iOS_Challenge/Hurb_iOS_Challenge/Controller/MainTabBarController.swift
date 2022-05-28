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
    }
    
    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
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
        
        tabBar.backgroundColor = .white
        tabBar.tintColor = .black
    }
    
    func templateNatigationController(image: UIImage?, rootViewControoler: UIViewController) -> UINavigationController {
        let nav = UINavigationController(rootViewController: rootViewControoler)
        nav.tabBarItem.image = image
        
        /// Costumization of the `NavigationBar` color after the iOS 15 atualizations.
        /// Now the `standardAppearance` must be equal to `scrollEdgeAppearance` to not be transparent.
        let appearance = UINavigationBarAppearance()
        appearance.configureWithOpaqueBackground()
        appearance.backgroundColor = .white
        appearance.titleTextAttributes = [.foregroundColor: UIColor.black]
        
        nav.navigationBar.standardAppearance = appearance
        nav.navigationBar.scrollEdgeAppearance = nav.navigationBar.standardAppearance
        nav.navigationBar.tintColor = .black
        nav.navigationBar.barStyle = .default
        nav.navigationBar.isTranslucent = false
        nav.navigationBar.titleTextAttributes = [.foregroundColor: UIColor.white]
        nav.hidesBarsOnSwipe = false
        
        configureNavigationBar(nav: nav)
        
        return nav
    }
    
    func configureNavigationBar(nav: UINavigationController) {
        let imageView = UIImageView(image: UIImage(named: "HurbNavigationBarLogo"))
        imageView.contentMode = .scaleAspectFit
        imageView.anchor(height: 35)
        nav.navigationBar.topItem?.titleView = imageView
    }
}

// MARK: - UINavigationController
    
extension UINavigationController {
    open override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
}
