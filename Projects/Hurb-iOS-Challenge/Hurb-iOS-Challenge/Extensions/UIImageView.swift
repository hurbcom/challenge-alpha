//
//  UIImageView.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 10/01/23.
//

import UIKit
import Kingfisher

extension UIImageView {
    
    func loadPhoto(url: String?, completion: (() -> Void)? = nil) {
        
        guard let imageUrl: String = url,
              let anUrl = URL(string: imageUrl) else { return }
        
        let resourse: ImageResource = ImageResource(
            downloadURL: anUrl,
            cacheKey: imageUrl
        )
        
        self.kf.indicatorType = .activity
        
        DispatchQueue.main.async {
            
            self.kf.setImage(
                with: resourse,
                placeholder: nil,
                options: [.diskCacheExpiration(.days(15))],
                completionHandler: { [weak self] result in
                    
                    
                    switch result {
                            
                        case .success(let image):
                            let hRatio: CGFloat = image.image.size.height / image.image.size.width
                            self?.contentMode = hRatio >= 1 ? .scaleAspectFit : .scaleAspectFill
                            break
                            
                        default:
                            break
                    }
                    
                    completion?()
                })
        }
    }
    
    func prepareForReuse() {
        
        self.kf.cancelDownloadTask()
        self.contentMode = .scaleAspectFit
        self.image = nil
    }
}
