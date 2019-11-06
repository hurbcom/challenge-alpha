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
    
    var hurbOffer: HurbOffers? {
        didSet {
            guard let offer = hurbOffer else { return }
            if let _ = offer.isHotel {
                makeHotelCell(with : offer)
                return
            }
            if let _ = offer.isPackage {
                makePackageCell(with: offer)
                return
            }
        }
    }
    var myImageView: UIImageView = {
        let view = UIImageView(frame: .zero)
        view.contentMode = .scaleAspectFill
        view.clipsToBounds = true
        view.layer.cornerRadius = 10
        view.backgroundColor = .white
        view.clipsToBounds = true
        return view
    }()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        
        setupUI()
        setupConstraints()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Methods for the Cell
    func setupUI() {
        ///add all elements in the contentView of the cell
        self.contentView.addSubview(myImageView)
    }
    /**
    Make the collectionViewCell  for a hotel

    - Parameter with  : The HurbOffer that will be offered as a hotel
    */
    private func makeHotelCell(with hotel: HurbOffers) {
        /// atribute the image
        guard let imageUrl = hotel.image else { return }
        myImageView.kf.setImage(with: imageUrl)
        
    }
    
    /**
    Make the collectionViewCell  for a package

    - Parameter with  : The HurbOffer that will be offered as a package
    */
    private func makePackageCell(with package: HurbOffers) {
        
    }
    
    // MARK: - Constraints
    /// This method sets the constrainsts using the SnapKit framework for a better usage with the constraint
    func setupConstraints() {
        
        myImageView.snp.makeConstraints { (make) in
            make.top.equalToSuperview().offset(10)
            make.leading.equalToSuperview().offset(10)
            make.trailing.equalToSuperview().offset(-10)
            make.height.equalTo(220)
        }
        
    }
}
