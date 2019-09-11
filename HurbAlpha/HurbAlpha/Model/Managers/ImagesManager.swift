//
//  ImagesManager.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 10/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation
import UIKit

// MARK: - Declaration

class ImagesManager {
    
    // MARK: - Instance Initialization
    
    static let instance = ImagesManager()
    private init() {}
    
    // The image view to load image
    var onImageView:UIImageView? 
    
    // The image view activity indicator
    let activityView = UIActivityIndicatorView(style: .gray)
    
    // MARK: - Enum for loading image state declaration
    enum LoadingState {
        case notLoading
        case loading
        case loaded(UIImage)
    }
    
    // The loading image state
    var loadingState: LoadingState = .notLoading {
        didSet {
            guard let imageView = onImageView else { return }
            switch loadingState {
            case .notLoading:
                imageView.image = nil
                activityView.stopAnimating()
            case .loading:
                imageView.image = nil
                activityView.startAnimating()
            case let .loaded(img):
                imageView.image = img
                imageView.contentMode = .scaleAspectFill
                activityView.stopAnimating()
            }
        }
    }
    
    
    /**
     Tries to convert the image url into UIImage.
     - Parameters:
        - url: The image url to convert.
     */
    func tryConvertionFromURL(from url:String) {
        DispatchQueue.main.async {
            let newURL = self.transformToHTTPS(on: url)
            guard let url = URL(string: newURL) else {
                debugPrint("error in image url", #function)
                return
            }
            guard let data = try? Data(contentsOf: url) else {
                debugPrint("error in data from url", url)
                return
            }
            guard let img = UIImage(data: data) else {
                debugPrint("error in uiimage", #function)
                self.loadingState = .notLoading
                return
            }
            self.loadingState = .loaded(img)
        }
    }
    
    /**
     Convert url from http to https.
     - Parameters:
        - url: The image url to convert.
     */
    func transformToHTTPS(on url:String) -> String {
        if url.dropFirst(5) == "https" {
            return url
        } else {
            let nohttp = url.split(separator: ":")
            return "https:" + nohttp[1]
        }
    }
}
