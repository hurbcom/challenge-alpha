//
//  AmenitiesCellView.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import SwiftUI

/// class responsible for displaying amenities data
struct AmenitiesCellView: View {
    
    @ObservedObject var amenitiesCelVM: AmenitiesCellViewModel
       
       init(name: String?) {
           amenitiesCelVM = AmenitiesCellViewModel(name: name)
       }
    
    var body: some View {
        VStack {
            Image(systemName: "checkmark.seal.fill").foregroundColor(Color.init(hex: "#143A7B"))
                .font(.largeTitle)
            Text(self.amenitiesCelVM.name).font(.subheadline).foregroundColor(.secondary).multilineTextAlignment(.center).frame(height: 60)
        }.frame(width: 100, height: 120)
    }
}

struct AmenitiesCellView_Previews: PreviewProvider {
    static var previews: some View {
        AmenitiesCellView(name: "Sala de Jogos")
    }
}
