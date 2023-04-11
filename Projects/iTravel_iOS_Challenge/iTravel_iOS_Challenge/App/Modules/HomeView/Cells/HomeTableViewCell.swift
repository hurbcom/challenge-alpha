//
//  HomeTableViewCell.swift
//  iTravel_iOS_Challenge
//
//  Created by convidado on 09/04/23.
//

import UIKit
import ImageSlideshow

protocol HomeTableViewCellDelegate{
    func tapImage(package:PackageResult)
}
class HomeTableViewCell: UITableViewCell {

    @IBOutlet weak var view: UIView!
    @IBOutlet weak var slideShow: ImageSlideshow!
    @IBOutlet weak var titulo: UILabel!
    @IBOutlet weak var diferencial: UILabel!
    @IBOutlet weak var preco: UILabel!
    @IBOutlet weak var local: UILabel!
    @IBOutlet weak var data: UILabel!
    
    var delegate:HomeTableViewCellDelegate?
    var package:PackageResult?
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        addTapImageGesture()
        
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
    
    
    func addTapImageGesture(){
        let tap = UITapGestureRecognizer(target: self, action: #selector(imageTapped(_:)))
        slideShow.addGestureRecognizer(tap)
    }
    
    @objc func imageTapped(_ sender:Any){
        if let item = self.package{
            delegate?.tapImage(package: item)
        }
    }
}



