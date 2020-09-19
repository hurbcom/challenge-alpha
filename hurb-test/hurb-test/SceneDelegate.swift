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
        // Use this method to optionally configure and attach the UIWindow `window` to the provided UIWindowScene `scene`.
        // If using a storyboard, the `window` property will automatically be initialized and attached to the scene.
        // This delegate does not imply the connecting scene or session are new (see `application:configurationForConnectingSceneSession` instead).
        guard let windowScene = (scene as? UIWindowScene) else { return }
        let window = UIWindow(windowScene: windowScene)
        let hotelSearcher = RemoteHotelSearcher(url: URL(string: "https://www.hurb.com/search/api?q=")!, client: URLSessionHTTPClient(session: .shared))
        let imageDataLoader = RemoteImageDataLoader(client: URLSessionHTTPClient(session: .shared))
        let controller = HotelSearchUIComposer.hotelSearchComposedWith(hotelSearcher: MainQueueDispatchDecorator(decoratee: hotelSearcher), imageDataLoader: MainQueueDispatchDecorator(decoratee: imageDataLoader))
        window.rootViewController = controller
        window.makeKeyAndVisible()
        self.window = window
    }


}

