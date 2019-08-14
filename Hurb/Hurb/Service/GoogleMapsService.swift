//
//  GoogleMapsService.swift
//  Hurb
//
//  Created by Alexandre Papanis on 13/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import Alamofire
import SwiftyJSON

enum ErroWS {
    case http400
    case http500
    case timeout
}

class GoogleMapsService {
    
    //Pegar a latitude, longitude e o endereço formatado a partir do endereço
    func getLatitudeLongitude(endereco: String,
                              successCompletion: @escaping (_ location: Location, _ address: String)->Void,
                              errorCompletion: @escaping (_ erro: ErroWS)->Void){
        
        var enderecoSemEspacos = endereco.replacingOccurrences(of: " ", with: "+")
        enderecoSemEspacos = enderecoSemEspacos.replacingOccurrences(of: "/", with: "%2F")
        enderecoSemEspacos = enderecoSemEspacos.folding(options: .diacriticInsensitive, locale: .current)
        
        let url = String.init(format: Defines.GOOGLE_MAPS_GEOCODE, enderecoSemEspacos, Defines.GOOGLE_KEY)
        
        var request = URLRequest(url: URL(string: url)!)
        request.httpMethod = "GET"
        request.timeoutInterval = 20
        
        Alamofire.request(request as URLRequestConvertible).responseJSON { response in
            
            if let status = response.response?.statusCode {
                switch(status){
                case 200:
                    print("getLatitudeLongitude sucesso")
                    
                    switch (response.result) {
                    case .success:
                        if let result = response.result.value {
                            let json = JSON(result)
                            print(json)
                            
                            let jsonDecoder = JSONDecoder()
                            
                            do {
                                
                                let geocodingResponse = try jsonDecoder.decode(GeocondingResponse.self, from: json.rawData())
                                if let results = geocodingResponse.results {
                                    if let result = results.first {
                                        if let location = result.geometry.location {
                                            if location.lat != 0.0 || location.lng != 0.0 {
                                                successCompletion(location, result.formattedAddress!)
                                            }
                                        }
                                        
                                        if let bounds = result.geometry.bounds {
                                            guard let southwest = bounds.southwest else { return }
                                            guard let northeast = bounds.northeast else { return }
                                            
                                            let coordinate = Location(lat: (southwest.lat + northeast.lat)/2, lng: (southwest.lng + northeast.lng)/2)
                                            successCompletion(coordinate, result.formattedAddress!)
                                        }
                                    }
                                }
                                
                            } catch {
                                print(error)
                            }
                            
                        }
                    case .failure(let error):
                        if error._code == NSURLErrorTimedOut {
                            print("Request timeout!")
                            errorCompletion(.timeout)
                        } else {
                            print("Erro Genérico")
                            errorCompletion(.http500)
                        }
                        
                    }
                    
                default:
                    //completion(false)
                    print("error with response status: \(status)")
                    errorCompletion(.http500)
                }
            }
            
        }
    }
}
