//
//  Observable.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import RxSwift

extension Observable {
    public static func page(
        make nextPage: @escaping (Element?) -> Observable<Element>,
        while hasNext: @escaping (Element) -> Bool,
        when trigger: Observable<Void>
    ) -> Observable<Element> {
        func next(_ fromPage: Element?) -> Observable<Element> {
            return nextPage(fromPage).map { page -> Observable<Element> in
                guard hasNext(page) else { return Observable.just(page) }

                return Observable.concat(
                    [
                        Observable.just(page),
                        Observable.never().takeUntil(trigger),
                        next(page)
                    ]
                )
            }.flatMap { $0 }
        }

        return next(nil)
    }
}
