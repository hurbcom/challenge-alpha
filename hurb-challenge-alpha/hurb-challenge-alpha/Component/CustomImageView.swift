//
//  CustomImageView.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import SwiftUI

import struct Kingfisher.KFImage
import struct Kingfisher.DownsamplingImageProcessor
import struct Kingfisher.ResizingImageProcessor
import struct Kingfisher.RoundCornerImageProcessor

/// Kingfisher component abstraction responsible for displaying images
struct CustomImageView: View {
    
    var targetSize = CGSize()
    var url: String
    
    init(url: String, size: CGSize = CGSize(width: 247.05, height: 140)) {
        
        self.url = url
        self.targetSize = size
        
    }
    var body: some View {
        KFImage(URL(string: self.url),
                options: [
                    .processor(
                        ResizingImageProcessor(referenceSize: targetSize, mode: .none)),
                    .scaleFactor(UIScreen.main.scale)            ]
        )
    }
}

struct CustomImageView_Previews: PreviewProvider {
    
    @State static var  urlImage = DataContants.sharedInstance.urlImage
    
    static var previews: some View {
        CustomImageView(url: urlImage)
    }
}
