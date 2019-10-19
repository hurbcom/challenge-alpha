//
//  HotelsCollectionViewCell.swift
//  Alpha
//
//  Created by Theo Mendes on 18/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit
import Kingfisher

class HotelCollectionViewCell: BaseCollectionViewCell {
    var isFirst: Bool = false
    var isLast: Bool = false

    var hotel: APIResult? {
        didSet {
            guard let hotel = hotel else { return }
            titleLabel.text = hotel.name
            priceLabel.text = "\(hotel.price.symbol) \(hotel.price.amount)"
            guard let imageURL = hotel.image else { return }
            guard var comps = URLComponents(url: imageURL,
                                            resolvingAgainstBaseURL: false) else { return }
            comps.scheme = "https"
            imageView.kf.setImage(with: comps.url)
            guard let currentPrice = hotel.price.currentPrice, let oldPrice = hotel.price.oldPrice else { return }
            if oldPrice != 0 {
                let discount: Int = Int(((oldPrice - currentPrice) / oldPrice) * 100)
                if discount < 0 {
                    print("Has discount of \(discount)")
                    discountLabel.text = "\(String(discount))%"
                } else {
                    print("Dont have discount \(discount)")
                    discountLabel.isHidden = true
                }
            }
        }
    }

    var imageView: UIImageView = {
        let view = UIImageView(frame: .zero)
        view.contentMode = .scaleAspectFill
        view.clipsToBounds = true
        view.layer.cornerRadius = 14
        view.backgroundColor = .gray
        view.clipsToBounds = true
        return view
    }()

    let titleLabel: UILabel = {
        let label = UILabel(frame: .zero)
        label.font = .systemFont(ofSize: 20.0, weight: .regular)
        label.textColor = .black
        label.textAlignment = .center
        label.numberOfLines = 2
        return label
    }()

    let priceLabel: PaddingUILabel = {
        let label = PaddingUILabel(withInsets: 8, 8, 10, 10)
        label.font = .systemFont(ofSize: 16.0, weight: .regular)
        label.textColor = .white
        label.textAlignment = .center
        label.numberOfLines = 1
        label.layer.cornerRadius = 14
        label.layer.maskedCorners = [.layerMinXMaxYCorner, .layerMaxXMinYCorner]
        label.backgroundColor = .blue
        label.clipsToBounds = true
        label.sizeToFit()
        return label
    }()

    let discountLabel: PaddingUILabel = {
        let label = PaddingUILabel(withInsets: 8, 8, 10, 10)
        label.font = .systemFont(ofSize: 16.0, weight: .bold)
        label.textColor = .white
        label.textAlignment = .center
        label.numberOfLines = 1
        label.layer.cornerRadius = 14
        label.layer.maskedCorners = [.layerMinXMaxYCorner]
        label.backgroundColor = .orange
        label.clipsToBounds = true
        label.sizeToFit()
        return label
    }()

    override func setupUI() {
        self.contentView.addSubview(imageView)
        self.contentView.addSubview(titleLabel)
        self.contentView.addSubview(priceLabel)
        self.contentView.addSubview(discountLabel)
    }

    override func setupConstraints() {
        // Aspect Ratio is original height / width
        let imageAspec = (200.0 / 153.0)

        imageView.snp.makeConstraints { (make) in
            make.top.equalToSuperview()
            make.left.equalToSuperview()
            make.width.equalTo(205.0)
            make.height.equalTo(imageView.snp.width).priority(999)
        }

        titleLabel.snp.makeConstraints { (make) in
            make.top.equalTo(imageView.snp.bottom).offset(10)
            make.leading.trailing.equalTo(imageView)
        }

        priceLabel.snp.makeConstraints { (make) in
            make.top.equalTo(imageView.snp.top)
            make.trailing.equalTo(imageView.snp.trailing)
        }

        discountLabel.snp.makeConstraints { (make) in
            make.top.equalTo(priceLabel.snp.bottom)
            make.trailing.equalTo(imageView.snp.trailing)
        }
    }
}
