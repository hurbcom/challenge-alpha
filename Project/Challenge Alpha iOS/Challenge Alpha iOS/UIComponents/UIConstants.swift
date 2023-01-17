//
//  UIConstants.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import Foundation
import SwiftUI

public struct UIConstants {
    public struct COLOR_NAME {
        public static var hurbBlue: String = "hurb_blue"
        public static var hurbGray: String = "hurb_gray"
        public static var hurbLightGray: String = "hurb_light_gray"
        public static var hurbDarkGray: String = "hurb_dark_gray"
        public static var hurbOrange: String = "hurb_orange"
    }
    
    public struct COLOR {
        public static var hurbBlue: Color = Color(UIConstants.COLOR_NAME.hurbBlue)
        public static var hurbGray: Color = Color(UIConstants.COLOR_NAME.hurbGray)
        public static var hurbLightGray: Color = Color(UIConstants.COLOR_NAME.hurbLightGray)
        public static var hurbDarkGray: Color = Color(UIConstants.COLOR_NAME.hurbDarkGray)
        public static var hurbOrange: Color = Color(UIConstants.COLOR_NAME.hurbOrange)
    }
}
