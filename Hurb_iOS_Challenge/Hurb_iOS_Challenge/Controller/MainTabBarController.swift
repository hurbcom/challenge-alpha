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
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        if #available(iOS 13.0, *) {
            let window = UIApplication.shared.windows.filter {$0.isKeyWindow}.first
            lazy var statusBarHeight = window?.windowScene?.statusBarManager?.statusBarFrame.size.height ?? 20
            
            let statusbarView = UIView()
            statusbarView.backgroundColor = UIColor.white
            view.addSubview(statusbarView)
          
            statusbarView.translatesAutoresizingMaskIntoConstraints = false
            statusbarView.heightAnchor
                .constraint(equalToConstant: statusBarHeight).isActive = true
            statusbarView.widthAnchor
                .constraint(equalTo: view.widthAnchor, multiplier: 1.0).isActive = true
            statusbarView.topAnchor
                .constraint(equalTo: view.topAnchor).isActive = true
            statusbarView.centerXAnchor
                .constraint(equalTo: view.centerXAnchor).isActive = true
          
        } else {
            let statusBar = UIApplication.shared.value(forKeyPath: "statusBarWindow.statusBar") as? UIView
            statusBar?.backgroundColor = UIColor.white
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        configureMainTabBarControllers()
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
        tabBar.tintColor = .hurbMainBlue
        tabBar.unselectedItemTintColor = .lightGray
    }
    
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
    
    func configureNavigationBar(nav: UINavigationController) {
        let imageView = UIImageView(image: UIImage(named: "HurbNavigationBarLogo"))
        imageView.contentMode = .scaleAspectFit
        imageView.anchor(height: 35)
        nav.navigationBar.topItem?.titleView = imageView
    }
}
