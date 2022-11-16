//
//  PackageAmenitieCollectionViewCell.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 16/11/22.
//

import UIKit

class PackageAmenitieCollectionViewCell: UICollectionViewCell {
    static let identifier = "PackageAmenitieCollectionViewCell"
    
    // MARK: Outlets
    @IBOutlet weak var imageViewAcessory: UIImageView!
    @IBOutlet weak var labelAmenitie: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
    }

    // MARK: Setup
    func setup(with ameniti: SearchResultModel.Amenities) {
        
        labelAmenitie.text = ameniti.name
    }
}
