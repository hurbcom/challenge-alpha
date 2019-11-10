//
//  HotelCollectionViewCell.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 07/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import UIKit
import Kingfisher
import SnapKit

class HotelCollectionViewCell: UICollectionViewCell {
    var hotel: Experience? {
        didSet {
            guard let hotel = self.hotel else { return }//fatalError("HotelCollectionViewCell - No hotel was set")}
            hotelName.text = hotel.name
            hotelAdress.text = "\(String(describing: hotel.address.city)) \(String(describing: hotel.address.state))"
            hotelPrice.text = "\(hotel.price.getPriceSymbol()) \(hotel.price.amount)"
            
            guard let imageURL = hotel.image else { return }
            guard var urlComps = URLComponents(url: imageURL,resolvingAgainstBaseURL: false) else {
                debugPrint("URLComponents could not transform URL")
                return
            }
            urlComps.scheme = "https"
            hotelPicture.kf.setImage(with: urlComps.url)
        }
    }
    
    let cardView: UIView = {
        let view = UIView(frame: .zero)
        view.backgroundColor = .clear
        view.layer.borderColor = UIColor.black.cgColor
        view.layer.borderWidth = 0.5
        view.layer.cornerRadius = 12
        return view
    }()
    
    let hotelPicture: UIImageView = {
        let view = UIImageView(frame: .zero)
        view.contentMode = .scaleAspectFill
        view.clipsToBounds = true
        view.layer.cornerRadius = 12
        view.backgroundColor = .gray
        view.clipsToBounds = true
        return view
    }()
    
    let hotelName: UILabel = {
        let label = UILabel(frame: .zero)
        label.font = .systemFont(ofSize: 15, weight: .bold)
        label.textColor = .black
        label.textAlignment = .left
        label.numberOfLines = 2
        return label
    }()
    
    let hotelAdress: UILabel = {
        let label = UILabel(frame: .zero)
        label.font = .systemFont(ofSize: 15, weight: .regular)
        label.textColor = .black
        label.textAlignment = .left
        label.numberOfLines = 2
        return label
    }()
    
    let hotelPrice: UILabel = {
        let label = UILabel(frame: .zero)
        label.font = .systemFont(ofSize: 20 , weight: .semibold)
        label.textColor = #colorLiteral(red: 0.9994023442, green: 0.4260467291, blue: 0.008425070904, alpha: 1)
        label.textAlignment = .right
        label.numberOfLines = 1
        return label
    }()
    
    func setUpUI() {
        self.contentView.addSubview(cardView)
        self.contentView.addSubview(hotelPicture)
        self.contentView.addSubview(hotelName)
        self.contentView.addSubview(hotelAdress)
        self.contentView.addSubview(hotelPrice)
        setUpConstraits()
    }
    
    func setUpConstraits() {
        cardView.snp.makeConstraints { (make) in
            make.centerX.equalToSuperview()
            make.centerY.equalToSuperview()
            make.width.equalTo(0.9 * self.contentView.bounds.size.width)
            make.height.equalTo(0.9 * self.contentView.bounds.size.height)
        }
        
        hotelPicture.snp.makeConstraints { (make) in
            make.top.equalTo(cardView.snp.top)
            make.centerX.equalToSuperview()
            make.width.equalTo(0.9 * self.contentView.bounds.size.width)
            make.height.equalTo(0.6 * self.contentView.bounds.size.height)
        }
        
        hotelName.snp.makeConstraints { (make) in
            make.top.equalTo(hotelPicture.snp.bottom).offset(10)
            make.width.equalTo(0.8 * self.contentView.bounds.size.width)
            make.left.equalTo(hotelPicture.snp.left).offset(10)
        }

        hotelAdress.snp.makeConstraints { (make) in
            make.top.equalTo(hotelName.snp.bottom).offset(10)
            make.width.equalTo(0.4 * self.contentView.bounds.size.width)
            make.left.equalTo(hotelPicture.snp.left).offset(10)
        }

        hotelPrice.snp.makeConstraints { (make) in
            make.top.equalTo(hotelName.snp.bottom).offset(10)
            make.width.equalTo(0.8 * self.contentView.bounds.size.width)
            make.right.equalTo(hotelPicture.snp.right).offset(-10)
        }
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        hotel = nil
        hotelName.text = nil
        hotelPrice.text = nil
        hotelAdress.text = nil
        hotelPicture.image = nil
    }
    
}
