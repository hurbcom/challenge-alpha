//
//  TabBarPresenter.swift
//  GPSurb
//
//  Created by Gilson Santos on 14/10/19.
//  Copyright (c) 2019 Gilson Santos. All rights reserved.
//

import Foundation
import UIKit

// MARK: - ENUM TABBAR -
enum StoryBoardName: String {
    case home = "Home"
    case favorites = "Favorites"
}

enum IdentifierViewController: String {
    case home = "NavigationHome"
    case favorites = "NavigationFavorites"
}

enum ImageName: String {
    case homeNormal = "home_off"
    case homeSelected = "home_on"
    case favoriteNormal = "favorite_off"
    case favoriteSelected = "favorite_on"
}

enum MenuName: String {
    case home = "Home"
    case favorite = "Favoritos"
}

// MARK: - STRUCT VIEW DATA -
struct TabBarViewData {
    var tabaBarItens = [TabBarItem]()
}

struct TabBarItem {
    var name = MenuName.home
    var storyBoard = StoryBoardName.home
    var viewControllerIdentifier = IdentifierViewController.home
    var imageTabBar = TabBarImage()
    var accessibilityIdentifier: String {
        return "\(self.name)-identifier"
    }
}

struct TabBarImage {
    var imageNameNormal = ImageName.homeNormal
    var imageNameSelected = ImageName.homeSelected
    var imageNotSelected: UIImage? {
        return UIImage(named: self.imageNameNormal.rawValue)
    }
    var imageSelected: UIImage? {
        return UIImage(named: self.imageNameSelected.rawValue)
    }
}

// MARK: - VIEW DELEGATE -
protocol TabBarViewDelegate: NSObjectProtocol {
    func setViewData(viewData: TabBarViewData)
}

// MARK: - PRESENTER CLASS -
class TabBarPresenter {
    
    private weak var viewDelegate: TabBarViewDelegate?
    private var viewData = TabBarViewData()
    private lazy var itens = [MenuName]()
    
    init(viewDelegate: TabBarViewDelegate) {
        self.viewDelegate = viewDelegate
    }
}

// MARK: - PUBLICS METHODS -
extension TabBarPresenter {
    func getTabBarItens() {
        self.createTabBar()
        self.viewDelegate?.setViewData(viewData: self.viewData)
    }
    
    func createTabBar() {
        self.itens.append(.home)
        self.itens.append(.favorite)
        self.itens.forEach { (itemMenu) in
            self.viewData.tabaBarItens.append(self.getMenuItem(menuNameTabBar: itemMenu))
        }
    }
}

// MARK: - AUX METHODS -
extension TabBarPresenter {
    
    private func getMenuItem(menuNameTabBar: MenuName) -> TabBarItem {
        switch menuNameTabBar {
        case .home:
            return self.getItemTabBar(storyBoardName: .home,
                                      controllerId: .home,
                                      nameMenu: .home,
                                      imageSelectedName: .homeSelected,
                                      imageNotSelectedName: .homeNormal)
        case .favorite:
            return self.getItemTabBar(storyBoardName: .favorites,
                                      controllerId: .favorites,
                                      nameMenu: .favorite,
                                      imageSelectedName: .favoriteSelected,
                                      imageNotSelectedName: .favoriteNormal)
        }
    }
      
    private func getItemTabBar(storyBoardName: StoryBoardName,
                               controllerId: IdentifierViewController,
                               nameMenu: MenuName, imageSelectedName: ImageName,
                               imageNotSelectedName: ImageName) -> TabBarItem {
        
        let imageTabBar = TabBarImage(imageNameNormal: imageNotSelectedName,
                                      imageNameSelected: imageSelectedName)
        
        return TabBarItem(name: nameMenu, storyBoard: storyBoardName,
                          viewControllerIdentifier: controllerId,
                          imageTabBar: imageTabBar)
    }
}
