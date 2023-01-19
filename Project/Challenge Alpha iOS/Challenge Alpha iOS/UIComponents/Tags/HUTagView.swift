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
            .font(.system(size: UIConstants.FONT_SIZE.SMALL, weight: .semibold))
            .lineLimit(1)
            .foregroundColor(textColor)
            .padding(.vertical, UIConstants.PADDING_VALUES.SMALL)
            .padding(.horizontal, UIConstants.PADDING_VALUES.NORMAL)
            .background(
                backgroundColor
            )
            .cornerRadius(16)
    }
}
