//
//  ViewController.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 25/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    var service = HotelUrbanoService()
    var hotels: Hotels?

    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.service.getHotels(success: { result  in
            self.hotels = result
            print("Hotels: \(String(describing: self.hotels))")
        }) { error in
            print("ERRRRRRO")
            
        }
        
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

