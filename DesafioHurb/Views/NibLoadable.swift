//
//  NibLoadable.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 04/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import UIKit

protocol NibLoadable: AnyObject {}

extension NibLoadable where Self: UIView {
    static var nibBundle: Bundle {
        return Bundle(for: self)
    }

    static var nibName: String {
        return String(describing: self)
    }

    static func loadFromNib(withOwner owner: Any?) -> Self {
        guard let view = nibBundle.loadNibNamed(nibName, owner: owner, options: nil)?.first as? Self else {
            fatalError("Could not load nib named '\(nibName)'.")
        }
        return view
    }
}
