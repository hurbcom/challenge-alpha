//
//  HomeHotelCell.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 28/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import UIKit
import SDWebImage

class HomeHotelCell: UITableViewCell {
    
    private lazy var titleLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 16, weight: .bold)
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        return label
    }()
    
    private lazy var addressLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 14, weight: .medium)
        label.numberOfLines = 0
        label.textColor = .darkGray
        label.lineBreakMode = .byWordWrapping
        return label
    }()
    
    private lazy var priceLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 14, weight: .bold)
        label.lineBreakMode = .byWordWrapping
        label.textColor = .secondaryColor
        return label
    }()
    
    private lazy var amenittiesLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 14, weight: .medium)
        label.numberOfLines = 0
        label.textColor = .lightGray
        label.lineBreakMode = .byWordWrapping
        return label
    }()
    
    private lazy var resultImage: UIImageView = {
        let image = UIImageView()
        image.contentMode = .scaleAspectFill
        image.layer.cornerRadius = 5
        image.clipsToBounds = true
        return image
    }()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        configureLayout()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        resultImage.image = nil
    }
    
    func configure(result: Result) {
        resultImage.loadWith(result.image)
        titleLabel.text = result.name
        configureAddress(city: result.address?.city, state: result.address?.state)
        configureAmenitties(amenities: result.amenities)
        configurePrice(price: result.price?.amount)
    }
}

private extension HomeHotelCell {
    func configureLayout() {        
        resultImage.createConstraints(contentView) { maker in
            maker.leading.equalToSuperview().inset(16)
            maker.top.bottom.equalToSuperview().inset(8)
            maker.width.equalTo(80)
            maker.height.equalTo(100)
        }
        
        priceLabel.createConstraints(contentView) { maker in
            maker.top.trailing.equalToSuperview().inset(8)
        }
        
        titleLabel.createConstraints(contentView) { maker in
            maker.leading.equalTo(resultImage.snp.trailing).offset(16)
            maker.trailing.equalToSuperview().inset(6)
            maker.top.equalTo(priceLabel.snp.bottom)
        }
        
        addressLabel.createConstraints(contentView) { maker in
            maker.leading.equalTo(titleLabel)
            maker.trailing.equalToSuperview().inset(6)
            maker.top.equalTo(titleLabel.snp.bottom).offset(6)
        }
        
        amenittiesLabel.createConstraints(contentView) { maker in
            maker.leading.trailing.equalTo(addressLabel)
            maker.top.equalTo(addressLabel.snp.bottom).offset(3)
//            maker.bottom.greaterThanOrEqualToSuperview().inset(5).priority(.low)
        }
        
        priceLabel.createConstraints(contentView) { maker in
            maker.top.trailing.equalToSuperview().inset(5)
        }
    }
    
    func configureAddress(city: String?, state: String?) {
        if let city = city,
            !city.isEmpty,
            let state = state,
            !state.isEmpty {
            addressLabel.isHidden = false
            let address = "\(city), \(state)"
            addressLabel.text = address
        } else {
            addressLabel.isHidden = true
        }
    }
    
    func configureAmenitties(amenities: [Amenities]?) {
        if let amenities = amenities,
            !amenities.isEmpty {
            amenittiesLabel.isHidden = false
            let text = amenities.compactMap { $0.name }.prefix(3).joined(separator: ", ")
            amenittiesLabel.text = text
        } else {
            amenittiesLabel.isHidden = true
        }
    }
    
    func configurePrice(price: Double?) {
        if let price = price {
            priceLabel.isHidden = false
            let text = "R$ \(price)"
            priceLabel.text = text
        } else {
            priceLabel.isHidden = true
        }
    }
}
