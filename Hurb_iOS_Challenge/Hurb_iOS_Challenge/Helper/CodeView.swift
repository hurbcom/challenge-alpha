//
//  CodeView.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 15/05/22.
//

/// Protocol that set the necessary functions to build UIViews Hierarchy, Constraints and Additional Configurations.
protocol CodeView {
    func buildViewHierarchy()
    func setupConstraints()
    func setupAdditionalConfiguration()
    func setupView()
}

extension CodeView {
    func setupView() {
        buildViewHierarchy()
        setupConstraints()
        setupAdditionalConfiguration()
    }
    
    func setupAdditionalConfiguration() {}
}
