//
//  AppDelegate.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 12/11/22.
//

import UIKit

@main
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        setupRootViewController()
        return true
    }
    
    private func setupRootViewController() {
        let tabbar = BaseTabbarController()
        
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = tabbar
        window?.makeKeyAndVisible()
    }
}

