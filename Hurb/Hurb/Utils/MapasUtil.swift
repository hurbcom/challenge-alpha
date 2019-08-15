//
//  MapasUtil.swift
//  Hurb
//
//  Created by Alexandre Papanis on 14/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import Foundation
import MapKit

class MapasUtil {
    
    static func openAppleMaps(endereco: String) {
        var enderecoSemEspacos = endereco.replacingOccurrences(of: " ", with: "+")
        enderecoSemEspacos = enderecoSemEspacos.replacingOccurrences(of: "/", with: "%2F")
        enderecoSemEspacos = enderecoSemEspacos.folding(options: .diacriticInsensitive, locale: .current)
        enderecoSemEspacos = enderecoSemEspacos.convertStringToUrlString
        
        var urlComponents = URLComponents(string: "http://maps.apple.com/")!
        
        urlComponents.queryItems = [
            URLQueryItem(name: "address", value: enderecoSemEspacos),
        ]
        
        let options: [UIApplication.OpenExternalURLOptionsKey : Any] = [
            UIApplication.OpenExternalURLOptionsKey(rawValue: MKLaunchOptionsDirectionsModeKey) : MKLaunchOptionsDirectionsModeDriving
        ]
        
        guard let url = urlComponents.url else { return }
        UIApplication.shared.open(url, options: options, completionHandler: { success in
            print(success)
        })
    }
    
    static func openWaze(endereco: String) {
        var enderecoSemEspacos = endereco.replacingOccurrences(of: " ", with: "+")
        enderecoSemEspacos = enderecoSemEspacos.replacingOccurrences(of: "/", with: "%2F")
        enderecoSemEspacos = enderecoSemEspacos.folding(options: .diacriticInsensitive, locale: .current)
        enderecoSemEspacos = enderecoSemEspacos.convertStringToUrlString
        
        if UIApplication.shared.canOpenURL(URL(string: "waze://")!) {
            
            var urlComponents = URLComponents(string: "waze://")!
            
            urlComponents.queryItems = [
                URLQueryItem(name: "q", value: enderecoSemEspacos),
                URLQueryItem(name: "navigate", value: "yes")
            ]
            
            UIApplication.shared.open(urlComponents.url!, options: [:], completionHandler: nil)
        }
        else {
            UIApplication.shared.open(URL(string: "http://itunes.apple.com/us/app/id323229106")!, options: [:], completionHandler: nil)
        }
    }
    
    static func openGoogleMaps(endereco: String) {
        if UIApplication.shared.canOpenURL(URL(string: "comgooglemaps://")!) {
            var enderecoSemEspacos = endereco.replacingOccurrences(of: " ", with: "+")
            enderecoSemEspacos = enderecoSemEspacos.replacingOccurrences(of: "/", with: "%2F")
            enderecoSemEspacos = enderecoSemEspacos.folding(options: .diacriticInsensitive, locale: .current)
            enderecoSemEspacos = enderecoSemEspacos.convertStringToUrlString
            
            var urlComponents = URLComponents(string: "comgooglemaps://")!
            
            urlComponents.queryItems = [
                URLQueryItem(name: "daddr", value: enderecoSemEspacos),
                URLQueryItem(name: "directionsmode", value: "driving")
            ]
            
            UIApplication.shared.open(urlComponents.url!, options: [:], completionHandler: nil)
            
        }
        else {
            UIApplication.shared.open(URL(string: "https://itunes.apple.com/us/app/id585027354")!, options: [:], completionHandler: nil)
        }
    }
}
