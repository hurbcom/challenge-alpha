//
//  HotelListView.swift
//  Hurb
//
//  Created by Arthur Givigir on 12/27/20.
//

import SwiftUI

struct ProductListView: View {
    
    @ObservedObject var productListViewModel = ProductListViewModel()
    
    var body: some View {
        Text("Hello, World!")
    }
}

struct HotelListView_Previews: PreviewProvider {
    static var previews: some View {
        ProductListView()
    }
}
