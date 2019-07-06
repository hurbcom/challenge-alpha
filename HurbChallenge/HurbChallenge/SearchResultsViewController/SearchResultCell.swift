//
//  SearchResultCell.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import UIKit
import SDWebImage

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
    @IBOutlet weak var amenitiesLabel: UILabel!
    @IBOutlet weak var priceLabel: UILabel!
    @IBOutlet weak var priceBorderView: UIView!
    @IBOutlet weak var img: UIImageView!
    
    lazy var formatter: NumberFormatter = {
        let formatter = NumberFormatter()
        formatter.locale = Locale(identifier: "pt_BR")
        formatter.numberStyle = .currency
        return formatter
    }()
    
    override func prepareForReuse() {
        super.prepareForReuse()
        nameLabel.text = nil
        cityStateLabel.text = nil
        amenitiesLabel.text = nil
        priceLabel.text = nil
        priceBorderView.isHidden = true
        img.alpha = 1.0
        img.sd_cancelCurrentImageLoad()
    }
    
    func configure(with result: SearchResultElement?) {
        nameLabel.text = result?.name
        cityStateLabel.text = [result?.address?.city, result?.address?.state].compactMap{$0}.joined(separator: ", ")
        if let amenities = result?.amenities {
            if amenities.count >= 3 {
                amenitiesLabel.text = result?.amenities?.compactMap({$0.name})[0..<3].joined(separator: ", ")
            } else {
                amenitiesLabel.text = result?.amenities?.compactMap({$0.name}).joined(separator: ", ")
            }
        }
        if let price = result?.price?.currentPrice, let priceString = formatter.string(from: price as NSNumber) {
            priceLabel.text = priceString
            priceBorderView.isHidden = false
        } else if let price = result?.price?.amount, let priceString = formatter.string(from: price as NSNumber) {
            priceLabel.text = priceString
            priceBorderView.isHidden = false
        }
        
        if let imgUrlString = result?.gallery?.first?.url {
            img.sd_setImage(with: URL(string: imgUrlString)) { (_, _, cache, _) in
                if cache == .none {
                    self.img.alpha = 0
                    UIView.animate(withDuration: 0.45, animations: {self.img.alpha = 1.0})
                }
            }
        }
    }

}
