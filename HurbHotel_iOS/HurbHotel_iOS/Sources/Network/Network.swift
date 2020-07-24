//
//  Network.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation

//Custumo aplicar a BASE_URL no BuiderSettings, mas para simplicidade vou deixar aqui!
let BASE_URL = "https://www.hurb.com"

//A camada de Networking é mais completa para atender a todos os verbos e tals...
//Gosto de criar uma interface para lidar com ela dentro de cada Sena, fica muito legal,
//mas vou aplicar a forma mais simples agora, até porque usarei apenas um GET.
class Network {
    
    static func get(url: URL, completion: @escaping (Data?, Error?) -> Void) {
        print("==> Request GET: \(url.absoluteString)")
        URLSession.shared.dataTask(with: url) { (data, response, error) in
            if let err = error {
                completion(nil, err)
                return
            }
            
            guard let httpResponse = response as? HTTPURLResponse,
                  (200...299).contains(httpResponse.statusCode) else {
                completion(nil, nil)
                return
            }
            
            completion(data, nil)
        }.resume()
    }
}
