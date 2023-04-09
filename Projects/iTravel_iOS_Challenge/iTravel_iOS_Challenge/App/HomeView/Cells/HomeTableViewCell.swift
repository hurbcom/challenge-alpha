//
//  HomeTableViewCell.swift
//  iTravel_iOS_Challenge
//
//  Created by convidado on 09/04/23.
//

import UIKit
import ImageSlideshow

class HomeTableViewCell: UITableViewCell {

    @IBOutlet weak var view: UIView!
    @IBOutlet weak var slideShow: ImageSlideshow!
    @IBOutlet weak var titulo: UILabel!
    @IBOutlet weak var diferencial: UILabel!
    @IBOutlet weak var preco: UILabel!
    @IBOutlet weak var local: UILabel!
    @IBOutlet weak var data: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code

        
        
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}

extension HomeTableViewCell{
    
    func setupUI(){
        let pageIndicator = UIPageControl()
        pageIndicator.currentPageIndicatorTintColor = UIColor(named: "AccentColor")
        pageIndicator.pageIndicatorTintColor = UIColor.black
        slideShow.pageIndicator = pageIndicator
        slideShow.contentScaleMode = .scaleAspectFill
        self.view.layer.cornerRadius = 8
        self.view.layer.masksToBounds = true

    }
}



