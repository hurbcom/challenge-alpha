//
//  ViewCodable.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 17/01/23.
//

import Foundation

public protocol ViewCodableProtocol: AnyObject {
    func setupView()
    func buildViewHierarchy()
    func setupConstraints()
    func setupAdditionalConfiguration()
   
}

public extension ViewCodableProtocol {
    func setupView() {
        buildViewHierarchy()
        setupConstraints()
        setupAdditionalConfiguration()
    }

    func buildViewHierarchy() {}

    func setupConstraints() {}

    func setupAdditionalConfiguration() {}
}
