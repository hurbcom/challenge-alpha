//
//  UIView.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 28/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import UIKit
import SnapKit

extension UIView {
    func createConstraints(_ container: UIView, closure: (ConstraintMaker) -> Void) {
        container.addSubview(self)
        self.snp.makeConstraints(closure)
    }
}
