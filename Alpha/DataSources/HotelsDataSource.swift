//
//  HotelsDataSource.swift
//  Alpha
//
//  Created by Theo Mendes on 18/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit

/// A Data Source for the Hotels Collection View
class HotelsDataSource: NSObject {
    // MARK: - Properties
    var items: [Deal] = []

    // MARK: - Lifecycle

    /**
    Initializes a new Hotels Data Source.

    - Parameters:
       - items: A Deal array to be shown in the collection view.

    - Returns: An initialized data source object.
    */
    init(with items: [Deal]) {
        self.items = items
    }
}

extension HotelsDataSource: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        items.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: Identifiers.Hotel.rawValue, for: indexPath) as? HotelCollectionViewCell else { fatalError("Unknown identifier") }
        cell.hotel = items[indexPath.row]
        cell.accessibilityIdentifier = "Hotel\(indexPath)"
        cell.imageView.accessibilityIdentifier = "HotelImage\(indexPath)"
        cell.imageView.isAccessibilityElement = true
        return cell
    }
}
