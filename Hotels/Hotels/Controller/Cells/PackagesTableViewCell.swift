//
//  PackagesTableViewCell.swift
//  Hotels
//
//  Created by Adolfho Athyla on 27/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import UIKit
import SDWebImage

class PackagesTableViewCell: UITableViewCell {

    @IBOutlet var packagesCollectionView: UICollectionView!
    var packages = [Hotel]()
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        packagesCollectionView.delegate = self
        packagesCollectionView.dataSource = self
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}



//MARK: - collection view datasource
extension PackagesTableViewCell: UICollectionViewDelegate, UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return packages.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "PACKAGE_CELL_IDENTIFIER", for: indexPath) as? PackageCollectionViewCell
        let package = packages[indexPath.row]
        fillPackageCell(cell: cell!, package: package)
        return cell!
    }
    
    //MARK: - Fill cells
    private func fillPackageCell(cell: PackageCollectionViewCell, package: Hotel) {
        cell.packageName.text = package.name
        cell.packageAddress.text = package.address?.addressResume
        cell.packagePrice.text = package.price?.currentPrice?.doubleValue.getMoneyValue()
        cell.amenities = Array(package.amenities.prefix(4))
        cell.packageImage.sd_setImage(with: URL(string: package.gallery.first?.url ?? ""), completed: { (image, error, type, url) in
            print(error ?? "Sem erros")
        })
    }
}



//MARk: - collection view flow layout
extension PackagesTableViewCell: UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        return UIEdgeInsets(top: 0, left: 7.0, bottom: 0, right: 3.0)
    }
    
}
