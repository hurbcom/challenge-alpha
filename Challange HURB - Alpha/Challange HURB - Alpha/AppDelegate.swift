//
//  AppDelegate.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 26/10/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var coordinator: MainCoordinator?
    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // create the main navigation controller to be used for our app
        let navController = UINavigationController()
//
//        // send that into our coordinator so that it can display view controllers
        coordinator = MainCoordinator(navigationController: navController)
//
//        // tell the coordinator to take over control
        coordinator!.start()

        // create a basic UIWindow and activate it
         window = UIWindow(frame: UIScreen.main.bounds)
        window!.rootViewController = navController
//        window.rootViewController = UIViewController()
//        window.rootViewController!.view.backgroundColor = .red
        window!.makeKeyAndVisible()

        return true
    }

}

