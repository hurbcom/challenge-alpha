//
//  HUTagView.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 17/01/23.
//

import SwiftUI

struct HUTagView: View {
    
    var title: String
    var textColor: Color
    var backgroundColor: Color
    
    var body: some View {
        Text(title)
            .font(.system(size: 14, weight: .semibold))
            .lineLimit(1)
            .foregroundColor(textColor)
            .padding(.vertical, 4)
            .padding(.horizontal, 12)
            .background(
                backgroundColor
            )
            .cornerRadius(16)
    }
}
