//
//  HomeViewController.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 23/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import UIKit

class HomeViewController: BaseViewController {
    let viewModel: HomeViewModel
    
    init(viewModel: HomeViewModel) {
        self.viewModel = viewModel
        super.init()
        configureLayout()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

private extension HomeViewController {
    func configureLayout() {
        view.backgroundColor = .white
        //TODO: remove api test
        HotelsService.getHotels(query: "buzios") { result in
            switch result {
            case .failure(let error, _):
                print(error)
            case .success(let hotels, _):
                print("opa")
            }
        }
        
    }
}
