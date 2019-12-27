//
//  SectionHeaderViewModel.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import Foundation
class SectionHeaderViewModel: ObservableObject {
     
    @Published var stars: Int?
    @Published var title: String
    
    //init label header section for hotel or package
    init(stars: Int) {
        self.stars = stars
        if stars == 0 {
            self.title = "Pacotes".uppercased()
        } else {
            self.title = "Acomodações".uppercased()
        }
    }
    
}
