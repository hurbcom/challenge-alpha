//
//  ElementTableViewCell.swift
//  GPSurb
//
//  Created by Gilson Santos on 17/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import UIKit

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
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

    }

}
