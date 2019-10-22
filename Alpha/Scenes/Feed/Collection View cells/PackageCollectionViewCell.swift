//
//  PackageCollectionViewCell.swift
//  Alpha
//
//  Created by Theo Mendes on 19/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit
import Kingfisher

class PackageCollectionViewCell: BaseCollectionViewCell {
    // MARK: - Properties

    var package: Deal? {
        didSet {
            guard let package = package else { return }
            titleLabel.text = package.name
            currencyFormatter.currencySymbol = package.price.symbol

            priceLabel.text = currencyFormatter.string(from: NSNumber(value: package.price.amount))
            locationLabel.text = "\(package.address.city) | \(package.address.state)"

            var amenitiesText: String = "\(L10n.Feed.Cell.amenities): "

            for (idx, element) in package.amenities.enumerated() {
                if idx > 2 {
                    break
                }
                if idx == package.amenities.startIndex {
                    amenitiesText = amenitiesText + " \(element.name)"
                } else if idx != package.amenities.endIndex-1 {
                    amenitiesText = amenitiesText + ", \(element.name)"
                }
            }

            amenitiesLabel.text = amenitiesText

            guard var comps = URLComponents(url: package.gallery[0].url,
                                            resolvingAgainstBaseURL: false) else { return }
            comps.scheme = "https"
            imageView.kf.setImage(with: comps.url)

            if (package.amenities.filter({ $0.planeIncluded }).count >= 1) {
                flightLabel.isHidden = false
            } else {
                flightLabel.isHidden = true
            }
        }
    }

    private let currencyFormatter: NumberFormatter = {
        let formatter = NumberFormatter()
        formatter.usesGroupingSeparator = true
        formatter.numberStyle = .currency
        // Localize
        formatter.locale = Locale.current
        return formatter
    }()

    var imageView: GradientUIImage = {
        let view = GradientUIImage(frame: .zero)
        view.contentMode = .scaleAspectFill
        view.clipsToBounds = true
        view.layer.cornerRadius = 14
        view.layer.maskedCorners = [.layerMaxXMinYCorner, .layerMinXMinYCorner]
        view.backgroundColor = .gray
        view.clipsToBounds = true
        return view
    }()

    let titleLabel: UILabel = {
        let label = UILabel(frame: .zero)
        label.font = .systemFont(ofSize: 20.0, weight: .regular)
        label.textColor = .white
        label.textAlignment = .left
        label.numberOfLines = 2
        return label
    }()

    let amenitiesLabel: UILabel = {
        let label = UILabel(frame: .zero)
        label.font = .systemFont(ofSize: 12.0, weight: .regular)
        label.textColor = Theme.hurbLightGray
        label.textAlignment = .left
        label.numberOfLines = 2
        return label
    }()

    let locationLabel: UILabel = {
        let label = UILabel(frame: .zero)
        label.font = .systemFont(ofSize: 12.0, weight: .bold)
        label.textColor = Theme.hurbLightGray
        label.textAlignment = .left
        label.numberOfLines = 1
        return label
    }()

    let priceLabel: PaddingUILabel = {
        let label = PaddingUILabel(withInsets: 10, 10, 10, 10)
        label.font = .systemFont(ofSize: 18.0, weight: .semibold)
        label.textColor = .white
        label.textAlignment = .center
        label.numberOfLines = 1
        label.layer.cornerRadius = 14
        label.layer.maskedCorners = [.layerMinXMaxYCorner, .layerMaxXMaxYCorner]
        label.backgroundColor = Theme.hurbOrange
        label.clipsToBounds = true
        label.sizeToFit()
        return label
    }()

    let flightLabel: PaddingUILabel = {
        let label = PaddingUILabel(withInsets: 8, 10, 10, 8)
        label.font = .systemFont(ofSize: 16.0, weight: .bold)
        label.textColor = .black
        label.textAlignment = .center
        label.numberOfLines = 1
        label.isHidden = false
        label.text = L10n.Feed.Cell.planeIncluded
        label.layer.cornerRadius = 14
        label.layer.maskedCorners = [.layerMaxXMaxYCorner, .layerMinXMinYCorner]
        label.backgroundColor = Theme.hurbYellow
        label.clipsToBounds = true
        label.sizeToFit()
        return label
    }()

    // MARK: - View methods

    override func setupUI() {
        self.contentView.addSubview(imageView)
        self.contentView.addSubview(flightLabel)
        self.contentView.addSubview(titleLabel)
        self.contentView.addSubview(amenitiesLabel)
        self.contentView.addSubview(locationLabel)
        self.contentView.addSubview(priceLabel)
    }

    override func setupConstraints() {
        // Aspect Ratio is original height / width
        let imageAspec = (307.0 / 335.0)

        imageView.snp.makeConstraints { (make) in
            make.top.equalToSuperview()
            make.centerX.equalToSuperview()
            make.width.equalTo(UIScreen.main.bounds.width - 40)
            make.height.equalTo(imageView.snp.width).multipliedBy(imageAspec)
        }

        locationLabel.snp.makeConstraints { (make) in
            make.bottom.equalTo(imageView.snp.bottom).offset(-10)
            make.leading.equalTo(imageView.snp.leading).offset(10)
            make.trailing.equalTo(imageView.snp.trailing).offset(-10)
        }

        amenitiesLabel.snp.makeConstraints { (make) in
            make.bottom.equalTo(locationLabel.snp.top).offset(-5)
            make.leading.equalTo(imageView.snp.leading).offset(10)
            make.trailing.equalTo(imageView.snp.trailing).offset(-10)
        }

        titleLabel.snp.makeConstraints { (make) in
            make.bottom.equalTo(amenitiesLabel.snp.top).offset(-10)
            make.leading.equalTo(imageView.snp.leading).offset(10)
            make.trailing.equalTo(imageView.snp.trailing).offset(-10)
        }

        priceLabel.snp.makeConstraints { (make) in
            make.top.equalTo(imageView.snp.bottom)
            make.trailing.leading.equalTo(imageView)
        }

        flightLabel.snp.makeConstraints { (make) in
            make.top.equalTo(imageView.snp.top)
            make.leading.equalTo(imageView.snp.leading)
        }

    }

    override func prepareForReuse() {
        super.prepareForReuse()

        package = nil
        titleLabel.text = nil
        priceLabel.text = nil
        flightLabel.isHidden = false
        amenitiesLabel.text = nil
        imageView.image = nil
        locationLabel.text = nil

    }
}
