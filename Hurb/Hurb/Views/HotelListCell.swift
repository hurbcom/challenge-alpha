//
//  HotelListCell.swift
//  Hurb
//
//  Created by Arthur Givigir on 12/29/20.
//

import SwiftUI

struct HotelListCell: View {
    
    var hotel: Hotel
    
    
    var body: some View {
        HStack(alignment: .center) {
            VStack(alignment: .leading, spacing: 5) {
                Text("Hotel Name")
                Text("Price")
            }
        }
        .frame(
            minWidth: 0,
            maxWidth: .infinity,
            minHeight: 0,
            maxHeight: .infinity,
            alignment: .leading
        )
        
    }
}

//struct HotelListCell_Previews: PreviewProvider {
//    static var previews: some View {
//        
//        
//        HotelListCell(hotel: )
//    }
//}
