//
//  HotelsDataSource.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 07/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import UIKit

class HotelsDataSource: NSObject {
    var hotels: [Experience]
    
    init(hotels: [Experience]) {
        self.hotels = hotels
    }
}

extension HotelsDataSource: UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        hotels.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "hotelsCollectionViewCell", for: indexPath) as? HotelCollectionViewCell else {
            debugPrint("Hotel Collection View Cell has no identifier")
            fatalError()
        }
        print(hotels[indexPath.row].address)
        cell.hotel = hotels[indexPath.row]
        cell.setUpUI()
        return cell
    }
    
    
}
