//
//  HotelHeaderSectionController.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import IGListKit

final class HotelHeaderSectionController: ListSectionController {
    private var hotelHeader: HotelHeaderDisplay?

    override func didUpdate(to object: Any) {
        guard let hotelHeader = object as? DiffableBox<HotelHeaderDisplay> else {
            return
        }
        self.hotelHeader = hotelHeader.value
    }

    override func cellForItem(at index: Int) -> UICollectionViewCell {
        guard let hotelHeader = hotelHeader, let context = collectionContext else { fatalError() }

        let cell = context.dequeueReusableCellWithNib(
            of: HotelHeaderViewCell.self,
            for: self,
            at: index
        )
        cell.setup(hotelHeader: hotelHeader)
        return cell
    }

    override func sizeForItem(at index: Int) -> CGSize {
        let width = collectionContext!.containerSize.width
        return CGSize(width: width, height: HotelHeaderViewCell.defaultHeight)
    }
}
