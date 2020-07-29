//
//  UIImageView.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 28/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import UIKit
import SDWebImage

extension UIImageView {
    func loadWith(_ string: String?) {
        guard let string = string,
            let url = URL(string: string) else {
                return
        }
        
        let loading = showLoading()
        SDWebImageManager.shared.loadImage(with: url, options: SDWebImageOptions(), progress: nil) { image, _, _, _, _, _ in
            self.image = image
            loading.stopAnimating()
        }
    }
    
    private func showLoading() -> UIActivityIndicatorView {
        if let loading = subviews.first(where: { $0 is UIActivityIndicatorView }) as? UIActivityIndicatorView {
            loading.startAnimating()
            return loading
        } else {
            let loading = UIActivityIndicatorView()
            loading.hidesWhenStopped = true
            loading.startAnimating()
            loading.createConstraints(self) { maker in
                maker.center.equalToSuperview()
            }
            return loading
        }
    }
}
