//
//  HomeInteractorTests.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 15/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

@testable import DesafioHurb
import RxCocoa
import RxSwift
import RxTest
import XCTest

final class HomeInteractorTests: XCTestCase {
    private var sut: HomeInteractor!
    private var disposeBag: DisposeBag!

    override func setUp() {
        super.setUp()
        setupHomeInteractor()
    }

    private func setupHomeInteractor() {
        disposeBag = DisposeBag()
        sut = HomeInteractor(fetchHotelsUseCase: <#T##FetchHotelsUseCase#>)
    }

    func testExample() {}
}
