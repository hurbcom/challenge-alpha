//
//  SearchResultCell.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import UIKit
import SDWebImage

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
        img.sd_cancelCurrentImageLoad()
        img.alpha = 1.0
    }
    
    func configure(with result: SearchResultElement?) {
        setNameLabel(result?.name)
        setCityLabel(result?.address)
        setAmenitiesLabel(result?.amenities)
        setPriceLabel(result?.price)
        loadImage(result?.gallery)
    }

}

// MARK - Métodos Privados

fileprivate extension SearchResultCell {
    
    func setNameLabel(_ name: String?) {
        nameLabel.text = name
    }
    
    func setCityLabel(_ address: Address?) {
        cityStateLabel.text = [address?.city, address?.state].compactMap{$0}.joined(separator: ", ")
    }
    
    func setAmenitiesLabel(_ amenities: [Amenity]?) {
        if let amenities = amenities {
            if amenities.count >= 3 {
                amenitiesLabel.text = amenities.compactMap({$0.name})[0..<3].joined(separator: ", ")
            } else {
                amenitiesLabel.text = amenities.compactMap({$0.name}).joined(separator: ", ")
            }
        }
    }
    
    func setPriceLabel(_ price: Price?) {
        if let value = price?.currentPrice, let priceString = formatter.string(from: value as NSNumber) {
            priceLabel.text = priceString
            priceBorderView.isHidden = false
        } else if let value = price?.amount, let priceString = formatter.string(from: value as NSNumber) {
            priceLabel.text = priceString
            priceBorderView.isHidden = false
        }
    }
    
    func loadImage(_ gallery: [ImageElement]?) {
        if let imgUrlString = gallery?.first?.url {
            img.sd_setImage(with: URL(string: imgUrlString)) { (_, _, cache, _) in
                if cache == .none {
                    self.img.alpha = 0
                    UIView.animate(withDuration: 0.45, animations: {self.img.alpha = 1.0})
                }
            }
        }
    }
}
