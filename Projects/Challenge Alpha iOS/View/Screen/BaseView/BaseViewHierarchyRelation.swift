//
//  BaseViewHierarchyRelation.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 16/01/23.
//

import UIKit

struct BaseViewHierarchyRelation {
    let parentView: UIView
    let subViews: [UIView]
    
    init(parentView: UIView, subViews: [UIView]) {
        self.parentView = parentView
        self.subViews = subViews
    }
    
    func makeHierarchy() {
        if let stackView = parentView as? UIStackView {
            for view in subViews {
                stackView.addArrangedSubview(view)
            }
        } else {
            for view in subViews {
                parentView.addSubview(view)
            }
        }
    }
}
