//
//  AppDelegate.swift
//  Alpha
//
//  Created by Theo Mendes on 13/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit
import os.log

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?
    private var appCoordinator: AppCoordinator!

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        self.window = UIWindow()

        guard let window = window else { fatalError("Window was nil") }
        os_log("\nLog Meaning:\n👶 -> View Init\n⚰️ -> View Deinit\n🧠 -> View Model\n🧭 -> Coordinator\n🎮 -> View Controller\n🔲 -> Table View Cell\n🏻 -> Collection View Cell\n📶 -> Network\n\t⬇️ -> Receiving\n\t⬆️ -> Sending\n\t✅ -> Status Successful\n\t⚠️ -> Status Failure", log: Logger.appLog(), type: .info)

        let appCoordinator = AppCoordinator(window: window, provider: AlphaNetworkManager.shared)
        appCoordinator.start()
        self.appCoordinator = appCoordinator
        return true
    }

    func applicationWillResignActive(_ application: UIApplication) {}

    func applicationDidEnterBackground(_ application: UIApplication) {}

    func applicationWillEnterForeground(_ application: UIApplication) {}

    func applicationDidBecomeActive(_ application: UIApplication) {}

    func applicationWillTerminate(_ application: UIApplication) {}

}
