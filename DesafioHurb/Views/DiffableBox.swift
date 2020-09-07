//
//  DiffableBox.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 07/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import IGListKit

final class DiffableBox<T>: ListDiffable {
    let value: T
    let identifier: NSObjectProtocol
    let compare: (T, T) -> Bool

    init(value: T, identifier: NSObjectProtocol, compare: @escaping (T, T) -> Bool) {
        self.value = value
        self.identifier = identifier
        self.compare = compare
    }

    func diffIdentifier() -> NSObjectProtocol {
        return identifier
    }

    func isEqual(toDiffableObject object: ListDiffable?) -> Bool {
        guard let other = object as? DiffableBox<T> else {
            return false
        }

        return compare(value, other.value)
    }
}

extension DiffableBox: Equatable where T: Equatable {
    convenience init(value: T, identifier: NSObjectProtocol) {
        self.init(value: value, identifier: identifier, compare: ==)
    }

    static func == (lhs: DiffableBox<T>, rhs: DiffableBox<T>) -> Bool {
        return lhs.value == rhs.value
    }
}
