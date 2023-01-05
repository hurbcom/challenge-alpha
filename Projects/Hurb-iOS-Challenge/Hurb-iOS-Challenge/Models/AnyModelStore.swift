//
//  AnyModelStore.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 05/01/23.
//

import Foundation

protocol ModelStore {
    
    associatedtype Model: Identifiable
    
    func fetchByID(_ id: Model.ID) -> Model
}

class AnyModelStore<Model: Identifiable>: ModelStore {
    
    private var models = [Model.ID: Model]()
    
    init(_ models: [Model]) {
        self.models = models.groupingByUniqueID()
    }
    
    func fetchByID(_ id: Model.ID) -> Model {
        return self.models[id]!
    }
    
    func append(_ newModels: [Model]) {
        
        self.models.merge(newModels.groupingByUniqueID()) { (_, new) in new }
    }
    
    func replace(_ newModel: Model, for key: Model.ID) {
        
        self.models[key] = newModel
    }
}

extension Sequence where Element: Identifiable {
    func groupingByID() -> [Element.ID: [Element]] {
        return Dictionary(grouping: self, by: { $0.id })
    }
    
    func groupingByUniqueID() -> [Element.ID: Element] {
        return Dictionary(uniqueKeysWithValues: self.map { ($0.id, $0) })
    }
}
