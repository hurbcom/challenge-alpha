//
//  HomeRouter.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 04/09/20.
//  Copyright (c) 2020 Edson Aparecido Guido. All rights reserved.
//

import UIKit

protocol HomeRouting: AnyObject {
    func navigateToDetail(hotel: Hotel)
}

final class HomeRouter: Router, HomeRouting {
    
    private let detailBuilder: DetailBuildable
    
    init(detailBuilder: DetailBuildable) {
        self.detailBuilder = detailBuilder
    }
    
    func navigateToDetail(hotel: Hotel) {
        viewController.navigationController!.pushViewController(
            detailBuilder.build(hotel: hotel),
            animated: true
        )
    }
    
}
