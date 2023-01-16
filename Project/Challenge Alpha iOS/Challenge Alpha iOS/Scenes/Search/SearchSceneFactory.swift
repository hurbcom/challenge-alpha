//
//  SearchSceneFactory.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import Foundation

enum SearchSceneFactory {
    static func build() -> SearchViewHostingController {
        let interactor = SearchInteractor()
        let router = SearchRouter()
        let viewModel = SearchViewModel(interactor: interactor, router: router)
        let rootView = SearchView(viewModel: viewModel)
        let viewController = SearchViewHostingController(rootView: rootView)
        
        router.viewController = viewController
        
        return viewController
    }
}
