//
//  HANavBar.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 23/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import UIKit

class HANavBar: UINavigationBar {
    override init(frame: CGRect) {
        super.init(frame: frame)
        barTintColor = .primaryColor
        tintColor = .white
        setBackgroundImage(UIImage(), for: .default)
        shadowImage = UIImage()
        isTranslucent = false
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
