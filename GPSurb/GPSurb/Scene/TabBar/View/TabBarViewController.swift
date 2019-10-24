//
//  TabBarViewController.swift
//  GPSurb
//
//  Created by Gilson Santos on 14/10/19.
//  Copyright (c) 2019 Gilson Santos. All rights reserved.
//

import UIKit

class TabBarViewController: UITabBarController {
    
    // MARK: OUTLETS
    
    // MARK: CONSTANTS
    
    // MARK: VARIABLES
    private var presenter: TabBarPresenter!
    private lazy var viewData = TabBarViewData()
    
    // MARK: IBACTIONS
}

// MARK: - LIFE CYCLE -
extension TabBarViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        self.presenter = TabBarPresenter(viewDelegate: self)
        self.presenter.getTabBarItens()
    }
}

// MARK: - DELEGATE PRESENTER -
extension TabBarViewController: TabBarViewDelegate {
    func setViewData(viewData: TabBarViewData) {
        self.viewData = viewData
        self.createTabBar()
    }
}

// MARK: - AUX METHODS -
extension TabBarViewController {
    private func createTabBar() {
        var controllers = [UIViewController]()
        self.viewData.tabaBarItens.forEach { (viewDataRow) in
            let controller = self.getViewController(viewData: viewDataRow)
            controller.tabBarItem = self.getTabBarItem(viewData: viewDataRow)
            controllers.append(controller)
        }
        self.viewControllers = controllers
        self.selectedIndex = 0
    }
    
    private func getViewController(viewData: TabBarItem) -> UIViewController {
        let storyboard = UIStoryboard(name: viewData.storyBoard.rawValue, bundle: nil)
        let viewController = storyboard.instantiateViewController(withIdentifier: viewData.viewControllerIdentifier.rawValue)
        return viewController
    }
    
    private func getTabBarItem(viewData: TabBarItem) -> UITabBarItem {
        let item = UITabBarItem(title: viewData.name.rawValue,
                                image: viewData.imageTabBar.imageNotSelected?.withRenderingMode(.alwaysOriginal),
                                selectedImage: viewData.imageTabBar.imageSelected?.withRenderingMode(.alwaysOriginal))
        
        let attibutes = [NSAttributedString.Key.foregroundColor: UIColor.systemGreen,
                         NSAttributedString.Key.font: UIFont.boldSystemFont(ofSize: 10)]
        item.setTitleTextAttributes(attibutes, for: .normal)
        item.accessibilityIdentifier = viewData.accessibilityIdentifier
        return item
    }
}
