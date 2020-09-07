//
//  ListCollectionContext.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 07/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import IGListKit

extension ListCollectionContext {
    func dequeueReusableCellWithNib<Cell: UICollectionViewCell & NibLoadable>(
        of type: Cell.Type,
        for sectionController: ListSectionController,
        at index: Int
    ) -> Cell {
        guard
            let cell = dequeueReusableCell(
                withNibName: type.nibName,
                bundle: type.nibBundle,
                for: sectionController,
                at: index
            ) as? Cell
        else {
            fatalError("Couldn't dequeue cell of type: \(type.self)")
        }

        return cell
    }
}
