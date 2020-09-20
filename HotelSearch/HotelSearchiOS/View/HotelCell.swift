//
//  HotelCell.swift
//  HotelSearchiOS
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import UIKit

import HotelSearch

final public class HotelCell: UITableViewCell {

    // MARK: - IBOutlets
    
    @IBOutlet private(set) public weak var imageContainer: UIView!
    @IBOutlet private(set) public weak var imvBackground: UIImageView!
    @IBOutlet private(set) public weak var viewBlur: UIView!
    @IBOutlet private(set) public weak var stackView: UIStackView!
    @IBOutlet private(set) public weak var lblName: UILabel!
    @IBOutlet private(set) public weak var lblLocation: UILabel!
    @IBOutlet private(set) public weak var lblAmenities: UILabel!
    @IBOutlet private(set) public weak var lblPrice: UILabel!

    // MARK: - Properties
    
    var imageData: Data? {
        didSet {
            let isDataNil = self.imageData == nil
            self.imageContainer.isShimmering = isDataNil
            self.imvBackground.setImageAnimated(isDataNil ? nil : UIImage(data: self.imageData!))
        }
    }
    
    var viewModel: HotelViewModel? {
        didSet {
            self.lblName.text = self.viewModel?.name
            self.lblLocation.text = self.viewModel?.location
            self.lblAmenities.text = self.viewModel?.amenities
            self.lblPrice.text = self.viewModel?.price
        }
    }

    // MARK: - Life Cycle
    
    public override func awakeFromNib() {
        super.awakeFromNib()
        self.setupUI()
    }
    
}

private extension HotelCell {

    // MARK: - Setup
    
    func setupUI() {
        [self.imvBackground, self.viewBlur].forEach { $0.layer.cornerRadius = 6 }
    }
    
}
