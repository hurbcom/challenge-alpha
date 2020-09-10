//
//  HotelImageSectionController.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 09/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import IGListKit

final class HotelImageSectionController: ListSectionController {
    private var didSelectItem: (Int) -> Void
    private var hotelGallery: Gallery?

    init(didSelectItem: @escaping (Int) -> Void) {
        self.didSelectItem = didSelectItem
    }

    override func didUpdate(to object: Any) {
        guard let hotelGallery = object as? DiffableBox<Gallery> else {
            return
        }
        self.hotelGallery = hotelGallery.value
    }

    override func cellForItem(at index: Int) -> UICollectionViewCell {
        guard let hotelGallery = hotelGallery, let context = collectionContext else { fatalError() }

        let cell = context.dequeueReusableCellWithNib(
            of: HotelImageViewCell.self,
            for: self,
            at: index
        )
        cell.setup(hotelImage: URL(string: hotelGallery.url))
        return cell
    }

    override func sizeForItem(at index: Int) -> CGSize {
        return HotelImageViewCell.defaultSize
    }

    override func didSelectItem(at index: Int) {
        super.didSelectItem(at: index)
        didSelectItem(section)
    }
}
