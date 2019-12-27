//
//  ActivityIndicatorView.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import SwiftUI


/// class responsability load my custom activity indicator
struct ActivityIndicator: View {

  @State private var isAnimating: Bool = false
    
    var hide: Bool = false
    init(hide: Bool) {
        self.hide = hide
       }
    
  var body: some View {
    
    VStack {
    GeometryReader { (geometry: GeometryProxy) in
      ForEach(0..<5) { index in
        Group {
            Circle().foregroundColor(Color.init(hex: "#143A7B"))
            .frame(width: geometry.size.width / 5, height: geometry.size.height / 5)
            .scaleEffect(!self.isAnimating ? 1 - CGFloat(index) / 5 : 0.2 + CGFloat(index) / 5)
            .offset(y: geometry.size.width / 10 - geometry.size.height / 2)
          }.frame(width: geometry.size.width, height: geometry.size.height)
            .rotationEffect(!self.isAnimating ? .degrees(0) : .degrees(360))
            .animation(Animation
              .timingCurve(0.5, 0.15 + Double(index) / 5, 0.25, 1, duration: 1.5)
              .repeatForever(autoreverses: false))
        }

      }.aspectRatio(1, contentMode: .fit)
        .onAppear {
          self.isAnimating = true
        }
    }
  }

}
