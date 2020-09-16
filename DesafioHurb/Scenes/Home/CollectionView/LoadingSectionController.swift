//
//  LoadingSectionController.swift
//  Cardpay
//
//  Created by Edson Aparecido Guido on 05/08/20.
//

import IGListKit

class LoadingSectionController: ListSectionController {
    private let cellHeight: CGFloat

    init(cellHeight: CGFloat) {
        self.cellHeight = cellHeight
    }

    override func cellForItem(at index: Int) -> UICollectionViewCell {
        guard let cell = collectionContext?.dequeueReusableCellWithNib(
            of: LoadingViewCell.self,
            for: self,
            at: section
        )
        else { fatalError() }
        cell.startAnimating()
        return cell
    }

    override func sizeForItem(at index: Int) -> CGSize {
        let width = collectionContext!.containerSize.width
        return CGSize(width: width, height: cellHeight)
    }
}
