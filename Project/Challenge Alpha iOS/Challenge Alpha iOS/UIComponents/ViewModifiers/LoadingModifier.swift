//
//  LoadingModifier.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import SwiftUI

extension View {
    func loading(_ isLoading: Binding<Bool>) -> some View {
        self.modifier(LoadingModifier(isLoading: isLoading))
    }
}
struct LoadingModifier: ViewModifier {
    @Binding var isLoading: Bool
    
    func body(content: Content) -> some View {
        ZStack {
            if isLoading {
                ProgressView()
                    .progressViewStyle(CircularProgressViewStyle(tint: UIConstants.COLOR.hurbBlue))
            }
            
            content
        }
    }
}
