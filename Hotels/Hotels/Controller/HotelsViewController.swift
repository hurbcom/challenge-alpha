//
//  HotelsViewController.swift
//  Hotels
//
//  Created by Adolfho Athyla on 25/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import UIKit
import SDWebImage

class HotelsViewController: UIViewController {

    var hotels = [Hotel]()
    
    @IBOutlet var tableView: UITableView!
    @IBOutlet var searchCollectionView: UICollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        HUAPIHelper.loadHotels { (hotels, success) in
            if success {
                guard let _ = hotels else { return }
                print(hotels!)
                self.hotels = hotels!
                self.tableView.reloadData()
            }
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}


extension HotelsViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return Hotel.getHotels(hotels: self.hotels).count + 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if indexPath.row == 0 {
            let cell = tableView.dequeueReusableCell(withIdentifier: "PACKAGES_CELL_IDENTIFIER", for: indexPath) as? PackagesTableViewCell
            fillPackageCell(cell: cell!, packages: Hotel.getPackages(hotels: self.hotels))
            return cell!
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier: "HOTEL_CELL_IDENTIFIER", for: indexPath) as? HotelTableViewCell
            let hotel = Hotel.getHotels(hotels: self.hotels)[indexPath.row-1]
            fillHotelCell(cell: cell!, hotel: hotel)
            return cell!
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return indexPath.row == 0 ? 280.0 : 150.0
    }
    
    //MARK: - Fill cells
    private func fillHotelCell(cell: HotelTableViewCell, hotel: Hotel) {
        cell.hotelName.text = hotel.name
        cell.hotelAddress.text = hotel.address?.addressResume
        cell.hotelPrice.text = hotel.price?.currentPrice?.doubleValue.getMoneyValue()
        cell.amenities = Array(hotel.amenities.prefix(4))
        cell.hotelImage.sd_setImage(with: URL(string: hotel.image ?? "Error"), completed: { (image, error, type, url) in
            print(error ?? "Nenhum erro")
        })
    }
    
    private func fillPackageCell(cell: PackagesTableViewCell, packages: [Hotel]) {
        cell.packages = packages
        cell.packagesCollectionView.reloadData()
    }
}


extension HotelsViewController: UICollectionViewDelegate, UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 0
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        return UICollectionViewCell()
    }
    
    
}
