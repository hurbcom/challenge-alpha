//
//  ImageCarousel.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import SwiftUI
import Kingfisher

struct ImageCarousel: View {
    
    var imagesURL: [String]
    var maxHeight: CGFloat = 224
    
    var body: some View {
        TabView {
            ForEach(imagesURL, id: \.self) { url in
                KFImage(URL(string: url))
                    .placeholder { _ in
                        Color.white
                    }
                    .resizable()
            }
            .overlay(
                LinearGradient(
                    colors: [.clear, .clear, .black.opacity(0.6)],
                    startPoint: .center,
                    endPoint: .bottom
                )
            )
        }
        .frame(maxWidth: .infinity, minHeight: self.maxHeight, maxHeight: self.maxHeight)
        .tabViewStyle(.page(indexDisplayMode: .always))
    }
}

struct ImageCarousel_Previews: PreviewProvider {
    static var previews: some View {
        VStack {
            ImageCarousel(
                imagesURL: [
                    "https://thumbcdn-b.hotelurbano.net/HMPCfEPtnilwIrF6fEbUQLKNAbI=/origxorig/center/middle/filters:quality(70)/https://i.travelapi.com/hotels/3000000/2230000/2227000/2226944/2b054887_z.jpg",
                    "https://thumbcdn-3.hotelurbano.net/f8sxWMKkAQ6nGtA20ynXRGac0zI=/origxorig/center/middle/filters:quality(70)/https://i.travelapi.com/hotels/3000000/2230000/2227000/2226944/02587451_z.jpg"
                ],
                maxHeight: UIScreen.main.bounds.height / 4
            )
            Spacer()
        }
    }
}
