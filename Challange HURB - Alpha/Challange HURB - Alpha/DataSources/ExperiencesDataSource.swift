//
//  HotelsDataSource.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 07/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import UIKit

class ExperiencesDataSource: NSObject {
    var experiences: [Experience]
    
    init(experiences: [Experience]) {
        self.experiences = experiences
    }
}

extension ExperiencesDataSource: UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        experiences.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "experiencesCollectionViewCell", for: indexPath) as? ExperienceCollectionViewCell else {
            debugPrint("Experience Collection View Cell has no identifier")
            fatalError()
        }
        cell.experience = experiences[indexPath.row]
        cell.setUpUI()
        return cell
    }
    
    
}
