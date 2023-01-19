//
//  HULabel.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 17/01/23.
//

import SwiftUI

/// Horizontal Image + Text
struct HULabel: View {
    
    var imageName: String
    var title: String
    var fillColor: Color
    
    var body: some View {
        HStack(spacing: 4) {
            Image(systemName: imageName)
                .font(.system(size: 14))
                .foregroundColor(fillColor)
            
            Text("\(title)")
                .font(.system(size: 14))
                .foregroundColor(fillColor)
                .minimumScaleFactor(0.5)
                .lineLimit(1)
        }
    }
}
