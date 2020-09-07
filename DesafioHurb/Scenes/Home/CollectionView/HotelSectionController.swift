//
//  HotelSectionController.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 07/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import IGListKit

final class HotelSectionController: ListSectionController {
    private var didSelectItem: (Int) -> Void
    private var hotel: HotelDisplay?

    init(didSelectItem: @escaping (Int) -> Void) {
        self.didSelectItem = didSelectItem
        super.init()
        self.inset = UIEdgeInsets(top: 8, left: 8, bottom: 8, right: 8)
    }
    
    override func numberOfItems() -> Int {
        return 1
    }

    override func didUpdate(to object: Any) {
        guard let hotel = object as? DiffableBox<HotelDisplay> else {
            return
        }
        self.hotel = hotel.value
    }

    override func cellForItem(at index: Int) -> UICollectionViewCell {
        guard let hotel = hotel, let context = collectionContext else { fatalError() }

        let cell = context.dequeueReusableCellWithNib(
            of: HotelViewCell.self,
            for: self,
            at: index
        )
        cell.setup(hotel: hotel)
        return cell
    }

    override func sizeForItem(at index: Int) -> CGSize {
        let width = (collectionContext!.containerSize.width - 32) / 2
        return CGSize(width: width, height: HotelViewCell.defaultHeight)
    }

    override func didSelectItem(at index: Int) {
        super.didSelectItem(at: index)
        didSelectItem(section)
    }
}
