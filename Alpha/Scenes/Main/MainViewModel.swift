//
//  MainViewModel.swift
//  Alpha
//
//  Created by Theo Mendes on 14/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation
import RxCocoa
import RxSwift
import os.log

class MainViewModel: BaseViewModel, ViewModelType {
    let disposeBag = DisposeBag()

    struct Input {
        let headerRefresh: Observable<Void>
    }
    struct Output {
        let feed: BehaviorRelay<[String]>
    }

    func transform(input: Input) -> Output {
        let elements = BehaviorRelay<[String]>(value: [])

        input.headerRefresh.flatMapLatest { [weak self] () -> Observable<[String]> in
            guard let self = self else { return Observable.just([]) }
            return Observable.just([])
        }.subscribe(onNext: { (items) in
            elements.accept(items)
            }).disposed(by: disposeBag)

        return Output(feed: elements)
    }

}
