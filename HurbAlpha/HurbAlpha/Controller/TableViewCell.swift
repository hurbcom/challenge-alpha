//
//  TableViewCell.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 09/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation
import UIKit

// MARK: - Declaration

class TableViewCell: UITableViewCell {
    
    // MARK: - Outlets

    @IBOutlet weak var cellView: UIView!
    @IBOutlet weak var photo: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    
    // stars - for hotels only
    @IBOutlet weak var star1: UIImageView!
    @IBOutlet weak var star2: UIImageView!
    @IBOutlet weak var star3: UIImageView!
    @IBOutlet weak var star4: UIImageView!
    @IBOutlet weak var star5: UIImageView!
    @IBOutlet weak var localLabel: UILabel!
    @IBOutlet weak var maxValorLabel: UILabel!

    // Amenities - for hotels or atribuites - for packages
    @IBOutlet weak var amenity1: UILabel!
    @IBOutlet weak var amenity2: UILabel!
    @IBOutlet weak var amenity3: UILabel!
    
    // MARK: - Enum for cell type declaration
    enum CellType {
        case package
        case hotel
    }
    
    // Bool to check if the hotel is favorited
    var isFlagged: Bool = false
    
    // The cell type default initialization
    var type:CellType = .hotel
    
    // The current hotel
    var myHotel:Result?
    
    // The images manager instance
    let imageManager = ImagesManager.instance
    
    // The ui set ups in subviews
    override func layoutSubviews() {
        //Shadows
        cellView.layer.shadowOffset = CGSize(width: 0, height: 0)
        cellView.layer.shadowColor = UIColor.black.cgColor
        cellView.layer.shadowOpacity = 0.23
        cellView.layer.shadowRadius = 4
        cellView.layer.cornerRadius = 12
        cellView.isUserInteractionEnabled = true
        self.photo.layer.cornerRadius = 10
        self.photo.clipsToBounds = true
    }
    
    /**
     Loads ui elements using the information of the desired hotel.
     - Parameters:
        - result: The result to be shown.
     */
    func loadInfo(on result:Result) {
        if result.isHotel == nil {
            self.type = .package
        }
        let currency = String(result.price.currency ?? "BRL") + " "
        var cents = String(result.price.amount).split(separator: ".")[1]
        let value = String(result.price.amount).split(separator: ".")[0]
        if cents.count == 1 {
            cents = cents + "0"
        }
        self.nameLabel.text = result.name
        self.nameLabel.textColor = .baseBlue
        self.localLabel.text = result.address.city + " | " + result.address.state
        self.maxValorLabel.text = currency + value + "," + cents
        let amenitiesList = result.amenities
        switch amenitiesList.count {
        case 0:
            self.amenity1.isHidden = true
            self.amenity2.isHidden = true
            self.amenity3.isHidden = true
        case 1:
            self.amenity1.text = amenitiesList[0].name
            self.amenity2.isHidden = true
            self.amenity3.isHidden = true
        case 2:
            self.amenity1.text = amenitiesList[0].name
            self.amenity2.text = amenitiesList[1].name
            self.amenity3.isHidden = true
        default:
            self.amenity1.text = amenitiesList[0].name
            self.amenity2.text = amenitiesList[1].name
            self.amenity3.text = amenitiesList[2].name
        }
        
        self.amenity1.layer.cornerRadius = 10
        self.amenity1.clipsToBounds = true
        
        self.amenity2.layer.cornerRadius = 10
        self.amenity2.clipsToBounds = true
        
        self.amenity3.layer.cornerRadius = 10
        self.amenity3.clipsToBounds = true
        
        let stars = [self.star1, self.star2, self.star3, self.star4, self.star5]
        if !result.gallery.isEmpty {
            loadImage(from: result.gallery)
        }
        switch type {
        case .hotel:
            for star in stars {
                star?.isHidden = true
            }
            if result.stars != nil {
                for i in 0...result.stars! - 1 {
                    stars[i]!.isHidden = false
                }
            }
        case .package:
            for star in stars {
                star?.isHidden = true
            }
        }
        imageManager.activityView.color = UIColor.baseBlue
        imageManager.activityView.frame = CGRect(x: 0, y: 0, width: 150.0, height: 150.0)
        imageManager.activityView.center = photo.center
        imageManager.activityView.transform = CGAffineTransform(scaleX: 1.5, y: 1.5)
        self.cellView.addSubview(imageManager.activityView)
        
    }
    
    
    /**
     Loads one of the images in the result gallery.
     - Parameters:
        - imageURLGallery: The image gallery of the hotel.
     */
    func loadImage(from imageURLGallery: [Gallery]) {
        imageManager.onImageView = self.photo
        imageManager.tryConvertionFromURL(from: imageURLGallery[0].url)
    }
    
}
