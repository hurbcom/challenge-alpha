//
//  UIConstants.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import Foundation
import SwiftUI

public struct UIConstants {
    private init() {}
    
    public struct COLOR_NAME {
        private init() {}
        
        public static var hurbBlue: String = "hurb_blue"
        public static var hurbGray: String = "hurb_gray"
        public static var hurbLightGray: String = "hurb_light_gray"
        public static var hurbDarkGray: String = "hurb_dark_gray"
        public static var hurbOrange: String = "hurb_orange"
        public static var hurbPink: String = "hurb_pink"
    }
    
    public struct COLOR {
        private init() {}
        
        public static var hurbBlue: Color = Color(UIConstants.COLOR_NAME.hurbBlue)
        public static var hurbGray: Color = Color(UIConstants.COLOR_NAME.hurbGray)
        public static var hurbLightGray: Color = Color(UIConstants.COLOR_NAME.hurbLightGray)
        public static var hurbDarkGray: Color = Color(UIConstants.COLOR_NAME.hurbDarkGray)
        public static var hurbOrange: Color = Color(UIConstants.COLOR_NAME.hurbOrange)
        public static var hurbPink: Color = Color(UIConstants.COLOR_NAME.hurbPink)
    }
    
    public struct PADDING_VALUES {
        private init() {}
        
        public static var LARGE: CGFloat = 24
        /// 16
        public static var DEFAULT_BIG: CGFloat = 16
        /// 12
        public static var NORMAL: CGFloat = 12
        /// 8
        public static var DEFAULT_SMALL: CGFloat = 8
        /// 4
        public static var SMALL: CGFloat = 4
        /// 2
        public static var SUPER_SMALL: CGFloat = 2
        /// 0
        public static var NONE: CGFloat = 0
    }
}
