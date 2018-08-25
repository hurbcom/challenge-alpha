//
//  HotelTableViewCell.swift
//  Hotels
//
//  Created by Adolfho Athyla on 25/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import UIKit

class HotelTableViewCell: UITableViewCell {

    @IBOutlet var background: UIView!
    @IBOutlet var hotelImage: UIImageView!
    @IBOutlet var hotelName: UILabel!
    @IBOutlet var hotelAddress: UILabel!
    @IBOutlet var hotelPrice: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        background.layer.shadowColor = UIColor.gray.cgColor
        background.layer.shadowOpacity = 0.5
        background.layer.shadowOffset = CGSize.zero
        background.layer.shadowRadius = 4
        background.layer.shadowPath = UIBezierPath(rect: background.bounds).cgPath
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
