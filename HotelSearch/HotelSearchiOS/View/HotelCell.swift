//
//  HotelCell.swift
//  HotelSearchiOS
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import UIKit

import HotelSearch

class HotelCell: UITableViewCell {

    // MARK: - IBOutlets
    
    @IBOutlet weak var imvBackground: UIImageView!
    @IBOutlet weak var viewBlur: UIView!
    @IBOutlet weak var stackView: UIStackView!
    @IBOutlet weak var lblName: UILabel!
    @IBOutlet weak var lblLocation: UILabel!
    @IBOutlet weak var lblAmenities: UILabel!
    @IBOutlet weak var lblPrice: UILabel!

    // MARK: - Properties
    
    var viewModel: HotelViewModel? {
        didSet {
            self.lblName.text = self.viewModel?.name
            self.lblLocation.text = self.viewModel?.location
            self.lblAmenities.text = self.viewModel?.amenities
            self.lblPrice.text = self.viewModel?.price
        }
    }

    // MARK: - Life Cycle
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.setupUI()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
}

private extension HotelCell {

    // MARK: - Setup
    
    func setupUI() {
        [self.imvBackground, self.viewBlur].forEach { $0.layer.cornerRadius = 6 }
    }
    
}
