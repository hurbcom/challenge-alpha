//
//  PackageDataSource.swift
//  Alpha
//
//  Created by Theo Mendes on 19/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit

class PackageDataSource: NSObject {
    var items: [APIResult] = []

    init(with items: [APIResult]) {
        self.items = items
    }
}

extension PackageDataSource: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        items.count
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: Identifiers.Package.rawValue, for: indexPath) as? PackageCollectionViewCell else { fatalError("Unknown identifier") }
        cell.package = items[indexPath.row]
        return cell
    }
}
