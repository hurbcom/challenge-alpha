//
//  ViewController.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 04/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import UIKit

/// Custom UIViewController based on Hurb paterns for this aplication
class HurbViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        netManager.state = .loading
        netManager.getOffers(place: "buzios", page: 1) { [weak self] (offers, error) in
            if let err = error {
                debugPrint("An error has ocurred trying to get info ", err)
                netManager.state = .error
            }
            guard let offers = offers else { return }
            if netManager.enableLogs {
                 dump(offers)
            }
            netManager.state = .ready
        }
    }


}

