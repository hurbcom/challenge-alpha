//
//  StarsTableViewHeaderCell.swift
//  Hotels
//
//  Created by Adolfho Athyla on 30/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import UIKit
import Cosmos

class StarsTableViewHeaderCell: UITableViewCell {

    @IBOutlet var cosmosView: CosmosView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        cosmosView.settings.updateOnTouch = false
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
