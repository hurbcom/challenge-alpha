//
//  StoryboardInitializable.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 05/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import UIKit

protocol StoryboardInitializable {
    static func instantiate() -> Self
}

extension StoryboardInitializable where Self: UIViewController {
    static func instantiate() -> Self {
        let fullName = NSStringFromClass(self)
        
        let className = fullName.components(separatedBy: ".")[1]

        let storyboard = UIStoryboard(name: "Main", bundle: Bundle.main)

        // instantiate a view controller with that identifier, and force cast as the type that was requested
        return storyboard.instantiateViewController(withIdentifier: className) as! Self
    }
}
