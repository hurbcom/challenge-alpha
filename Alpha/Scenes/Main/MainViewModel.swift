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
        let feed: BehaviorRelay<[FeedSection]>
    }

    func transform(input: Input) -> Output {
        let elements = BehaviorRelay<[FeedSection]>(value: [])

        input.headerRefresh.flatMapLatest { () -> Observable<[APIResult]> in
            return APIClient.RxGetFeed(forCity: "buzios", page: 1)
        }.subscribe(
            onNext: { items in
                elements.accept(self.handleFeed(withRawElements: items))
            }
        ).disposed(by: disposeBag)

        return Output(feed: elements)
    }

    func handleFeed(withRawElements elements: [APIResult]) -> [FeedSection] {
        var feed: [FeedSection] = []
        var stars: [Int: [APIResult]] = [:]

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

    func splitHotels(withStarsDic dic: [Int: [APIResult]]) -> [FeedSection] {
        var hotelsFeed: [FeedSection] = []

        for (key, value) in dic {
            let sortedHotels = value.sorted(by: { $0.price.discount < $1.price.discount })
            if key == 1 {
                hotelsFeed.append(FeedSection(header: "\(String(key)) \(L10n.Feed.Section.star)",
                                              subTitle: nil,
                                              items: [.Star(hotels: sortedHotels)]))
            } else {
                hotelsFeed.append(FeedSection(header: "\(String(key)) \(L10n.Feed.Section.stars)",
                subTitle: nil,
                items: [.Star(hotels: sortedHotels)]))
            }
        }

        // DESC sort order
        hotelsFeed = hotelsFeed.sorted(by: { $0.header > $1.header})

        return hotelsFeed
    }

}
