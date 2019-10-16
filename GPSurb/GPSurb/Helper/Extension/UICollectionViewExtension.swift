//
//  UICollectionViewExtension.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import UIKit

extension UICollectionView {
    func register<T: UICollectionViewCell>(_: T.Type) {
        let bundle = Bundle(for: T.self)
        let nib = UINib(nibName: T.nibName, bundle: bundle)
        self.register(nib, forCellWithReuseIdentifier: T.reusableIdentifier)
    }
    
    func dequeueReusableCell<T: UICollectionViewCell>(for indexPath: IndexPath) -> T {
        let dequeueReusableCell = self.dequeueReusableCell(withReuseIdentifier: T.reusableIdentifier, for: indexPath)
        guard let cell = dequeueReusableCell as? T else { return T() }
        return cell
    }
}
