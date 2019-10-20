//
//  HotelsDataSource.swift
//  Alpha
//
//  Created by Theo Mendes on 18/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit

class HotelsDataSource: NSObject {
    var items: [Deal] = []

    init(with items: [Deal]) {
        self.items = items
    }
}

extension HotelsDataSource: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        items.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: Identifiers.Hotels.rawValue, for: indexPath) as? HotelCollectionViewCell else { fatalError("Unknown identifier") }
        cell.hotel = items[indexPath.row]
        return cell
    }
}

//extension HotelsDataSource: UICollectionViewDelegateFlowLayout {
//    func collectionView(_ collectionView: UICollectionView,
//                        layout collectionViewLayout: UICollectionViewLayout,
//                        sizeForItemAt indexPath: IndexPath) -> CGSize {
//
//        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: Identifiers.Hotels.rawValue, for: indexPath) as? HotelCollectionViewCell else { fatalError("Unknown identifier") }
//
//        cell.hotel = items[indexPath.row]
//        cell.setNeedsLayout()
//        cell.layoutIfNeeded()
//        let size: CGSize = cell.contentView.systemLayoutSizeFitting(UIView.layoutFittingCompressedSize)
//        return CGSize(width: size.width, height: 263)
//    }
//}
