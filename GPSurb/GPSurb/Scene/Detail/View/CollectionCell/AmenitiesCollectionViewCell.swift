//
//  AmenitiesCollectionViewCell.swift
//  GPSurb
//
//  Created by Gilson Santos on 21/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import UIKit

class AmenitiesCollectionViewCell: UICollectionViewCell {

    @IBOutlet weak var viewTitle: UIView!
    @IBOutlet weak var lblTitle: UILabel!
    @IBOutlet weak var lblDescription: UILabel!
    
}

extension AmenitiesCollectionViewCell {
    override func awakeFromNib() {
           super.awakeFromNib()
//            self.setupView()
       }
}

extension AmenitiesCollectionViewCell {
    private func setupView() {
        self.viewTitle.layer.masksToBounds = false
        self.viewTitle.layer.borderWidth = 2.0
        self.viewTitle.layer.borderColor = UIColor.label.cgColor
        self.viewTitle.layer.cornerRadius = self.viewTitle.bounds.height / 2
        self.viewTitle.layer.shadowColor = UIColor.black.cgColor
        self.viewTitle.layer.shadowOpacity = 0.4
        self.viewTitle.layer.shadowOffset = .zero
    }
    
    public func setupCell(description: String) {
        if let title = description.first {
            self.lblTitle.text = String(title)
        }
        self.lblDescription.text = description
    }
}
