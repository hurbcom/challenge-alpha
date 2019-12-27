//
//  HomePageView.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import SwiftUI
//class load navigation bar
struct HomePageView: View {
    var body: some View {
        
        NavigationView {
            HotelListView().navigationBarTitle(Text("Búzios"))
        }
    }
}

struct HomePageView_Previews: PreviewProvider {
    static var previews: some View {
        HomePageView()
    }
}
