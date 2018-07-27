//
//  HotelItemViewCell.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 26/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import UIKit
import AlamofireImage

class HotelItemViewCell: UITableViewCell {
    
    @IBOutlet fileprivate weak var name: UILabel!
    @IBOutlet fileprivate weak var price: UILabel!
    @IBOutlet fileprivate weak var state: UILabel!
    @IBOutlet fileprivate weak var city: UILabel!
    @IBOutlet fileprivate weak var imageHotel: UIImageView!
    

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    
    func fillOutlets(hotel: HotelListViewModel.HotelItemViewModel) {
        
        self.city.text = hotel.city
        self.name.text = hotel.name
        self.state.text = hotel.state
        self.price.text = hotel.price?.formatedAsCurreny()
        
//            Double(hotel.price!)
        
        if let imageString = hotel.image, let imageURL = URL(string: imageString) {
            imageHotel!.af_setImage(withURL: imageURL)
        }
    }
    
}
