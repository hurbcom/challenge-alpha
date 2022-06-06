//
//  NetworkingService.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 22/05/22.
//

import Foundation
import Alamofire

final class NetworkingService {
    
    // MARK: - Properties
    static let shared = NetworkingService()
    
    private let hotelsBaseURL: URL = Bundle.main.url(forResource: Constants.JSONFilesNames.HOTEL_JSON_FILE_NAME, withExtension: Constants.JSONFilesNames.JSON_TYPE)!
    
    // MARK: - Helper Methods
    func fetchHotels(completion: @escaping (Result<[HotelResult]?, Error>) -> Void) {
        AF.request(hotelsBaseURL,
                   method: .get,
                   parameters: nil,
                   encoding: URLEncoding.default,
                   headers: nil,
                   interceptor: nil,
                   requestModifier: .none).response { response in ()
            guard let data = response.data else { return }
            do {
                let hotelsResponse = try JSONDecoder().decode(HotelReponse.self, from: data)
                let hotelResult = hotelsResponse.results
                completion(.success(hotelResult))
                print("DEBUG: ✅ REQUEST SUCCESS ✅ - \n", hotelResult!)
            }
            catch {
                print("DEBUG: ❌ REQUEST ERROR ❌ - ", error.localizedDescription)
                completion(.failure(error))
            }
        }
    }
}
