//
//  FetchHotelsUseCase.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 04/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import RxSwift
import Alamofire

enum CustomError: String, Error {
    case notFound
    case parseError
    
    var localizedDescription: String {
        switch self {
        case .notFound:
            return "Dados não encontrados, tente novamente."
        case .parseError:
            return "Não foi possível efetuar a conversão de dados recebidos, tente novamente."
        }
    }
}

enum NetWorkError: Int, Error {
    case unAuthorized = 401
    case notFound = 404
    
    var localizedDescription: String {
        switch self {
        case .unAuthorized:
            return "Sem autorização para efetuar a operação solicitada, tente novamente"
        case .notFound:
            return "O recurso solicitado não foi encontrado, tente novamente."
        }
    }
}

protocol FetchHotelsUseCase {
    func fetchHotels() -> Single<FetchHotelsResponse>
}

final class FetchHotelsUseCaseImpl: FetchHotelsUseCase {
    
    private let urlRequest = "https://www.hurb.com/search/api?q=buzios&page=1"
    
    private let disposeBag = DisposeBag()
    
    func fetchHotels() -> Single<FetchHotelsResponse> {
        
        return Single.create(subscribe: { completion in
            AF.request(self.urlRequest)
                .validate()
                .responseJSON { response in
                    switch response.result {
                    case .success:
                        guard let data = response.data else {
                            return completion(.error(response.error ?? CustomError.notFound))
                        }
                        do {
                            let salePoint = try JSONDecoder().decode(FetchHotelsResponse.self, from: data)
                            return completion(.success(salePoint))
                        } catch {
                            return completion(.error(error))
                        }
                    case .failure(let error):
                        if let statusCode = response.response?.statusCode,
                            let reason = NetWorkError(rawValue: statusCode) {
                            return completion(.error(reason))
                        }
                        return completion(.error(error))
                    }
            }
            return Disposables.create()
        })
    }
}
