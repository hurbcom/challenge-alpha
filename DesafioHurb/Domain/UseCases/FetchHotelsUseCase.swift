//
//  FetchHotelsUseCase.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 04/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import RxSwift
import Alamofire

enum ParseError: String, Error {
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
    case unknow = 0
    case badRequest = 400
    case unAuthorized = 401
    case forbidden = 403
    case notFound = 404
    case timeout = 408
    
    var localizedDescription: String {
        switch self {
        case .unknow:
            return "Algo deu errado, verifique sua conexão e tente novamente."
        case .badRequest:
            return "Requisição incorreta."
        case .unAuthorized:
            return "Sem autorização para efetuar a operação solicitada, tente novamente"
        case .forbidden:
            return "Solicitação não permitida."
        case .notFound:
            return "O recurso solicitado não foi encontrado, tente novamente."
        case .timeout:
            return "O tempo limite da operação foi atingido."
            
        }
    }
}

protocol FetchHotelsUseCase {
    func fetchHotels(page: Int) -> Single<FetchHotelsResponse>
}

final class FetchHotelsUseCaseImpl: FetchHotelsUseCase {
    
    private let urlRequest = "https://www.hurb.com/search/api?q=buzios"
    
    func fetchHotels(page: Int) -> Single<FetchHotelsResponse> {
        
        return Single.create(subscribe: { completion in
            AF.request("\(self.urlRequest)&page=\(page)")
                .validate()
                .responseJSON { response in
                    LoggerInterceptor.shared.intercept(request: response.request!)
                    LoggerInterceptor.shared.intercept(response: response.response,
                                                       data: response.data,
                                                       error: response.error,
                                                       for: response.request!)
                    switch response.result {
                    case .success:
                        guard let data = response.data else {
                            return completion(.error(response.error ?? ParseError.notFound))
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
