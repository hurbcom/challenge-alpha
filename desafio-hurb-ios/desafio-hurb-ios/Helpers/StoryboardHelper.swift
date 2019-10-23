//
//  StoryboardHelper.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 22/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import UIKit

struct StoryboardHelper {
    
    static let mainStoryboard = UIStoryboard.init(name: "Main", bundle: nil)
    static let detailVC: UIViewController = StoryboardHelper.mainStoryboard.instantiateViewController(withIdentifier: HotelDetailViewController.identifier)
}
