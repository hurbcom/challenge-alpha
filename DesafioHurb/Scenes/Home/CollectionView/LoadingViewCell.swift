//
//  LoadingViewCell.swift
//  Cardpay
//
//  Created by Edson Aparecido Guido on 05/08/20.
//

import UIKit

class LoadingViewCell: UICollectionViewCell, NibLoadable {
    static let defaultHeight = CGFloat(44.0)

    @IBOutlet private var activityIndicator: UIActivityIndicatorView!

    override func didMoveToWindow() {
        super.didMoveToWindow()
        if #available(iOS 13.0, *) {
            activityIndicator.style = UIActivityIndicatorView.Style.large
        } else {
            activityIndicator.style = UIActivityIndicatorView.Style.white
        }
    }

    override func prepareForReuse() {
        super.prepareForReuse()
        activityIndicator.stopAnimating()
    }

    func startAnimating() {
        activityIndicator.startAnimating()
    }
}
