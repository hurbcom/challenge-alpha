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

/// Cell that presents the experiece data
class ExperienceCollectionViewCell: UICollectionViewCell {
    
    // MARK: - Properties
    var experience: Experience? {
        didSet {
            guard let experience = self.experience else { return }//fatalError("experienceCollectionViewCell - No experience was set")}
            experienceName.text = experience.name
            experienceAdress.text = "\(String(describing: experience.address.city)) \(String(describing: experience.address.state))"
            experiencePrice.text = "\(experience.price.getPriceSymbol()) \(experience.price.amount)"
            
            guard let imageURL = experience.image else { return }
            guard var urlComps = URLComponents(url: imageURL, resolvingAgainstBaseURL: false) else {
                debugPrint("URLComponents could not transform URL")
                return
            }
            urlComps.scheme = "https"
            experiencePicture.kf.setImage(with: urlComps.url)
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
    
    let experiencePicture: UIImageView = {
        let view = UIImageView(frame: .zero)
        view.contentMode = .scaleAspectFill
        view.clipsToBounds = true
        view.layer.cornerRadius = 12
        view.backgroundColor = .gray
        view.clipsToBounds = true
        return view
    }()
    
    let experienceName: UILabel = {
        let label = UILabel(frame: .zero)
        label.font = .systemFont(ofSize: 15, weight: .bold)
        label.textColor = .black
        label.textAlignment = .left
        label.numberOfLines = 2
        return label
    }()
    
    let experienceAdress: UILabel = {
        let label = UILabel(frame: .zero)
        label.font = .systemFont(ofSize: 15, weight: .regular)
        label.textColor = .black
        label.textAlignment = .left
        label.numberOfLines = 2
        return label
    }()
    
    let experiencePrice: UILabel = {
        let label = UILabel(frame: .zero)
        label.font = .systemFont(ofSize: 20 , weight: .semibold)
        label.textColor = #colorLiteral(red: 0.9994023442, green: 0.4260467291, blue: 0.008425070904, alpha: 1)
        label.textAlignment = .right
        label.numberOfLines = 1
        return label
    }()
    
    // MARK: - Methods
    
    func setUpUI() {
        self.contentView.addSubview(cardView)
        self.contentView.addSubview(experiencePicture)
        self.contentView.addSubview(experienceName)
        self.contentView.addSubview(experienceAdress)
        self.contentView.addSubview(experiencePrice)
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
    }
}
