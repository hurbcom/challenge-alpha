//
//  HotelImage.swift
//  Hotels
//
//  Created by Cmdev on 31/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import UIKit

class HotelImage: UIImageView {

    override func layoutSubviews() {
        super.layoutSubviews()
        
        //arredonda bordas das imagens dos hotéis no lado esquerdo: superior esquerdo e inferior esquerdo
        self.roundCorners([.topLeft, .bottomLeft], radius: 7)
    }

}
