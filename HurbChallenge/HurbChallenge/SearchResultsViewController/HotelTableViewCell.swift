//
//  HotelTableViewCell.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import UIKit

class HotelTableViewCell: UITableViewCell {

    static let identifier = "HotelCell"
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        textLabel?.text = nil
    }
    
    func configure(with hotel: SearchResultElement?) {
        textLabel?.text = hotel?.name
    }

}
