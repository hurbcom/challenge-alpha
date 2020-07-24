//
//  CardVerticalCollectionViewCell.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit
import Kingfisher

class CardVerticalCollectionViewCell: UICollectionViewCell {

    // MARK: Outlets
    @IBOutlet weak var vwCard: UIView!
    @IBOutlet weak var ivCard: UIImageView!
    @IBOutlet weak var lblTitle: UILabel!
    @IBOutlet weak var lblDailys: UILabel!
    @IBOutlet weak var lblPeoples: UILabel!
    @IBOutlet weak var lblValue: UILabel!
    @IBOutlet weak var lblDiscount: UILabel!
    
    // MARK: Overrides
    override func awakeFromNib() {
        super.awakeFromNib()
        
        vwCard.layer.borderWidth = 0.2
        vwCard.layer.borderColor = UIColor.lightGray.cgColor
        vwCard.layer.cornerRadius = 8
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        ivCard.image = nil
        lblTitle.text = nil
        lblValue.text = nil
        lblDiscount.text = nil
        lblDailys.text = nil
        lblPeoples.text = nil
    }
    
    // MARK: Setup
    func setup(with card: Highlights.Section.Card) {
        lblTitle.text = card.title
        lblValue.text = card.value
        lblDiscount.text = card.discount
        
        let numberDailys = card.dailys ?? 1
        lblDailys.text = numberDailys == 1 ? "1 diária" : "\(numberDailys) diárias"
        let numberPeaples = card.peoples ?? 1
        lblPeoples.text = numberPeaples == 1 ? "1 pessoa" : "\(numberPeaples) pessoas"
        
        if let imageUrl = card.imageUrl, let url = URL(string: imageUrl) {
            ivCard.kf.setImage(with: url)
        }
    }
}
