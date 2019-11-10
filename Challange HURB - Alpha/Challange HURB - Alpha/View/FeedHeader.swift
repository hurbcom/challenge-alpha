//
//  HotelsHeader.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 09/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import UIKit
import SnapKit

class FeedHeader: UITableViewHeaderFooterView {
    
    var starsNumberLabel: UILabel = {
        let label = UILabel()
        label.font = .systemFont(ofSize: 28.0, weight: .semibold)
        label.textColor = #colorLiteral(red: 0.1396300793, green: 0.2136737406, blue: 0.4599552155, alpha: 1)
        return label
    }()
    
    override public init(reuseIdentifier: String?) {
        super.init(reuseIdentifier: reuseIdentifier)
        self.contentView.addSubview(starsNumberLabel)
        self.tintColor = .white
        setUpConstraints()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func setUpConstraints() {
        starsNumberLabel.snp.makeConstraints { (make) in
            make.top.equalToSuperview()
            make.leading.equalTo(contentView.snp.leading).offset(20)
            make.trailing.equalTo(contentView.snp.trailing).offset(-20)
            make.bottom.equalToSuperview().priority(999)
        }
    }

}
