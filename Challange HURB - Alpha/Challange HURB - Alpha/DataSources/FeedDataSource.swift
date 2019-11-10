//
//  FeedDataSource.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 09/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import UIKit

class FeedDataSource: NSObject {
    
    // MARK: - Properties
    private var hotels: [Experience]
    private var packages: [Experience]
    var hotelsStarsIndex: [Int] = []
    
    // MARK: - Initializers
    init(hotels: [Experience], packages: [Experience]) {
        self.hotels = hotels
        self.packages = packages
        super.init()
        getHotelsStarsIndex()
    }
    
    // MARK: - Methods
    
    /// Gets the number and the type of the different hotels categories
    private func getHotelsStarsIndex() {
        for exp in hotels {
            guard let star = exp.stars else { continue }
            if !hotelsStarsIndex.contains(star) {
                hotelsStarsIndex.append(star)
            }
        }
    }
    
    func hasPackages() -> Bool {
        !packages.isEmpty
    }
}

// MARK: - Extensions
extension FeedDataSource: UITableViewDataSource {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        packages.isEmpty ? hotelsStarsIndex.count : hotelsStarsIndex.count
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "feedTableViewCell") as? FeedTableViewCell else {
            fatalError("Feed table view cell could not be configured")
        }
        if !packages.isEmpty && indexPath.section == 0 {
            cell.currentDataSource = ExperiencesDataSource(experiences: packages)
        } else {
            let starIndex = packages.isEmpty ? indexPath.section : indexPath.section - 1
            let selectedStarHotels = hotels.filter({ ($0.stars) == hotelsStarsIndex[starIndex] })
            cell.currentDataSource = ExperiencesDataSource(experiences: selectedStarHotels)
        }
        cell.setupUI()
        return cell
        
    }
    
}
