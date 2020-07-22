//
//  ProductCardCell.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

class ProductCardCell: UITableViewCell {
    
    // MARK: Properties
    var isFavorited = false
    
    // MARK: Outlets
    @IBOutlet private weak var vwCard: UIView!
    @IBOutlet private weak var lblTitle: UILabel!
    @IBOutlet private weak var lblDescription: UILabel!
    @IBOutlet private weak var vwTagDiscount: UIView!
    @IBOutlet private weak var ivProduct: UIImageView!
    @IBOutlet private weak var numberDailys: UILabel!
    @IBOutlet private weak var numberPeoples: UILabel!
    @IBOutlet private weak var lblOldPrice: UILabel!
    @IBOutlet private weak var lblPrice: UILabel!
    @IBOutlet private weak var ivHotel: UIImageView!
    @IBOutlet private weak var ivCoffee: UIImageView!
    @IBOutlet private weak var ivAirplane: UIImageView!
    @IBOutlet private weak var ivFavorite: UIImageView!
    

    // MARK: Overrides
    override func awakeFromNib() {
        super.awakeFromNib()
        selectionStyle = .none
        
        vwCard.layer.cornerRadius = 8
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        isFavorited = false
    }
    
    // MARK: Setup
    func setup(with product: Product) {
        setTitleAndDescription(product)
        setInfoValues(product)
        setAnimatedFavorite()
    }
    
    // MARK: Actions
    @IBAction func handlerFavorite(_ sender: Any) {
        //Adicionei o botão de favoritos porque acho que é interessante ter ele na lista de resultados.
        //Mas, implementei apenas a animação do botão.
        isFavorited.toggle()
        setAnimatedFavorite()
    }
    
    // MARK: Helpers
    private func setTitleAndDescription(_ product: Product) {
        lblDescription.text = product.smallDescription
    }
    
    private func setInfoValues(_ product: Product) {
        let price: Double = product.price?.total_price ?? 0
        let oldPrice: Double = product.price?.old_price ?? 0
        lblOldPrice.text = oldPrice.description
        lblPrice.text = price.description
        let isHiddingOldPrice = (price == oldPrice) || oldPrice == 0
        lblOldPrice.isHidden = isHiddingOldPrice
    }
    
    private func setAnimatedFavorite() {
        let imgFavorite = isFavorited ? UIImage(named: "imgFavorite") : UIImage(named: "imgNotFavorite")
        ivFavorite.image = imgFavorite
        ivFavorite.tintImage(color: .white)
    }
}
