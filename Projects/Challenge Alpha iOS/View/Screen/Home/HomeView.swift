//
//  HomeView.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 16/01/23.
//

import UIKit

class HomeVIew: UIView {
    
    // MARK: - Private Properties UI
   
    


    
    // MARK: - Init
    init() {
       super.init(frame: .zero)
        setupView()
   }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

extension HomeVIew: ViewCodableProtocol {
    func setupView() {
        buildViewHierarchy()
        setupConstraints()
        setupAdditionalConfiguration()
    }

    func buildViewHierarchy() {}

    func setupConstraints() {}

    func setupAdditionalConfiguration() {}
}
