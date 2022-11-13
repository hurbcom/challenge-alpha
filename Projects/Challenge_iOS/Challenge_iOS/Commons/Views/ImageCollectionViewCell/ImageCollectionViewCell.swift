//
//  ImageCollectionViewCell.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 13/11/22.
//

import UIKit

class ImageCollectionViewCell: UICollectionViewCell {
    static let identifier = "ImageCollectionViewCell"

    // MARK: Outlets
    @IBOutlet weak var imageView: UIImageView!
    
    // MARK: Overrides
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    // MARK: Setup
    func setup(with imageUrl: String) {
        print("==> ImageURL: \(imageUrl)")
    }
}
