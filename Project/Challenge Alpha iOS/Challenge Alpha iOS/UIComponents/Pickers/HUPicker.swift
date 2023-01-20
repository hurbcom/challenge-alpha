//
//  HUPicker.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 20/01/23.
//

import SwiftUI

struct HUPicker: View {
    
    var title: String
    @Binding var rooms: Int
    var minLimit: Int
    var maxLimit: Int
    
    var body: some View {
        HStack {
            Text(self.title)
            
            Spacer()
            
            Image(systemName: "minus.circle")
                .font(.system(size: UIConstants.FONT_SIZE.HEADER))
                .foregroundColor(rooms != minLimit ? UIConstants.COLOR.hurbBlue : UIConstants.COLOR.hurbBlue.opacity(0.5))
                .allowsHitTesting(rooms != minLimit)
                .onTapGesture {
                    rooms -= 1
                }
                .padding(.trailing, UIConstants.PADDING_VALUES.DEFAULT_SMALL)
            
            Text("\(rooms)")
            
            Image(systemName: "plus.circle")
                .font(.system(size: UIConstants.FONT_SIZE.HEADER))
                .foregroundColor(rooms != maxLimit ? UIConstants.COLOR.hurbBlue : UIConstants.COLOR.hurbBlue.opacity(0.5))
                .allowsHitTesting(rooms != maxLimit)
                .onTapGesture {
                    rooms += 1
                }
                .padding(.leading, UIConstants.PADDING_VALUES.DEFAULT_SMALL)
        }
    }
}
