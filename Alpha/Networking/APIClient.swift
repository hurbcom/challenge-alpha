//
//  APIClient.swift
//  Alpha
//
//  Created by Theo Mendes on 15/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Alamofire
import RxSwift

/// API request errors
enum APIError: Int, Error {
    /// **401** Unauthorized
    case unAuthorized = 401
    /// **404** Not Found
    case notFound = 404
    /// **500** Internal Server Error
    case internalServer = 500
}

/// All the functions avaible to make the API request
class APIClient {
    /**
    A function that unifies all URL requests
     
    This way we can guarantee that there will only be one active request at a time.

    - Parameter route: The route wich the request will be made.
    - Parameter completion: Optional completion closure that is going to be called when the connection finishes or fails

    - Returns: `Void`.
    */
    private static func performRequest(route: APIRouter, completion: @escaping (AFDataResponse<Any>?) -> Void) {
        AF.request(route).validate().responseJSON { (response: AFDataResponse<Any>) in
            completion(response)
        }
    }
    
    /**
    A Reactive funcion that get the "feed" for the city
     
    - Parameter city: The city where you would like hotels and packages to be searched.
    - Parameter page: Default 1, this parameter helps with the pagination, each page return 20 elements

    - Returns: An `Observable` `APIResult` array.
    */
    static func RxGetFeed(forCity city: String,
                          page: Int = 1) -> Observable<[APIResult]> {
        return Observable.create { (observer) -> Disposable in

            performRequest(route: .search(
                parameters: SearchParams(
                    keyword: city,
                    page: page))) { (res) in
                // Verify if the result was a success or not
                switch res?.result {
                case .success:
                    guard let data = res?.data, let utf8Text = String(
                        data: data, encoding: .utf8) else {
                            // If no error is provided by Alamofire,
                            // then return error 500 - Internal Server Error
                            observer.onError(res?.error ?? APIError.internalServer)
                            return
                    }
                    
                    guard let json = utf8Text.data(using: .utf8) else { return }
                    
                    do {
                        let decoder = JSONDecoder()
                        let decodedJson = try decoder.decode(APIRes.self, from: json)
                        observer.onNext(decodedJson.results)
                    } catch (let e) {
                        observer.onError(e)
                    }
                    
                case .failure(let error):
                    if let statusCode = res?.response?.statusCode,
                        let reason = APIError(rawValue: statusCode) {
                        observer.onError(reason)
                    }
                    observer.onError(error)
                case .none:
                    observer.onError(res?.error ?? APIError.internalServer)
                }
            }
            return Disposables.create()
        }
    }
}
