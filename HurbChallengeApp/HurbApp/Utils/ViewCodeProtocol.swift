//
//  ViewCodeProtocol.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 23/01/23.
//

import Foundation

protocol ViewCodeProtocol {
    func buildView()
    func setupView()
    func setupLayoutConstraints()
}

extension ViewCodeProtocol {

    func buildView() {
        setupView()
        setupLayoutConstraints()
    }

}
