//
//  APIClient.swift
//  Alpha
//
//  Created by Theo Mendes on 15/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Alamofire
import RxSwift

enum APIError: Int, Error {
    case unAuthorized = 401
    case notFound = 404
    case unknow = 500
}

class APIClient {

    private static func performRequest(route: APIRouter, completion: @escaping (AFDataResponse<Any>?) -> Void) {
        AF.request(route).validate().responseJSON { (response: AFDataResponse<Any>) in
            completion(response)
        }
    }

    static func RxGetFeed(forCity city: String, page: Int = 1) -> Observable<[APIResult]> {
        return Observable.create { (observer) -> Disposable in

            let _ = performRequest(route: .search(parameters: SearchParams(keyword: city, page: page))) { (res) in
                switch res?.result {
                case .success:
                    guard let data = res?.data,
                        let utf8Text = String(data: data, encoding: .utf8) else {
                            // if no error provided by alamofire return .notFound error instead.
                            // .notFound should never happen here?
                            observer.onError(res?.error ?? APIError.notFound)
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
                    observer.onError(res?.error ?? APIError.notFound)
                }
            }
            return Disposables.create()
        }
    }
}
