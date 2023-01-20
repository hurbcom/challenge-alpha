//
//  PackageListInteractorMock.swift
//  Challenge Alpha iOSTests
//
//  Created by Yuri Strack on 19/01/23.
//

import Foundation
import Combine
import HUGraphQL
@testable import Challenge_Alpha_iOS

final class PackageListInteractorMock: PackageListInteractorProtocol {
    func getPackages(query: String, pagination: HUGraphQL.SearchInputPagination?) -> AnyPublisher<[Challenge_Alpha_iOS.PackageResult], Error> {
        let data = mockedPackages
        
        let subject = PassthroughSubject<[PackageResult], Error>()
        
        subject.send(data)
        subject.send(completion: .finished)
        
        return subject.eraseToAnyPublisher()
    }
}
