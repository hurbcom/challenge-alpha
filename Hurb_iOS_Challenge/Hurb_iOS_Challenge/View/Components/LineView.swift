//
//  LineView.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 29/05/22.
//

import UIKit

class LineView: UIView {
    // MARK: - Lifecycle
    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .lightGray
        setDimensions(width: Constants.HomeCollectionViewConstraints.HOME_COLLECTIONVIEW_DIMENTIONS_SIZE_WITH_PADDING.width, height: 0.5)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
