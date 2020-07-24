//
//  SearchNotFoundView.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

class SearchNotFoundView: UIView {
    
    func instanceFromNib() -> UIView {
        return UINib(nibName: String(describing: Self.self), bundle: nil).instantiate(withOwner: nil, options: nil)[0] as! UIView
    }
    
}
