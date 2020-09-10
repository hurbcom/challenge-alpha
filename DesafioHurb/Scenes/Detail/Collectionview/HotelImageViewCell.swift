//
//  HotelImageViewCell.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 09/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import UIKit

class HotelImageViewCell: UICollectionViewCell, NibLoadable {
    
    static let defaultSize = CGSize(width: 180, height: 240)

    @IBOutlet private var imageView: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        imageView.contentMode = .scaleAspectFill
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        imageView.image = nil
    }
    
    func setup(hotelImage: URL?) {
        imageView.kf.setImage(with: hotelImage)
    }

}
