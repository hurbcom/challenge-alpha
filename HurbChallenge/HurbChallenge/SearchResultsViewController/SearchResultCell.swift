//
//  SearchResultCell.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import UIKit

class ShadowView: UIView {
    override var bounds: CGRect {
        didSet {
            setupShadow()
        }
    }
    
    private func setupShadow() {
        self.layer.cornerRadius = 5
        self.layer.shadowOffset = CGSize(width: 1, height: 1)
        self.layer.shadowRadius = 2
        self.layer.shadowOpacity = 0.4
        self.layer.shadowPath = UIBezierPath(roundedRect: self.bounds, byRoundingCorners: .allCorners, cornerRadii: CGSize(width: 5, height: 5)).cgPath
        self.layer.shouldRasterize = true
        self.layer.rasterizationScale = UIScreen.main.scale
    }
}

class SearchResultCell: UITableViewCell {

    static let identifier = "SearchResultCell"
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var cityStateLabel: UILabel!
    @IBOutlet weak var firstAmenityLabel: UILabel!
    @IBOutlet weak var secondAmenityLabel: UILabel!
    @IBOutlet weak var thirdAmenityLabel: UILabel!
    @IBOutlet weak var priceLabel: UILabel!
    @IBOutlet weak var img: UIImageView!
    
    override func prepareForReuse() {
        super.prepareForReuse()
    }
    
    func configure(with result: SearchResultElement?) {
        nameLabel.text = result?.name
        cityStateLabel.text = [result?.address.city, result?.address.state].compactMap{$0}.joined(separator: ", ")
//        firstAmenityLabel.text = result.ame
    }

}
