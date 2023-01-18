//
//  BaseViewPipeline.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 16/01/23.
//

import UIKit

class BaseViewPipeline: UIView {
    
    open var hierarchies: [BaseViewHierarchyRelation] { [] }
    
    public override init(frame: CGRect) {
        super.init(frame: frame)
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setupConstraints() {
        NSLayoutConstraint.activate(constraints)
    }
    
    private func setupView() {
        for relation in hierarchies {
            relation.makeHierarchy()
        }
    }
}
