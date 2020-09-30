//
//  HomeCollectionViewCell.swift
//  Challenge-ios
//
//  Created by Andre Dias on 29/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//


import UIKit
import Kingfisher

class HomeCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet weak var hotelImage: UIImageView!
    @IBOutlet weak var hotelName: UILabel!
    var homeCollectionViewCellViewModel: HomeCollectionCellViewModel! {
        didSet {
            self.setup(hotelViewModel: homeCollectionViewCellViewModel)
        }
    }

    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
 
    func setup(hotelViewModel: HomeCollectionCellViewModel) {
//        self.hotelImage.kf.setImage(with: hotelViewModel.gameImageURL)
//        self.hotelName.text = hotelViewModel.gameName
       
    }
    
}


