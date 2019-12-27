//
//  HotelRowView.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import SwiftUI

struct HotelRowView: View {
    var hotels: [Accommodation]
    
    var body: some View {
        ScrollView(.horizontal, content: {
            HStack(spacing: 10) {
                ForEach(hotels, content: { item in
                    HotelCellView(hotel: item).frame(width: 247.05)
                        .padding(EdgeInsets(top: 10, leading: 0, bottom: 20, trailing: 30))
                })
            }
        })
    }
}

struct HotelRowView_Previews: PreviewProvider {
    
    @State static var hotelTest = [Accommodation]()
    
    static var previews: some View {
        HotelRowView(hotels: hotelTest)
    }
}
