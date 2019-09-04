//
//  ViewController.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 03/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import UIKit

class ViewController: UIViewController, DAORequester {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        DAO.instance.jsonDataRequest(page: 1, requester: self)
        
    }
    
    func readedDataFromJson(result: Hotel) {
        for element in result.results {
            print("\n===========", element.address.city)
        }
    }


}

