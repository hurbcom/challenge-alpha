//
//  SceneDelegate.swift
//  hurb-test
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import UIKit

import HotelSearch
import HotelSearchiOS

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        self.window = UIWindow(windowScene: windowScene)
        self.configureWindow()
    }

    func configureWindow() {
        let hotelSearcher = RemoteHotelSearcher(url: URL(string: "https://www.hurb.com/search/api?q=")!, client: URLSessionHTTPClient(session: .shared))
        let imageDataLoader = RemoteImageDataLoader(client: URLSessionHTTPClient(session: .shared))
        let controller = HotelSearchUIComposer.hotelSearchComposedWith(hotelSearcher: hotelSearcher, imageDataLoader: imageDataLoader)
        window?.rootViewController = controller
        window?.makeKeyAndVisible()
    }

}

