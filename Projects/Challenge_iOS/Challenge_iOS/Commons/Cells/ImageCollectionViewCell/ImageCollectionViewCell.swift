//
//  ImageCollectionViewCell.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 13/11/22.
//

import UIKit
import Kingfisher

class ImageCollectionViewCell: UICollectionViewCell {
    static let identifier = "ImageCollectionViewCell"

    // MARK: Outlets
    @IBOutlet weak var imageView: UIImageView!
    
    // MARK: Overrides
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    // MARK: Setup
    func setup(with imageUrl: String) {
        imageView.kf.setImage(with: URL(string: imageUrl))
    }
}
