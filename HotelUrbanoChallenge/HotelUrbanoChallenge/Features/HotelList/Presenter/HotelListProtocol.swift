//
//  HotelListProtocol.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 26/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

protocol HotelListProtocol: class {
    
    func startLoading()
    func stopLoading()
    func reloadTableView()
    func showAlertError(with title: String, message: String, buttonTitle: String)
}
