//
//  HighlightsNotFoundView.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

final class HighlightsNotFoundView: UIView {
    func instanceFromNib() -> UIView {
        return UINib(nibName: String(describing: Self.self), bundle: nil).instantiate(withOwner: nil, options: nil)[0] as! UIView
    }
}
