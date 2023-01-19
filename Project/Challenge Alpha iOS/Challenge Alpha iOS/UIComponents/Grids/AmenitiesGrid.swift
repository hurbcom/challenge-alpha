//
//  AmenitiesGrid.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import SwiftUI

struct AmenitiesGrid: View {
    
    var amenities: [String]
    
    var body: some View {
        LazyVGrid(
            columns: [
                .init(.flexible(), spacing: UIConstants.PADDING_VALUES.DEFAULT_BIG, alignment: .leading),
                .init(.flexible(), alignment: .leading)
            ],
            spacing: UIConstants.PADDING_VALUES.NORMAL)
        {
            ForEach(amenities, id: \.self) { amenity in
                HULabel(imageName: "checkmark.circle", title: amenity, fillColor: UIConstants.COLOR.hurbDarkGray)
            }
        }
    }
}
