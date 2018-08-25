//
//  HotelsViewController.swift
//  Hotels
//
//  Created by Adolfho Athyla on 25/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import UIKit

class HotelsViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        HUAPIHelper.loadHotels { (hotels, success) in
            print(hotels)
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
