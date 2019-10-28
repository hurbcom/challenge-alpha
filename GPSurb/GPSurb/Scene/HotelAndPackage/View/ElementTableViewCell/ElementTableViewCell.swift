//
//  ElementTableViewCell.swift
//  GPSurb
//
//  Created by Gilson Santos on 17/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import UIKit
import Kingfisher

protocol ElementDelegate: NSObjectProtocol {
    func showDetail(viewData: ResultViewData)
}

class ElementTableViewCell: UITableViewCell {
    
    @IBOutlet weak var cardView: UIView!
    @IBOutlet weak var cardImage: UIImageView!
    @IBOutlet weak var showButton: UIButton!
    @IBOutlet weak var destinationName: UILabel!
    @IBOutlet weak var offerName: UILabel!
    @IBOutlet weak var firstBenefitName: UILabel!
    @IBOutlet weak var secondBenefitName: UILabel!
    @IBOutlet weak var thirdBenefitName: UILabel!
    @IBOutlet weak var oldPrice: UILabel!
    @IBOutlet weak var newPrice: UILabel!
    @IBOutlet weak var heightImage: NSLayoutConstraint!
    @IBOutlet weak var freeCancellationStackView: UIStackView!
    private lazy var viewData = ResultViewData()
    public weak var delegate: ElementDelegate?
    
    override func awakeFromNib() {
        super.awakeFromNib()
        self.setupView()
    }
    
    override func layoutIfNeeded() {
        super.layoutIfNeeded()
        self.heightImage.constant = self.cardView.bounds.height
    }
    
    @IBAction func showDetail(_ sender: UIButton) {
        self.delegate?.showDetail(viewData: self.viewData)
    }
}

// MARK: - AUX METHODS  -
extension ElementTableViewCell {
    public func prepareCell(viewData: ResultViewData) {
        self.viewData = viewData
        self.destinationName.text = viewData.destinationName
        self.offerName.text = viewData.offerName
        self.oldPrice.text = viewData.oldPrice
        self.newPrice.text = viewData.newPrice
        self.freeCancellationStackView.isHidden = !viewData.freeCancellation
        self.downloadImage(urlString: viewData.urlImageCard)
        self.setBenefits(benefits: viewData.amenities)
        self.isAccessibilityElement = true
        self.accessibilityIdentifier = viewData.accessibilityIdentifier
    }
    
    private func setBenefits(benefits: [String]) {
        let resultList = benefits.count > 3 ? [String](benefits.prefix(upTo: 3)) : benefits
        for (index, element) in resultList.enumerated() {
            switch index {
            case 0:
                self.firstBenefitName.text = element
            case 1:
                self.secondBenefitName.text = element
            default:
                self.thirdBenefitName.text = element
            }
        }
    }
    
    func setupView() {
        self.cardView.layer.masksToBounds = false
        self.cardView.layer.cornerRadius = 6
        self.cardImage.layer.cornerRadius = 6
        self.showButton.layer.cornerRadius = 6
        self.cardImage.layer.maskedCorners = [.layerMinXMaxYCorner, .layerMinXMinYCorner]
        self.cardView.layer.shadowColor = UIColor.black.cgColor
        self.cardView.layer.shadowOpacity = 0.4
        self.cardView.layer.shadowOffset = .zero
        self.cardView.layer.shadowRadius = 4
        self.layoutIfNeeded()
    }
    
    private func downloadImage(urlString: String) {
        if let url: URL = URL(string: urlString) {
            let resource = ImageResource(downloadURL: url, cacheKey: urlString)
            let processor = DownsamplingImageProcessor(size: self.cardImage.bounds.size)
            self.cardImage.kf.indicatorType = .activity
            self.cardImage.kf.setImage(with: resource, placeholder: nil, options: [.transition(.fade(0.8)), .cacheOriginalImage, .processor(processor)], progressBlock: nil) { (result) in
                switch result {
                case .failure(_):
                     self.cardImage.image = UIImage(named: "")
                default: break
                }
            }
        }
    }
}
