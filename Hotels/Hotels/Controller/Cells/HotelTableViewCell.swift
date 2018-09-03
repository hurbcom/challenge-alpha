//
//  HotelTableViewCell.swift
//  Hotels
//
//  Created by Adolfho Athyla on 25/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import UIKit

class HotelTableViewCell: UITableViewCell {

    @IBOutlet var background: UIView!
    @IBOutlet var hotelImage: UIImageView!
    @IBOutlet var hotelName: UILabel!
    @IBOutlet var hotelAddress: UILabel!
    @IBOutlet var hotelPrice: UILabel!
    @IBOutlet var amenitiesCollectionView: UICollectionView!
    
    var amenities = [Amenity]()
    
    override func awakeFromNib() {
        super.awakeFromNib()
        //definindo sombra às células de hotéis
        background.layer.shadowColor = UIColor.gray.cgColor
        background.layer.shadowOpacity = 0.5
        background.layer.shadowOffset = CGSize.zero
        background.layer.shadowRadius = 4
        background.layer.shadowPath = UIBezierPath(rect: background.bounds).cgPath
        
        amenitiesCollectionView.delegate = self
        amenitiesCollectionView.dataSource = self
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}


//MARK: - collection view datasource
/*
 COLLECTION VIEW DE AMENITIES DE HOTÉIS
*/
extension HotelTableViewCell: UICollectionViewDelegate, UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return amenities.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "AMENITY_CELL_IDENTIFIER", for: indexPath) as? AmenityCollectionViewCell
        cell?.amenityName.text = amenities[indexPath.row].name
        return cell!
    }
}



//MARk: - collection view flow layout
extension HotelTableViewCell: UICollectionViewDelegateFlowLayout {
    
    //definindo tamanho das células de amenities de hotéis
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width = collectionView.bounds.width
        let height = collectionView.bounds.height
        return CGSize(width: (width/2.2), height: (height/2)-3)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 2.5
    }

}
