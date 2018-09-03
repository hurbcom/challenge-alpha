//
//  PackageCollectionViewCell.swift
//  Hotels
//
//  Created by Adolfho Athyla on 27/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import UIKit

class PackageCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet var background: UIView!
    @IBOutlet var packageImage: UIImageView!
    @IBOutlet var packageName: UILabel!
    @IBOutlet var packageAddress: UILabel!
    @IBOutlet var packagePrice: UILabel!
    @IBOutlet var amenitiesCollectionView: UICollectionView!
    
    var amenities = [Amenity]()
    
    override func awakeFromNib() {
        super.awakeFromNib()
        //definindo sombra para células de pacotes
        background.layer.shadowColor = UIColor.gray.cgColor
        background.layer.shadowOpacity = 0.5
        background.layer.shadowOffset = CGSize.zero
        background.layer.shadowRadius = 4
        background.layer.shadowPath = UIBezierPath(rect: background.bounds).cgPath
        
        //arredondando bordas: superior esquerda e superior direita
        packageImage.roundCorners([.topLeft, .topRight], radius: 7)
        
        amenitiesCollectionView.delegate = self
        amenitiesCollectionView.dataSource = self
    }
    
}



//MARK: - collection view datasource
/*
 COLLECTION VIEW DE AMENITIES DE PACOTES
*/
extension PackageCollectionViewCell: UICollectionViewDelegate, UICollectionViewDataSource {
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
extension PackageCollectionViewCell: UICollectionViewDelegateFlowLayout {
    
    //definindo tamanho das células da collection de amenities de pacotes
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width = collectionView.bounds.width
        let height = collectionView.bounds.height
        return CGSize(width: (width/2.2), height: (height/2)-3)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 2.0
    }
    
}
