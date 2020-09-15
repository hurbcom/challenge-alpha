//
//  HotelSearchView.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 15/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

public protocol HotelSearchView: class {
    func display(_ model: [HotelViewModel])
    func displayError(_ error: String)
    func displayLoading(_ isLoading: Bool)
}
