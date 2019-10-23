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

class FeedViewModel: BaseViewModel, ViewModelType {
    // MARK: - Properties

    let disposeBag = DisposeBag()

    // MARK: - Input Output
    struct Input {
        let refresh: Observable<Void>
    }
    struct Output {
        let feed: BehaviorRelay<[FeedSection]>
        let isLoading: BehaviorRelay<Bool>
    }

    func transform(input: Input) -> Output {
        let elements = BehaviorRelay<[FeedSection]>(value: [])
        let isLoading = BehaviorRelay<Bool>(value: true)

        input.refresh.flatMapLatest({[weak self] () -> Observable<[Deal]> in
            guard let self = self else { return Observable.just([]) }
            return self.request(query: "buzios", page: 1)
        }).subscribe(onNext: { items in
                elements.accept(self.handleFeed(withDealArray: items))
                isLoading.accept(false)
        }).disposed(by: disposeBag)

        return Output(feed: elements, isLoading: isLoading)
    }

    /**
    Perform the request with the provider

    - Parameters:
       - query: The city to be searched
       - page: The page to retrive the results

    - Returns: An Observable `Deal` array
    */
    func request(query: String, page: Int) -> Observable<[Deal]> {
        return provider
            .search(query: query, page: page)
            .map { $0.results }
            .asObservable()
    }

    /**
    Get the Deal array and split into Feed sections

    - Parameters:
       - elements: The Deal array to be splited

    - Returns: An `FeedSection` Array
    */
    func handleFeed(withDealArray elements: [Deal]) -> [FeedSection] {
        var feed: [FeedSection] = []
        var stars: [Int: [Deal]] = [:]

        // Split package from hotel
        let packages = elements.filter({ (element) in
            if element.isPackage == true {
                return true
            }
            guard let star = element.stars else { return false }
            // Split hotels into different stars
            if let _ = stars[star] {
                stars[star]?.append(element)
            } else {
                stars[star] = [element]
            }
            return false
        })

        let packageFeed = FeedSection(header: L10n.Feed.Section.Package.title,
                                      subTitle: L10n.Feed.Section.Package.subtitle,
                                      items: [.Package(packages: packages)])

        let hotelsFeed = splitHotels(withStarsDic: stars)

        feed.append(packageFeed)
        feed = feed + hotelsFeed

        return feed
    }

    /**
    Group the Deals by hotel star

    - Parameters:
       - dic: The Deals dictionary

    - Returns: An `FeedSection` Array
    */
    func splitHotels(withStarsDic dic: [Int: [Deal]]) -> [FeedSection] {
        var hotelsFeed: [FeedSection] = []

        for (key, value) in dic {
            let sortedHotels = value.sorted(by: { $0.price.discount < $1.price.discount })
            if key == 1 {
                hotelsFeed.append(
                    FeedSection(header: "\(String(key)) \(L10n.Feed.Section.star)",
                        subTitle: nil,
                        items: [
                            .Star(hotels: sortedHotels)
                    ]))
            } else {
                hotelsFeed.append(
                    FeedSection(header: "\(String(key)) \(L10n.Feed.Section.stars)",
                        subTitle: nil,
                        items: [
                            .Star(hotels: sortedHotels)
                    ]))
            }
        }

        // DESC sort order
        hotelsFeed = hotelsFeed.sorted(by: { $0.header > $1.header})

        return hotelsFeed
    }

}
