//
//  APIClient.swift
//  Alpha
//
//  Created by Theo Mendes on 15/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Alamofire

class APIClient {

    @discardableResult
    private static func performRequest(route: APIRouter, completion: @escaping (AFDataResponse<Any>?)-> Void) -> DataRequest {
        return AF.request(route).validate().responseJSON { (response: AFDataResponse<Any>) in
            completion(response)
        }
    }

    static func getFeed(forCity city: String? = "buzios", completion: @escaping ([APIResult]?, Error?) -> Void) {
        performRequest(route: .search(parameters: SearchParams(keyword: city, page: 1))) { (res) in
            if let error = res?.error {
                completion(nil, error)
            }
//            guard let values = res?.value else { return }
            guard let data = res?.data, let utf8Text = String(data: data, encoding: .utf8) else { return }
            guard let json = utf8Text.data(using: .utf8) else { return }

            do {
                let decoder = JSONDecoder()
                let decodedJson = try decoder.decode(APIRes.self, from: json)
                dump(decodedJson)
                completion(decodedJson.results, nil)
            } catch (let e) {
                completion(nil, e)
            }

        }
    }
}
