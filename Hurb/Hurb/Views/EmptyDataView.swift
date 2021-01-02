//
//  EmptyView.swift
//  Hurb
//
//  Created by Arthur Givigir on 1/2/21.
//

import SwiftUI

struct EmptyDataView: View {
    var body: some View {
        VStack {
            Text("😥")
                .font(.title)
                
            Text("Poxa... Não encontramos nada por aqui...")
                .font(.headline)
                .fontWeight(.bold)
                .foregroundColor(.darkGray)
                .frame(width: 300, alignment: .center)
        }
        .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .center)
    }
}

struct EmptyView_Previews: PreviewProvider {
    static var previews: some View {
        EmptyDataView()
    }
}
