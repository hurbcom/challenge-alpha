//
//  HighlightHorizontalCell.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

class HighlightHorizontalCell: UITableViewCell {

    // MARK: Outlets
    @IBOutlet weak var lblTitle: UILabel!
    @IBOutlet weak var lblSubtitle: UILabel!
    @IBOutlet weak var collectionView: UICollectionView! {
        didSet{
            
        }
    }
    
    
    // MARK: Overrides
    override func awakeFromNib() {
        super.awakeFromNib()
        selectionStyle = .none
    }
    
    // MARK: Setup
    func setup() {
        
    }
}
