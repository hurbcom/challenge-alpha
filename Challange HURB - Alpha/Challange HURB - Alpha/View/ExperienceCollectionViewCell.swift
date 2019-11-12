//
//  experienceCollectionViewCell.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 07/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import UIKit
import Kingfisher
import SnapKit
import os.log

/// Cell that presents the experiece data
class ExperienceCollectionViewCell: UICollectionViewCell {
    
    // MARK: - Properties
    var experience: Experience? {
        didSet {
            guard let experience = self.experience else { return }//fatalError("experienceCollectionViewCell - No experience was set")}
            experienceName.text = experience.name
            experienceAdress.text = "\(String(describing: experience.address.city)) \(String(describing: experience.address.state))"
            experiencePrice.text = "\(experience.price.getPriceSymbol()) \(experience.price.amount)"
            
            let imageURL: URL
            if experience.image != nil {
                imageURL = experience.image!
            } else {
                imageURL = experience.gallery[0].url
            }
            
            /*
                This isn`t the best practice, but some urls that come from the api do not conform
                with the HTTPS network protocol and to make apps safer Apple demands that all queries
                made by apps in production use HTTPS protocol.
            */
            guard var urlComps = URLComponents(url: imageURL, resolvingAgainstBaseURL: false) else {
                os_log(.fault, "URLComponents could not transform URL")
                return
            }
            urlComps.scheme = "https"
            experiencePicture.kf.setImage(with: urlComps.url)
            experienceAmenities.text = experience.amenities[0].name
        }
    }
    
    private let cardView: UIView = {
        let view = UIView(frame: .zero)
        view.backgroundColor = .clear
        view.layer.borderColor = UIColor.black.cgColor
        view.layer.borderWidth = 0.5
        view.layer.cornerRadius = 12
        return view
    }()
    
    private let experiencePicture: UIImageView = {
        let view = UIImageView(frame: .zero)
        view.contentMode = .scaleAspectFill
        view.clipsToBounds = true
        view.backgroundColor = .gray
        return view
    }()
    
    private let experienceName: UILabel = {
        let label = UILabel(frame: .zero)
        label.font = .systemFont(ofSize: 15, weight: .bold)
        label.textColor = .black
        label.textAlignment = .left
        label.numberOfLines = 2
        return label
    }()
    
    private let experienceAdress: UILabel = {
        let label = UILabel(frame: .zero)
        label.font = .systemFont(ofSize: 15, weight: .regular)
        label.textColor = .black
        label.textAlignment = .left
        label.numberOfLines = 2
        return label
    }()
    
    private let experiencePrice: UILabel = {
        let label = UILabel(frame: .zero)
        label.font = .systemFont(ofSize: 20 , weight: .semibold)
        label.textColor = #colorLiteral(red: 0.9994023442, green: 0.4260467291, blue: 0.008425070904, alpha: 1)
        label.textAlignment = .right
        label.numberOfLines = 1
        return label
    }()
    
    private let experienceAmenities: UILabel = {
        let label = UILabel(frame: .zero)
        label.font = .systemFont(ofSize: 20 , weight: .bold)
        label.textColor = #colorLiteral(red: 0.00358197093, green: 0.2135199308, blue: 0.4757859707, alpha: 1)
        label.textAlignment = .left
        label.numberOfLines = 2
        return label
    }()
    
    private let experieceAmenitiesBackground: UIView = {
        let view = UIView(frame: .zero)
        view.backgroundColor = .white
        view.alpha = 0.8
        return view
    }()
    
    // MARK: - View Methods
    
    override func layoutSubviews() {
        super.layoutSubviews()
        experiencePicture.roundCorners(corners: [.topLeft, .topRight],
                                       radius: 12)
    }
    
    // MARK: - Methods
    
    func setUpUI() {
        super.layoutSubviews()
        self.contentView.addSubview(cardView)
        self.contentView.addSubview(experiencePicture)
        self.contentView.addSubview(experienceName)
        self.contentView.addSubview(experienceAdress)
        self.contentView.addSubview(experiencePrice)
        self.contentView.addSubview(experieceAmenitiesBackground)
        self.contentView.addSubview(experienceAmenities)
        setUpConstraits()
    }
    
    func setUpConstraits() {
        cardView.snp.makeConstraints { (make) in
            make.centerX.equalToSuperview()
            make.centerY.equalToSuperview()
            make.width.equalTo(0.9 * self.contentView.bounds.size.width)
            make.height.equalTo(0.9 * self.contentView.bounds.size.height)
        }
        
        experiencePicture.snp.makeConstraints { (make) in
            make.top.equalTo(cardView.snp.top)
            make.centerX.equalToSuperview()
            make.width.equalTo(0.9 * self.contentView.bounds.size.width)
            make.height.equalTo(0.6 * self.contentView.bounds.size.height)
        }
        
        experienceAmenities.snp.makeConstraints { (make) in
            make.bottom.equalTo(experiencePicture.snp.bottom).offset(-10)
            make.width.equalTo(0.8 * self.contentView.bounds.size.width)
            make.left.equalTo(experiencePicture.snp.left).offset(10)
        }
        
        experieceAmenitiesBackground.snp.makeConstraints { (make) in
            make.bottom.equalTo(experiencePicture.snp.bottom)
            make.top.equalTo(experienceAmenities.snp.top).offset(-10)
            make.width.equalTo(experiencePicture.snp.width)
            make.centerX.equalTo(experiencePicture.snp.centerX)
        }
        
        experienceName.snp.makeConstraints { (make) in
            make.top.equalTo(experiencePicture.snp.bottom).offset(10)
            make.width.equalTo(0.8 * self.contentView.bounds.size.width)
            make.left.equalTo(experiencePicture.snp.left).offset(10)
        }

        experienceAdress.snp.makeConstraints { (make) in
            make.top.equalTo(experienceName.snp.bottom).offset(10)
            make.width.equalTo(0.4 * self.contentView.bounds.size.width)
            make.left.equalTo(experiencePicture.snp.left).offset(10)
        }

        experiencePrice.snp.makeConstraints { (make) in
            make.top.equalTo(experienceName.snp.bottom).offset(10)
            make.width.equalTo(0.8 * self.contentView.bounds.size.width)
            make.right.equalTo(experiencePicture.snp.right).offset(-10)
        }
    }
    
    // MARK: - Cell lifecycle
    override func prepareForReuse() {
        super.prepareForReuse()
        experience = nil
        experienceName.text = nil
        experiencePrice.text = nil
        experienceAdress.text = nil
        experiencePicture.image = nil
        experienceAmenities.text = nil
    }
}
