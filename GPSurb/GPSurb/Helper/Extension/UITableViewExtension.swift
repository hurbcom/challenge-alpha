//
//  UITableViewExtension.swift
//  GPSurb
//
//  Created by Gilson Santos on 17/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import UIKit

extension UITableView {
    func register<T: UITableViewCell>(_: T.Type) {
        let bundle = Bundle(for: T.self)
        let nib = UINib(nibName: T.nibName, bundle: bundle)
        self.register(nib, forCellReuseIdentifier: T.reusableIdentifier)
    }
    
    func dequeueReusableCell<T: UITableViewCell>(for indexPath: IndexPath) -> T {
        let dequeueReusableCell = self.dequeueReusableCell(withIdentifier: T.reusableIdentifier, for: indexPath)
        guard let cell = dequeueReusableCell as? T else { return T() }
        return cell
    }
}
