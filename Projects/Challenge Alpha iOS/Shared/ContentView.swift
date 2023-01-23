//
//  ContentView.swift
//  Shared
//
//  Created by Theo Mendes on 04/11/21.
//

import SwiftUI
import Combine

struct ContentView: View {
    @State private var selection: String? = nil
    
    var body: some View {
        NavigationView {
            VStack(alignment: .center) {
                NavigationLink(destination: ResultRenderView(type: .search)) {
                    Text("Search")
                        .fontWeight(.bold)
                        .font(.title)
                        .padding()
                        .background(Color.blue)
                        .cornerRadius(40)
                        .foregroundColor(.white)
                        .padding(10)
                }.buttonStyle(PlainButtonStyle())

                NavigationLink(destination: ResultRenderView(type: .searchPackage)) {
                    Text("Search Package")
                        .fontWeight(.bold)
                        .font(.title)
                        .padding()
                        .background(Color.blue)
                        .cornerRadius(40)
                        .foregroundColor(.white)
                        .padding(10)
                }.buttonStyle(PlainButtonStyle())

                NavigationLink(destination: ResultRenderView(type: .searchHotel)) {
                    Text("Search Hotel")
                        .fontWeight(.bold)
                        .font(.title)
                        .padding()
                        .background(Color.blue)
                        .cornerRadius(40)
                        .foregroundColor(.white)
                        .padding(10)
                }.buttonStyle(PlainButtonStyle())

                NavigationLink(destination: ResultRenderView(type: .suggestion)) {
                    Text("Suggestion")
                        .fontWeight(.bold)
                        .font(.title)
                        .padding()
                        .background(Color.blue)
                        .cornerRadius(40)
                        .foregroundColor(.white)
                        .padding(10)
                }.buttonStyle(PlainButtonStyle())
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
