//
//  HotelsCollectionViewCell.swift
//  Alpha
//
//  Created by Theo Mendes on 18/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit

class HotelCollectionViewCell: BaseCollectionViewCell {
    var hotel: APIResult? {
        didSet {
            guard let hotel = hotel else { return }
            titleLabel.text = hotel.name
            priceLabel.text = "\(hotel.price.symbol) \(hotel.price.amount)"
        }
    }

    var imageView: UIImageView = {
        let view = UIImageView(frame: .zero)
        view.contentMode = .scaleAspectFill
        view.clipsToBounds = true
        view.contentMode = .scaleAspectFill
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
        label.font = .systemFont(ofSize: 20.0, weight: .regular)
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

    override func setupUI() {
        self.contentView.addSubview(imageView)
        self.contentView.addSubview(titleLabel)
        self.contentView.addSubview(priceLabel)

//        roundPriceLabelCorners()
    }

    override func setupConstraints() {
        // Aspect Ratio is original height / width
        let imageAspec = (200.0 / 153.0)

        imageView.snp.makeConstraints { (make) in
            make.top.equalToSuperview()
            make.width.equalTo(153.0)
            make.height.equalTo(200).priority(999)
        }

        titleLabel.snp.makeConstraints { (make) in
            make.top.equalTo(imageView.snp.bottom).offset(10)
            make.leading.trailing.equalTo(imageView)
            make.bottom.equalToSuperview()
        }

        priceLabel.snp.makeConstraints { (make) in
            make.top.equalTo(imageView.snp.top)
            make.trailing.equalTo(imageView.snp.trailing)
        }
    }

    private func roundPriceLabelCorners() {
        let rectShape = CAShapeLayer()
        rectShape.bounds = priceLabel.frame
        rectShape.position = priceLabel.center
        rectShape.path = UIBezierPath(roundedRect: priceLabel.bounds, byRoundingCorners: [.bottomLeft, .topRight], cornerRadii: CGSize(width: 8, height: 8)).cgPath
        priceLabel.layer.mask = rectShape
    }
}
