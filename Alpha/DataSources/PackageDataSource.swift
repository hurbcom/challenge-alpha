//
//  PackageDataSource.swift
//  Alpha
//
//  Created by Theo Mendes on 19/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit
import os.log

/// A Data Source for the Package Collection View
class PackageDataSource: NSObject {
    // MARK: - Properties
    var items: [Deal] = []

    // MARK: - Lifecycle

    /**
    Initializes a new Package Data Source.

    - Parameters:
       - items: A Deal array to be shown in the collection view.

    - Returns: An initialized data source object.
    */
    init(with items: [Deal]) {
        self.items = items
    }
}

extension PackageDataSource: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView,
                        numberOfItemsInSection section: Int) -> Int {
        items.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(
            withReuseIdentifier: Identifiers.Package.rawValue,
            for: indexPath) as? PackageCollectionViewCell else {
                os_log("❌ - Unknown cell identifier %@", log: Logger.appLog(), type: .fault, "\(self)")
                fatalError("Unknown identifier")
        }
        cell.package = items[indexPath.row]
        cell.accessibilityIdentifier = "Package\(indexPath)"
        return cell
    }
}
