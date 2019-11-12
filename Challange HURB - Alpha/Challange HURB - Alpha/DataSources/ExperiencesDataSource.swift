//
//  HotelsDataSource.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 07/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import UIKit

/// Responsible to provide data to the experiences Collection View
class ExperiencesDataSource: NSObject {
    
    // MARK: - Properties
    var experiences: [Experience]
    
    // MARK: - Methods
    init(experiences: [Experience]) {
        self.experiences = experiences
    }
}

    // MARK: - Extensions

extension ExperiencesDataSource: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        experiences.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "experiencesCollectionViewCell",
                                                            for: indexPath) as? ExperienceCollectionViewCell else {
            debugPrint("Experience Collection View Cell has no identifier")
            fatalError()
        }
        cell.experience = experiences[indexPath.row]
        cell.accessibilityIdentifier = "Experience\(indexPath)"
        cell.isAccessibilityElement = true
        cell.setUpUI()
        return cell
    }
}
