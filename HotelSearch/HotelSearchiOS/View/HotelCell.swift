//
//  HotelCell.swift
//  HotelSearchiOS
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import UIKit

import HotelSearch

final public class HotelCell: UITableViewCell {

    // MARK: - IBOutlets
    
    @IBOutlet private(set) public weak var imageContainer: UIView!
    @IBOutlet private(set) public weak var imvBackground: UIImageView!
    @IBOutlet private(set) public weak var viewBlur: UIView!
    @IBOutlet private(set) public weak var stackView: UIStackView!
    @IBOutlet private(set) public weak var lblName: UILabel!
    @IBOutlet private(set) public weak var lblLocation: UILabel!
    @IBOutlet private(set) public weak var lblAmenities: UILabel!
    @IBOutlet private(set) public weak var lblPrice: UILabel!

    // MARK: - Properties
    
    var imageData: Data? {
        didSet {
            let isDataNil = self.imageData == nil
            self.imageContainer.isShimmering = isDataNil
            self.imvBackground.setImageAnimated(isDataNil ? nil : UIImage(data: self.imageData!))
        }
    }
    
    var viewModel: HotelViewModel? {
        didSet {
            self.lblName.text = self.viewModel?.name
            self.lblLocation.text = self.viewModel?.location
            self.lblAmenities.text = self.viewModel?.amenities
            self.lblPrice.text = self.viewModel?.price
        }
    }

    // MARK: - Life Cycle
    
    public override func awakeFromNib() {
        super.awakeFromNib()
        self.setupUI()
    }

    public override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
}

private extension HotelCell {

    // MARK: - Setup
    
    func setupUI() {
        [self.imvBackground, self.viewBlur].forEach { $0.layer.cornerRadius = 6 }
    }
    
}

extension UIImageView {
    func setImageAnimated(_ newImage: UIImage?) {
        image = newImage
        
        guard newImage != nil else { return }
        
        alpha = 0
        UIView.animate(withDuration: 0.25) {
            self.alpha = 1
        }
    }
}



extension UIView {
    public var isShimmering: Bool {
        set {
            if newValue {
                startShimmering()
            } else {
                stopShimmering()
            }
        }
        
        get {
            return layer.mask?.animation(forKey: shimmerAnimationKey) != nil
        }
    }
    
    private var shimmerAnimationKey: String {
        return "shimmer"
    }
    
    private func startShimmering() {
        let white = UIColor.white.cgColor
        let alpha = UIColor.white.withAlphaComponent(0.75).cgColor
        let width = bounds.width
        let height = bounds.height
        
        let gradient = CAGradientLayer()
        gradient.colors = [alpha, white, alpha]
        gradient.startPoint = CGPoint(x: 0.0, y: 0.4)
        gradient.endPoint = CGPoint(x: 1.0, y: 0.6)
        gradient.locations = [0.4, 0.5, 0.6]
        gradient.frame = CGRect(x: -width, y: 0, width: width*3, height: height)
        layer.mask = gradient
        
        let animation = CABasicAnimation(keyPath: #keyPath(CAGradientLayer.locations))
        animation.fromValue = [0.0, 0.1, 0.2]
        animation.toValue = [0.8, 0.9, 1.0]
        animation.duration = 1.25
        animation.repeatCount = .infinity
        gradient.add(animation, forKey: shimmerAnimationKey)
    }
    
    private func stopShimmering() {
        layer.mask = nil
    }
}
