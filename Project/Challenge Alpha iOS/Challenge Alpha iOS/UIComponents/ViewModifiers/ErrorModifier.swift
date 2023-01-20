//
//  ErrorModifier.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import SwiftUI

extension View {
    func error(_ show: Binding<Bool>) -> some View {
        self.modifier(ErrorModifier(show: show))
    }
}
struct ErrorModifier: ViewModifier {
    @Binding var show: Bool
    
    func body(content: Content) -> some View {
        ZStack {
            if show {
                ErrorView()
            }
            
            content
        }
    }
}
