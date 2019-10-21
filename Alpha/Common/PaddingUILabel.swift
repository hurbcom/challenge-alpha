//
//  PaddingLabel.swift
//  Alpha
//
//  Created by Theo Mendes on 18/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation
import UIKit

/// A UILabel with padding
class PaddingUILabel: UILabel {
    // MARK: - Properties

    var topInset: CGFloat
    var bottomInset: CGFloat
    var leftInset: CGFloat
    var rightInset: CGFloat

    override var intrinsicContentSize: CGSize {
        get {
            var contentSize = super.intrinsicContentSize
            contentSize.height += topInset + bottomInset
            contentSize.width += leftInset + rightInset
            return contentSize
        }
    }

    // MARK: - Lifecycle

    /**
    Initializes UILabel with padding

    - Parameters:
       - top: The top inset
       - bottom: The bottom inset
       - left: The left inset
       - right: The top inset

    - Returns: An initialized view object.
    */
    required init(withInsets top: CGFloat,
                  _ right: CGFloat,
                  _ bottom: CGFloat,
                  _ left: CGFloat) {
        self.topInset = top
        self.bottomInset = bottom
        self.leftInset = left
        self.rightInset = right
        super.init(frame: .zero)
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    // MARK: - View methods

    override func drawText(in rect: CGRect) {
        let insets = UIEdgeInsets(top: topInset, left: leftInset, bottom: bottomInset, right: rightInset)
        super.drawText(in: rect.inset(by: insets))
    }
}
