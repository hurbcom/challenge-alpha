//
//  HeaderSectionView.swift
//  Hurb
//
//  Created by Arthur Givigir on 12/27/20.
//

import SwiftUI

struct HeaderSectionView: View {
    
    var stars: Int?
    
    var body: some View {
        HStack {
            if let stars = stars {
                
                ForEach(0..<5, id: \.self) { value in
                    if value < stars {
                        Image(systemName: "star.fill")
                            .foregroundColor(.yellow)
                            .font(.title2)
                    } else {
                        Image(systemName: "star")
                            .foregroundColor(.yellow)
                            .font(.title2)
                    }
                }
            } else {
                Text("Pacotes")
                    .foregroundColor(.blueHurb)
                    .bold()
                    .font(.title2)
            }
        }
        .padding(10)
        .frame(minWidth: 0, maxWidth: .infinity, alignment: .leading)
    }
}

struct HeaderSectionView_Previews: PreviewProvider {
    static var previews: some View {
        HeaderSectionView(stars: 4)
    }
}
