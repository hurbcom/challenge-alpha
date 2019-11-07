//
//  AppDelegate.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 04/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    // window of the application
    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        // Initiates app without use of storyboard
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = HurbViewController()
        window?.makeKeyAndVisible()
        return true
    }


}

