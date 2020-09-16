//
//  HotelViewCell.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 04/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import UIKit
import Kingfisher

class HotelViewCell: UICollectionViewCell, NibLoadable {
    
    static let defaultHeight = CGFloat(327)
    
    @IBOutlet private var imageView: UIImageView!
    @IBOutlet private var citylabel: UILabel!
    @IBOutlet private var nameLabel: UILabel!
    @IBOutlet private var amenitiesLabel: UILabel!
    @IBOutlet private var starView: UIImageView!
    @IBOutlet private var originalAmountPerDayLabel: UILabel!
    @IBOutlet private var amountPerDayLabel: UILabel!
    @IBOutlet private var conditionLabel: UILabel!
    @IBOutlet private var cancellationLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        prepareContentView()
        prepareImageView()
        prepareCitylabel()
        prepareNameLabel()
        prepareAmenitieslabel()
        prepareStarView()
        prepareOriginalAmountPerDayLabel()
        prepareAmountPerDayLabel()
        prepareConditionLabel()
        prepareCancellationLabel()
    }
    
    private func prepareContentView() {
        contentView.backgroundColor = UIColor.white
        contentView.layer.cornerRadius = 8.0
        contentView.clipsToBounds = true
    }
    
    private func prepareImageView() {
        imageView.contentMode = .scaleAspectFill
    }
    
    private func prepareCitylabel() {
        citylabel.font = UIFont.systemFont(ofSize: 12, weight: .bold)
        citylabel.lineBreakMode = .byTruncatingTail
    }
    
    private func prepareAmenitieslabel() {
        amenitiesLabel.font = UIFont.systemFont(ofSize: 12, weight: .medium)
    }
    
    private func prepareStarView() {
        starView.layer.cornerRadius = 8.0
        starView.clipsToBounds = true
    }
    
    private func prepareNameLabel() {
        nameLabel.font = UIFont.systemFont(ofSize: 12, weight: .regular)
        nameLabel.lineBreakMode = .byTruncatingTail
    }
    
    private func prepareOriginalAmountPerDayLabel() {
        originalAmountPerDayLabel.font = UIFont.systemFont(ofSize: 10, weight: .medium)
        originalAmountPerDayLabel.textAlignment = .right
        originalAmountPerDayLabel.attributedText = nil
    }
    
    private func prepareAmountPerDayLabel() {
        amountPerDayLabel.font = UIFont.systemFont(ofSize: 14, weight: .bold)
        amountPerDayLabel.textColor = UIColor.orange
        amountPerDayLabel.textAlignment = .right
    }
    
    private func prepareConditionLabel() {
        conditionLabel.font = UIFont.systemFont(ofSize: 10, weight: .light)
        conditionLabel.textAlignment = .right
    }
    
    private func prepareCancellationLabel() {
        cancellationLabel.font = UIFont.systemFont(ofSize: 10, weight: .regular)
        cancellationLabel.textColor = UIColor.green

        cancellationLabel.textAlignment = .right
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        imageView.image = nil
        citylabel.text = " "
        nameLabel.text = " "
        originalAmountPerDayLabel.text = " "
        amountPerDayLabel.text = " "
        conditionLabel.text = " "
        cancellationLabel.text = " "
    }
    
    func setup(hotel: HotelDisplay) {
        imageView.kf.setImage(with: hotel.image)
        citylabel.text = hotel.address
        nameLabel.text = hotel.name
        amenitiesLabel.text = hotel.amenities.reduce("", { $0 == "" ? $1 : $0 + "\n" + $1 })
        starView.image = hotel.ratingImage
        originalAmountPerDayLabel.attributedText = hotel.originalAmountPerDay
        amountPerDayLabel.text = hotel.amountPerDay
        conditionLabel.text = hotel.condition
        cancellationLabel.text = hotel.cancelationFree
    }
    
}
