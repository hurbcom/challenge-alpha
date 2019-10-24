//
//  HotelTableViewCell.swift
//  DesafioHurb
//
//  Created by Filipo Negrao on 22/10/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import UIKit
import ImageLoader

class HotelTableViewCell: UITableViewCell {
    
    @IBOutlet weak var hotelImageView: UIImageView!
    @IBOutlet weak var labelName: UILabel!
    @IBOutlet weak var imageStars: UIImageView!
    @IBOutlet weak var labelPrice: UILabel!
    @IBOutlet weak var labelAddress: UILabel!
    @IBOutlet weak var labelAmenities: UILabel!
    
    @IBOutlet weak var content: UIView!
    
    private var imageUrl: String?
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Visual
        self.backgroundColor = UIColor.white
        self.labelName.textColor = UIColor.black
        self.labelName.numberOfLines = 2
        self.labelName.lineBreakMode = .byWordWrapping
        self.labelPrice.textColor = red
        
//        self.hotelImageView.layer.cornerRadius = 10
        self.hotelImageView.backgroundColor = UIColor.lightGray
        self.hotelImageView.contentMode = .scaleAspectFill
        
        self.labelAddress.textColor = UIColor.black
        self.labelAmenities.textColor = UIColor.black
        
        self.backgroundColor = UIColor.clear
        self.content.backgroundColor = UIColor.white
        self.content.clipsToBounds = true
        self.content.layer.cornerRadius = 5
        
        self.selectionStyle = .none
     
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    
    
    func configCell(hotel: Hotel) {
        self.labelName.text = hotel.name
        self.labelPrice.text = "R$ \(hotel.price)"
        // Verifica se precisa carregar a imagem
        let imageUrl =  hotel.imageUrl
        if imageUrl == "" {
            self.hotelImageView.image = nil
            self.imageUrl = nil
        }
        self.labelAddress.text = "\(hotel.city), \(hotel.state)"
        self.labelAmenities.text = hotel.getThreeAmenitiesString()
        
        if self.imageUrl == nil || self.imageUrl != imageUrl {
            self.hotelImageView.image = nil
            self.imageUrl = imageUrl
            // Carrega imagem
            if let url = URL.init(string: imageUrl) {
                self.hotelImageView.load.request(with: url)
            }
        }
        // Estrelas
        if hotel.isPackage {
            self.imageStars.image = nil
        } else {
            switch hotel.stars {
            case 1:
                self.imageStars.image = UIImage(named: "stars1")
            case 2:
                self.imageStars.image = UIImage(named: "stars2")
            case 3:
                self.imageStars.image = UIImage(named: "stars3")
            case 4:
                self.imageStars.image = UIImage(named: "stars4")
            case 5:
                self.imageStars.image = UIImage(named: "stars5")
            default:
                self.imageStars.image = UIImage(named: "stars1")
            }
        }

    }
    
}
