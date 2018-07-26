//
//  ExtensionSequence.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 26/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

extension Sequence {
    
    func groupBy<G: Hashable>(closure: (Iterator.Element)->G) -> [G: [Iterator.Element]] {
        var results = [G: Array<Iterator.Element>]()
        
        forEach {
            let key = closure($0)
            
            if var array = results[key] {
                array.append($0)
                results[key] = array
            } else {
                results[key] = [$0]
            }
        }
        return results
    }
}
