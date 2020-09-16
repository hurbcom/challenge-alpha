//
//  HomeViewModelTests.swift
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

final class HomeViewModelTests: XCTestCase {
    private var sut: HomeViewModel!
    private var disposeBag: DisposeBag!

    override func setUp() {
        super.setUp()
        setupHomeViewModel()
    }

    override func tearDown() {
        super.tearDown()
    }

    private func setupHomeViewModel() {
        disposeBag = DisposeBag()
    }

    func testExample() {}
}
