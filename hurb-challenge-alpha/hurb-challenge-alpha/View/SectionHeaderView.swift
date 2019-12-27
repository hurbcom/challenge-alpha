//
//  SectionHeaderView.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import SwiftUI

/// class responsable for load section header of list
struct SectionHeaderView: View {
    
    @ObservedObject var sectionVM: SectionHeaderViewModel

        init(stars: Int) {
           sectionVM = SectionHeaderViewModel(stars: stars)
       }
    
    var body: some View {
        HStack(spacing: 20) {
            Text(self.sectionVM.title).font(.headline)
                .foregroundColor(Color.white)
                .foregroundColor(.secondary)
                .padding(.leading, 15.0)
            ForEach((0..<self.sectionVM.stars!), id: \.self) {_ in
                Image(systemName: "star.fill").foregroundColor(Color.yellow)
               }
        }.frame( height: 50, alignment: .trailing)
        }
    }

struct SectionHeaderView_Previews: PreviewProvider {
    static var previews: some View {
        SectionHeaderView(stars: 5)
    }
}
