//
//  DescriptionView.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 20/01/23.
//

import SwiftUI

@available(iOS 15, *)
struct DescriptionView: View {
    
    var description: String
    var amenities: [String]
    var closeAction: (() -> Void)
    
    var body: some View {
        VStack(spacing: UIConstants.PADDING_VALUES.NONE) {
            AmenitiesGrid(amenities: amenities)
                .padding()
                .background(
                    Color.white
                )
            
            ScrollView(.vertical, showsIndicators: false) {
                Text(description.addFontToHTMLString().htmlToAttributedString())
            }.padding()
            
            HUButton(title: "Fechar", color: UIConstants.COLOR.hurbBlue, style: .fill, verticalPadding: 12) {
                self.closeAction()
            }
            .padding()
            .background(
                Color.white
            )
        }
        .frame(width: UIScreen.main.bounds.width - 32, height: UIScreen.main.bounds.height * 0.8)
        .background(
            Color.white
        )
        .cornerRadius(4)
    }
}
