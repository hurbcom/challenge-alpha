//
//  HurbColllectionViewCell.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 05/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import Foundation
import UIKit
import Kingfisher
import SnapKit


/// Custom UICollectionViewCell based on Hurb paterns for this aplication
class HurbTableViewCell: UITableViewCell {
    // MARK: - Stored Properties
    
    // MARK: - Init
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        
        setupUI()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Variables for the cell
    /// offer that will be represented in the cell
    var hurbOffer: HurbOffers? {
        didSet {
            guard let offer = hurbOffer else { return }
            if let _ = offer.isHotel {
                makeHotelCell(with: offer)
                return
            }
            if let _ = offer.isPackage {
                makePackageCell(with: offer)
                return
            }
        }
    }
    
    var cardView: UIView = {
        let view = UIView(frame: .zero)
        view.contentMode = .scaleAspectFill
        view.layer.cornerRadius = 10
        view.backgroundColor = .white
        view.clipsToBounds = true
        
        return view
    }()
    
    /// imageView that will be used in the cell
    var cellImageView: UIImageView = {
        let view = UIImageView(frame: .zero)
        view.contentMode = .scaleAspectFill
        view.layer.cornerRadius = 10
        view.backgroundColor = .white
        view.clipsToBounds = true
        return view
    }()
    
    /// Label describing the name of the offer
    var nameLabel: UILabel = {
        let label = UILabel(frame: .zero)
        label.textColor = .black
        label.font = UIFont.systemFont(ofSize: 18, weight: .light)
        label.textAlignment = .left
        label.numberOfLines = 0
        return label
    }()
    
    /// amenities labels that describes the offer
    var amenitiesLabels: [UILabel] = {
        var labels : [UILabel] = []
        // 3 amenities because will be the number of amenities to show in the cell
        for _ in 0...2{
            let label = UILabel(frame: .zero)
            label.textColor = .lightGray
            label.font = UIFont.boldSystemFont(ofSize: 16)
            label.textAlignment = .left
            label.text = ""
            label.numberOfLines = 0
            labels.append(label)
        }
        return labels
    }()
    
    /// Label describing the loaction of the offer
    var locationLabel: UILabel = {
        let label = UILabel(frame: .zero)
        label.textColor = .black
        label.font = UIFont.boldSystemFont(ofSize: 13)
        label.textAlignment = .left
        label.numberOfLines = 0
        return label
    }()
    
    /// Label describing the price
    var pricePrefixLabel: UILabel = {
        let label = UILabel(frame: .zero)
        label.textColor = .hurbOrange
        // in this case that we only search around buzios
        label.text = "R$"
        label.font = UIFont.boldSystemFont(ofSize: 14)
        label.textAlignment = .left
        label.numberOfLines = 0
        return label
    }()
    
    /// Label describing the price
    var priceLabel: UILabel = {
        let label = UILabel(frame: .zero)
        label.textColor = .hurbOrange
        label.font = UIFont.boldSystemFont(ofSize: 28)
        label.textAlignment = .left
        label.numberOfLines = 0
        return label
    }()
    

    
    // MARK: - Methods for the Cell
    func setupUI() {
        // set up selection style
        self.selectionStyle = .none
        // add all elements in the contentView of the cell
        self.contentView.addSubview(cardView)
        self.contentView.addSubview(pricePrefixLabel)
        self.contentView.addSubview(priceLabel)
        self.contentView.addSubview(cellImageView)
        self.contentView.addSubview(nameLabel)
        self.contentView.addSubview(locationLabel)
        for amenitie in amenitiesLabels {
            self.contentView.addSubview(amenitie)
        }
        self.backgroundColor = .white
    }
    
    // Add Shadows
    func setUpShadows(){
        // adding shadows
        cardView.layer.shadowColor = UIColor.black.cgColor
        cardView.layer.shadowOpacity = 0.4
        cardView.layer.shadowOffset = .zero
        cardView.layer.shadowRadius = 6
        cardView.layer.masksToBounds = false
        cardView.layer.shadowPath = UIBezierPath(rect: cardView.bounds).cgPath
        cardView.layer.shouldRasterize = true
        cardView.layer.rasterizationScale = UIScreen.main.scale
        
    }
    
    /**
     Make the TableViewCell  for a hotel
     
     - Parameter with  : The HurbOffer that will be offered as a hotel
     */
    private func makeHotelCell(with hotel: HurbOffers) {
        // atribute the image
        guard let imageURL = hotel.image else { return }
        
        guard var comps = URLComponents(url: imageURL,
                                        resolvingAgainstBaseURL: false) else { return }
        comps.scheme = "https"
        cellImageView.kf.setImage(with: comps.url)
        
        // atribute labels
        nameLabel.text = hotel.name
        let locationText = "\(hotel.address.city)/\(hotel.address.state)"
        locationLabel.text = locationText.uppercased()
        let priceText = "\(Int(hotel.price.totalPrice!))"
        priceLabel.text = priceText
        for amenitieCounter in 0..<hotel.amenities.count {
            // check if pass 3 amenities
            if amenitieCounter > 2 {
                break
            }
            let amenitie = hotel.amenities[amenitieCounter]
            let amenitieLabel = amenitiesLabels[amenitieCounter]
            amenitieLabel.text = amenitie.name
        }
        setupConstraintsForHotel()
        setUpShadows()
    }
    
    /**
     Make the TableViewCell  for a package
     
     - Parameter with  : The HurbOffer that will be offered as a package
     */
    private func makePackageCell(with package: HurbOffers) {
        // atribute the image
        if !package.gallery.isEmpty {
            let imageURL = package.gallery[0].url
            guard var comps = URLComponents(url: imageURL,
                                            resolvingAgainstBaseURL: false) else { return }
            comps.scheme = "https"
            cellImageView.kf.setImage(with: comps.url)
        }
        
        // atribute labels
        nameLabel.text = package.name
        let priceText = "\(Int(package.price.currentPrice!))"
        priceLabel.text = priceText
        for amenitieCounter in 0..<package.amenities.count {
            // check if pass 3 amenities
            if amenitieCounter > 2 {
                break
            }
            let amenitie = package.amenities[amenitieCounter]
            let amenitieLabel = amenitiesLabels[amenitieCounter]
            amenitieLabel.text = amenitie.name
        }
        setupConstraintsForPackage()
        setUpShadows()
        
    }
    
    // MARK: - Constraints
    /// This method sets the constrainsts for the Hotels Cells using the SnapKit framework for a better usage with the constraint
    func setupConstraintsForHotel() {
        cardView.snp.makeConstraints { (make) in
            make.top.equalToSuperview().offset(10)
            make.leading.equalToSuperview().offset(10)
            make.trailing.equalToSuperview().offset(-10)
            make.height.equalTo(340)
        }
        
        cellImageView.snp.makeConstraints { (make) in
            make.top.equalTo(cardView.snp.top).offset(10)
            make.leading.equalTo(cardView.snp.leading).offset(10)
            make.trailing.equalTo(cardView.snp.trailing).offset(-10)
            make.height.equalTo(180)
        }
        
        locationLabel.snp.makeConstraints { (make) in
            make.top.equalTo(cellImageView.snp.bottom).offset(10)
            make.leading.equalTo(cellImageView.snp.leading).offset(5)
            make.trailing.equalTo(cellImageView.snp.trailing).offset(-25)
        }
        
        nameLabel.snp.makeConstraints { (make) in
            make.top.equalTo(locationLabel.snp.bottom).offset(5)
            make.leading.equalTo(cellImageView.snp.leading).offset(5)
            make.trailing.equalTo(cellImageView.snp.trailing).offset(-25)
        }
        
        pricePrefixLabel.snp.makeConstraints { (make) in
            make.top.equalTo(nameLabel.snp.bottom).offset(48)
            make.leading.equalTo(cellImageView.snp.leading).offset(5)
            make.trailing.equalTo(cellImageView.snp.trailing).offset(-25)
        }
        
        priceLabel.snp.makeConstraints { (make) in
            make.top.equalTo(nameLabel.snp.bottom).offset(35)
            make.leading.equalTo(pricePrefixLabel.snp.leading).offset(20)
            make.trailing.equalTo(pricePrefixLabel.snp.trailing).offset(0)
        }
        
        // contraints for the amenities
        var lastBottom: UIView = cellImageView
        var firstOffSet = 60
        for amenitie in amenitiesLabels {
            amenitie.snp.makeConstraints { (make) in
                make.top.equalTo(lastBottom.snp.bottom).offset(firstOffSet)
                make.leading.equalTo(cardView.snp.leading).offset(140)
                make.trailing.equalTo(cardView.snp.trailing).offset(-5)
            }
            lastBottom = amenitie
            firstOffSet = 10
        }
        
    }
    
    /// This method sets the constrainsts for the Packages Cells using the SnapKit framework for a better usage with the constraint
    func setupConstraintsForPackage() {
        
        cardView.snp.makeConstraints { (make) in
            make.top.equalToSuperview().offset(15)
            make.leading.equalToSuperview().offset(15)
            make.trailing.equalToSuperview().offset(-15)
            make.height.equalTo(340)
        }
        
        cellImageView.snp.makeConstraints { (make) in
            make.top.equalTo(cardView.snp.top).offset(10)
            make.leading.equalTo(cardView.snp.leading).offset(10)
            make.trailing.equalTo(cardView.snp.trailing).offset(-10)
            make.height.equalTo(180)
        }
        
        nameLabel.snp.makeConstraints { (make) in
            make.top.equalTo(cellImageView.snp.bottom).offset(20)
            make.leading.equalTo(cellImageView.snp.leading).offset(5)
            make.trailing.equalTo(cellImageView.snp.trailing).offset(-25)
        }
        
        pricePrefixLabel.snp.makeConstraints { (make) in
            make.top.equalTo(nameLabel.snp.bottom).offset(38)
            make.leading.equalTo(cellImageView.snp.leading).offset(5)
            make.trailing.equalTo(cellImageView.snp.trailing).offset(-25)
        }
        
        priceLabel.snp.makeConstraints { (make) in
            make.top.equalTo(nameLabel.snp.bottom).offset(25)
            make.leading.equalTo(pricePrefixLabel.snp.leading).offset(20)
            make.trailing.equalTo(pricePrefixLabel.snp.trailing).offset(0)
        }
        
        // contraints for the amenities
        var lastBottom: UIView = cellImageView
        var firstOffSet = 60
        for amenitie in amenitiesLabels {
            amenitie.snp.makeConstraints { (make) in
                make.top.equalTo(lastBottom.snp.bottom).offset(firstOffSet)
                make.leading.equalTo(cardView.snp.leading).offset(140)
                make.trailing.equalTo(cardView.snp.trailing).offset(-5)
            }
            lastBottom = amenitie
            firstOffSet = 10
        }
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        hurbOffer = nil
        nameLabel.text = ""
        locationLabel.text = ""
        cellImageView.image = nil
    }
}
