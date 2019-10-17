//
//  ElementTableViewCell.swift
//  GPSurb
//
//  Created by Gilson Santos on 17/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import UIKit
import Kingfisher

class ElementTableViewCell: UITableViewCell {
    
    @IBOutlet weak var cardView: UIView!
    @IBOutlet weak var cardImage: UIImageView!
    @IBOutlet weak var showButton: UIButton!
    @IBOutlet weak var destinationName: UILabel!
    @IBOutlet weak var offerName: UILabel!
    @IBOutlet weak var firstBenefitName: UILabel!
    @IBOutlet weak var secondBenefitName: UILabel!
    @IBOutlet weak var thirdBenefitName: UILabel!
    @IBOutlet weak var oldPrice: UILabel!
    @IBOutlet weak var newPrice: UILabel!
    @IBOutlet weak var heightImage: NSLayoutConstraint!

    override func awakeFromNib() {
        super.awakeFromNib()
        self.setupView()
    }
    
    override func layoutIfNeeded() {
        super.layoutIfNeeded()
        self.heightImage.constant = self.cardView.bounds.height
    }
}

// MARK: - AUX METHODS  -
extension ElementTableViewCell {
    public func prepareCell(viewData: ResultViewData) {
        self.destinationName.text = viewData.destinationName
        self.offerName.text = viewData.offerName
        //self.firstBenefitName.text = viewData.amenities[0]
        //self.secondBenefitName.text = viewData.amenities[1]
        //self.thirdBenefitName.text = viewData.amenities[2]
        self.oldPrice.text = viewData.oldPrice
        self.newPrice.text = viewData.newPrice
        let url = URL(string: viewData.image)
        self.cardImage.kf.indicatorType = .activity
        self.cardImage.kf.setImage(with: url)
    }
    
    func setupView() {
        self.cardView.layer.masksToBounds = false
        self.cardView.layer.cornerRadius = 6
        self.cardImage.layer.cornerRadius = 6
        self.showButton.layer.cornerRadius = 6
        self.cardImage.layer.maskedCorners = [.layerMinXMaxYCorner, .layerMinXMinYCorner]
        self.cardView.layer.shadowColor = UIColor.black.cgColor
        self.cardView.layer.shadowOpacity = 0.4
        self.cardView.layer.shadowOffset = .zero
        self.cardView.layer.shadowRadius = 4
        self.layoutIfNeeded()
    }
}
