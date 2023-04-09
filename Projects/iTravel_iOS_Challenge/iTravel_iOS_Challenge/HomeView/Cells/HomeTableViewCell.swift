//
//  HomeTableViewCell.swift
//  iTravel_iOS_Challenge
//
//  Created by convidado on 09/04/23.
//

import UIKit
import ImageSlideshow

class HomeTableViewCell: UITableViewCell {

    @IBOutlet weak var slideShow: ImageSlideshow!
    @IBOutlet weak var titulo: UILabel!
    @IBOutlet weak var diferencial: UILabel!
    @IBOutlet weak var preco: UILabel!
    @IBOutlet weak var local: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        slideShow.setImageInputs(<#T##inputs: [InputSource]##[InputSource]#>)
        
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
