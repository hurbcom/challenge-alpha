//
//  DetailInteractorTests.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 19/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

@testable import DesafioHurb
import RxCocoa
import RxSwift
import RxTest
import XCTest

final class DetailInteractorTests: XCTestCase {
    private var sut: DetailInteractor!
    private var disposeBag: DisposeBag!

    override func setUp() {
        super.setUp()
        setupDetailInteractor()
    }

    private func setupDetailInteractor() {
        disposeBag = DisposeBag()
    }

    func testExample() {}
}
