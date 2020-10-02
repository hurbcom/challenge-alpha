//
//  AppDelegate.swift
//  Challenge-ios
//
//  Created by Andre Dias on 28/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    var window: UIWindow?
    var appCoordinator: AppCoordinator?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {

        UIApplication.shared.statusBarStyle = .lightContent
        self.window = UIWindow()
        self.appCoordinator = AppCoordinator(window: self.window!)
        self.appCoordinator?.start()
        return true
    }


}

