//
//  DetailViewController.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 25/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

final class DetailViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    // MARK: Builder
    class func builder(title: String) -> DetailViewController {
        let viewController = DetailViewController().instantiate() as! DetailViewController
        viewController.title = title
        return viewController
    }
}
