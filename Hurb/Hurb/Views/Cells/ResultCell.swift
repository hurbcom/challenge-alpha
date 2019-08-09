//
//  ResultCell.swift
//  Hurb
//
//  Created by Alexandre Papanis on 08/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import UIKit
import SDWebImage

class ResultCell: UITableViewCell {

    //MARK: - Outlets
    @IBOutlet weak var imgPhoto: UIImageView!
    @IBOutlet weak var lbCity: UILabel!
    @IBOutlet weak var lbName: UILabel!
    @IBOutlet weak var imgStar1: UIImageView!
    @IBOutlet weak var imgStar2: UIImageView!
    @IBOutlet weak var imgStar3: UIImageView!
    @IBOutlet weak var imgStar4: UIImageView!
    @IBOutlet weak var imgStar5: UIImageView!
    
    @IBOutlet weak var vwFreeCancellation: UIView!
    @IBOutlet weak var vwAmenities: UIView!
    @IBOutlet weak var lbAmenitiesTotal: UILabel!
    @IBOutlet weak var lbAmenities: UILabel!
    
    @IBOutlet weak var lbPriceDescription: UILabel!
    @IBOutlet weak var lbPrice: UILabel!
    
    var hotel: HotelViewModel! {
        didSet {
            updateUI()
        }
    }
    
    //MARK: - Configure UI
    fileprivate func updateUI() {
        lbCity.text = hotel.cityState
        lbName.text = hotel.name
        imgPhoto.sd_setImage(with: hotel.imageUrl, placeholderImage: UIImage(named: "placeholderImage"), options: [.continueInBackground], completed: nil)
        
        setStars()
        vwFreeCancellation.isHidden = !hotel.isFreeCancellation
        
        setAmenities()
        lbPriceDescription.text = hotel.priceDescription
        lbPrice.text = hotel.price
        
    }
    
    fileprivate func setStars(){
        let imgStars = [imgStar1, imgStar2, imgStar3, imgStar4, imgStar5]
        
        if let stars = hotel.stars {
            for imgStar in imgStars {
                if imgStar!.tag < stars {
                    imgStar?.image = UIImage(named: "star")
                } else {
                    imgStar?.image = UIImage()
                }
            }
        }
    }
    
    fileprivate func setAmenities() {
        vwAmenities.isHidden = hotel.amenities.count == 0
        lbAmenitiesTotal.text = "\(hotel.amenities.count) Amenidades"
        lbAmenities.text = hotel.amenities.compactMap({"◆ \($0.name ?? "")"})[0..<3].joined(separator: "\n")
    }
    
}
