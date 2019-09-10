//
//  TableViewCell.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 09/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation
import UIKit

class TableViewCell: UITableViewCell {
    
    enum CellType {
        case package
        case hotel
    }
    
    enum LoadingState {
        case notLoading
        case loading
        case loaded(UIImage)
    }
    
    var isFlagged: Bool = false
    var type:CellType = .hotel
    
    private let activityView = UIActivityIndicatorView(style: .gray)
    
    var loadingState: LoadingState = .notLoading {
        didSet {
            switch loadingState {
            case .notLoading:
                photo.image = nil
                activityView.stopAnimating()
            case .loading:
                photo.image = nil
                activityView.startAnimating()
            case let .loaded(img):
                photo.image = img
                photo.contentMode = .scaleAspectFill
                activityView.stopAnimating()
            }
        }
    }
    
    //outlets
    @IBOutlet weak var cellView: UIView!
    @IBOutlet weak var photo: UIImageView!
    @IBOutlet weak var favoriteButton: UIButton!
    @IBOutlet weak var nameLabel: UILabel!
    
    // stars - for hotels only
    @IBOutlet weak var star1: UIImageView!
    @IBOutlet weak var star2: UIImageView!
    @IBOutlet weak var star3: UIImageView!
    @IBOutlet weak var star4: UIImageView!
    @IBOutlet weak var star5: UIImageView!
    
    
    //outlets
    @IBOutlet weak var localLabel: UILabel!
    @IBOutlet weak var maxValorLabel: UILabel!
    //    @IBOutlet weak var minValorLabel: UILabel!
    
    
    //amenities - for hotels or atribuites - for packages
    @IBOutlet weak var amenity1: UILabel!
    @IBOutlet weak var amenity2: UILabel!
    @IBOutlet weak var amenity3: UILabel!
    
    
    override func layoutSubviews() {
        //Shadows
        cellView.layer.shadowOffset = CGSize(width: 0, height: 0)
        cellView.layer.shadowColor = UIColor.black.cgColor
        cellView.layer.shadowOpacity = 0.23
        cellView.layer.shadowRadius = 4
        cellView.layer.cornerRadius = 12
        
        // Favorite Button image
        let imageSelected = UIImage(named: "favoritoSelected")
        favoriteButton.setImage(imageSelected, for: .selected)
        
        let imageNormal = UIImage(named: "favoriteFlag")
        favoriteButton.setImage(imageNormal, for: .normal)
        
        for item in cellView.subviews {
            item.isUserInteractionEnabled = false
        }
        
        cellView.isUserInteractionEnabled = true
        self.photo.layer.cornerRadius = 10
        self.photo.clipsToBounds = true
        
    }
    
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
        switch type {
        case .hotel:
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
            if result.stars != nil {
                for i in 0...result.stars! - 1 {
                    stars[i]!.isHidden = false
                }
            }
            if !result.gallery.isEmpty {
                loadImage(from: result.gallery[0].url)
            }
        case .package:
            self.nameLabel.text = result.name
            self.nameLabel.textColor = .baseBlue
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
            if !result.gallery.isEmpty {
                loadImage(from: result.gallery[0].url)
            }
        }
        
        activityView.color = UIColor.baseBlue
        activityView.frame = CGRect(x: 0, y: 0, width: 150.0, height: 150.0)
        activityView.center = photo.center
        activityView.transform = CGAffineTransform(scaleX: 1.5, y: 1.5)
        self.cellView.addSubview(activityView)
        
    }
    
    
    
    func loadImage(from imageURL: String) {
        DispatchQueue.main.async {
            self.loadingState = .loading
            guard let url = URL(string: imageURL) else {
                debugPrint("error in image url", #function)
                return
            }
            guard let data = try? Data(contentsOf: url) else {
//                debugPrint("error getting data", #function, url)
                return
            }
            guard let img = UIImage(data: data) else {
                debugPrint("error in uiimage", #function)
                self.loadingState = .notLoading
                return
            }
            self.loadingState = .loaded(img)
        }
        
    }
    
    
    
    @IBAction func favoriteClicked(_ sender: Any) {
        isFlagged = !isFlagged
        switch isFlagged {
        case true:
            favoriteButton.isSelected = true
        case false:
            favoriteButton.isSelected = false
        }
    }
    
}
