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
    
    /// imageView that will be used in the cell
    var cellImageView: UIImageView = {
        let view = UIImageView(frame: .zero)
        view.contentMode = .scaleAspectFill
        view.clipsToBounds = true
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
    
    /// Label describing the loaction of the offer
    var locationLabel: UILabel = {
        let label = UILabel(frame: .zero)
        label.textColor = .black
        label.font = UIFont.boldSystemFont(ofSize: 13)
        label.textAlignment = .left
        label.numberOfLines = 0
        return label
    }()
    
    // MARK: - Methods for the Cell
    func setupUI() {
        ///add all elements in the contentView of the cell
        self.contentView.addSubview(cellImageView)
        self.contentView.addSubview(nameLabel)
        self.contentView.addSubview(locationLabel)
        self.backgroundColor = .white
    }
    
    /**
    Make the TableViewCell  for a hotel

    - Parameter with  : The HurbOffer that will be offered as a hotel
    */
    private func makeHotelCell(with hotel: HurbOffers) {
        /// atribute the image
        guard let imageURL = hotel.image else { return }
        
        guard var comps = URLComponents(url: imageURL,
                                        resolvingAgainstBaseURL: false) else { return }
        comps.scheme = "https"
        cellImageView.kf.setImage(with: comps.url)
        
        nameLabel.text = hotel.name
        let locationText = "\(hotel.address.city)/\(hotel.address.state)"
        locationLabel.text = locationText.uppercased()
        setupConstraintsForHotel()
    }
    
    /**
    Make the TableViewCell  for a package

    - Parameter with  : The HurbOffer that will be offered as a package
    */
    private func makePackageCell(with package: HurbOffers) {
        /// atribute the image
        if !package.gallery.isEmpty {
            let imageURL = package.gallery[0].url
            guard var comps = URLComponents(url: imageURL,
                                            resolvingAgainstBaseURL: false) else { return }
            comps.scheme = "https"
            cellImageView.kf.setImage(with: comps.url)
        }
        
        nameLabel.text = package.name
        setupConstraintsForPackage()
        
    }
    
    // MARK: - Constraints
    /// This method sets the constrainsts for the Hotels Cells using the SnapKit framework for a better usage with the constraint
    func setupConstraintsForHotel() {
        
        cellImageView.snp.makeConstraints { (make) in
            make.top.equalToSuperview().offset(10)
            make.leading.equalToSuperview().offset(10)
            make.trailing.equalToSuperview().offset(-10)
            make.height.equalTo(180)
        }
        
        locationLabel.snp.makeConstraints { (make) in
            make.top.equalTo(cellImageView.snp.bottom).offset(10)
            make.leading.equalTo(cellImageView.snp.leading).offset(5)
            make.trailing.equalTo(cellImageView.snp.trailing).offset(-25)
        }
        
        nameLabel.snp.makeConstraints { (make) in
            make.top.equalTo(locationLabel.snp.bottom).offset(10)
            make.leading.equalTo(cellImageView.snp.leading).offset(5)
            make.trailing.equalTo(cellImageView.snp.trailing).offset(-25)
        }
        
        
    }
    
    /// This method sets the constrainsts for the Packages Cells using the SnapKit framework for a better usage with the constraint
    func setupConstraintsForPackage() {
        
        cellImageView.snp.makeConstraints { (make) in
            make.top.equalToSuperview().offset(10)
            make.leading.equalToSuperview().offset(5)
            make.trailing.equalToSuperview().offset(-10)
            make.height.equalTo(180)
        }
        
        nameLabel.snp.makeConstraints { (make) in
            make.top.equalTo(cellImageView.snp.bottom).offset(20)
            make.leading.equalTo(cellImageView.snp.leading).offset(5)
            make.trailing.equalTo(cellImageView.snp.trailing).offset(-25)
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
