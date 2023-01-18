//
//  UIImageView+Extensions.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 17/01/23.
//

import UIKit

extension UIImageView {
    func loadFrom(URLAdress: String) {
        guard let url = URL(string: URLAdress) else {
            return
        }
            DispatchQueue.global().async {
                if let imageData = try? Data(contentsOf: url) {
                    if let loadedImage = UIImage(data: imageData) {
                        DispatchQueue.main.async {
                            self.image = loadedImage
                        }
                    }
                }
            }
        }
    }
