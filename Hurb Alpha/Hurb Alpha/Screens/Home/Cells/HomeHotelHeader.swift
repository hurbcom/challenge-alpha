//
//  HomeHotelHeader.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 29/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import UIKit
import Cosmos

class HomeHotelHeader: UITableViewHeaderFooterView {
    
    private lazy var starView: CosmosView = {
        var settings = CosmosSettings()
        settings.fillMode = .full
        let view = CosmosView(settings: settings)
        view.isUserInteractionEnabled = false
        return view
    }()
    
    private lazy var packagesLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 14, weight: .medium)
        label.numberOfLines = 0
        label.textColor = .darkGray
        label.lineBreakMode = .byWordWrapping
        label.textAlignment = .left
        label.text = "Pacotes"
        return label
    }()
    
    override init(reuseIdentifier: String?) {
        super.init(reuseIdentifier: reuseIdentifier)
        configureLayout()
        
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func configure(stars: Int?) {
        if let stars = stars {
            starView.rating = Double(stars)
            starView.isHidden = false
            packagesLabel.isHidden = true
        } else {
            starView.isHidden = true
            packagesLabel.isHidden = false
        }
    }
}

private extension HomeHotelHeader {
    func configureLayout() {
        contentView.backgroundColor = .primaryColor
        
        starView.createConstraints(contentView) { maker in
            maker.leading.trailing.equalToSuperview().inset(16)
            maker.top.bottom.equalToSuperview().inset(6)
        }
        
        packagesLabel.createConstraints(contentView) { maker in
            maker.edges.equalTo(starView)
        }
    }
}
