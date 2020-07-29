//
//  EmptyView.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 28/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import UIKit

enum EmptyViewType {
    case search,
    searchInitial
    
    var body: String {
        switch self {
        case .search: return "Nenhum resultado encontrado"
        case .searchInitial: return "Para começar digite sua busca"
        }
    }
    
    var icon: UIImage? {
        switch self {
        case .search, .searchInitial: return #imageLiteral(resourceName: "SearchIcon")
        }
    }
}

class EmptyView: UIView {
    
    private lazy var iconImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFill
        return imageView
    }()
    
    private lazy var bodyLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 14, weight: .medium)
        label.textColor = .darkGray
        label.textAlignment = .center
        label.numberOfLines = 0
        return label
    }()
    
    func configure(type: EmptyViewType) {
        configureLayout()
        iconImageView.image = type.icon
        bodyLabel.text = type.body
    }
}

private extension EmptyView {
    func configureLayout() {
        addSubview(bodyLabel)
        bodyLabel.snp.makeConstraints { make in
            make.leading.trailing.equalToSuperview().inset(45)
            make.centerX.equalToSuperview()
            make.top.equalTo(snp.centerY)
        }
        
        addSubview(iconImageView)
        iconImageView.snp.makeConstraints { make in
            make.centerX.equalToSuperview()
            make.size.equalTo(CGSize(width: 34, height: 34))
            make.bottom.equalTo(bodyLabel.snp.top).offset(-22)
        }
    }
}
