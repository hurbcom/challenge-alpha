//
//  BestDestinationsCollectionViewCell.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import UIKit

class BestDestinationsCollectionViewCell: UICollectionViewCell {
    // MARK: - VARIABLES -
    @IBOutlet weak var containerView: UIView!
    @IBOutlet weak var cardView: UIView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var destinationImage: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.setupShadow()
    }
}

// MARK: - AUX METHODS  -
extension BestDestinationsCollectionViewCell {
    func setupItem(viewData: DestinatonViewData) {
        self.nameLabel.text = viewData.name
        self.destinationImage.image = viewData.image
    }
    private func setupShadow() {
        self.cardView.layer.masksToBounds = false
        self.cardView.layer.cornerRadius = 16
        self.destinationImage.layer.cornerRadius = 16
        self.cardView.layer.shadowColor = UIColor.black.cgColor
        self.cardView.layer.shadowOpacity = 0.4
        self.cardView.layer.shadowOffset = .zero
        self.cardView.layer.shadowRadius = 8
    }
}
