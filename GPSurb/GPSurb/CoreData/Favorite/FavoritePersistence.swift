//
//  FavoritePersistence.swift
//  GPSurb
//
//  Created by Gilson Santos on 23/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation
import CoreData

class FavoritePersistence {
    private var fetchRequest: NSFetchRequest<FavoriteDB> = FavoriteDB.fetchRequest()
}

// MARK: - METHODS PUBLICS -
extension FavoritePersistence {
    
    public func createOrRemoveFavoriteDataBase(object: ResultViewData) {
        if let favoriteResult = self.getFavoriteBySku(sku: object.sku) {
            self.removeFavoriteDataBase(favoriteResult)
        } else {
            self.parseToDataBase(object: object)
        }
    }
    
    public func fetchMoviesDataBase() -> [ResultViewData]? {
        if let favoriteListDB = self.getFavoriteList(), favoriteListDB.count > 0 {
            return self.parseToViewData(favoriteDB: favoriteListDB)
        }
        return nil
    }
    
    public func isFavorite(sku: String) -> Bool {
        return self.isExistFavorite(sku: sku)
    }
}

// MARK: - METHODS AUX -
extension FavoritePersistence {
    private func getFavoriteBySku(sku: String) -> FavoriteDB? {
        self.fetchRequest.predicate = NSPredicate(format: "sku == %@", sku)
        do {
            let result = try PersistentManager.shared.context.fetch(self.fetchRequest)
            if let favoriteDb = result.first {
                return favoriteDb
            }
        } catch {
            return nil
        }
        return nil
    }

    private func isExistFavorite(sku: String) -> Bool {
        self.fetchRequest.predicate = NSPredicate(format: "sku == %@", sku)
        do {
            let result = try PersistentManager.shared.context.count(for: self.fetchRequest)
            return result > 0
        } catch {
            return false
        }
    }

    private func getFavoriteList() -> [FavoriteDB]? {
        do {
            return try PersistentManager.shared.context.fetch(self.fetchRequest)
        } catch {
            return nil
        }
    }

    private func removeFavoriteDataBase(_ dataBase: FavoriteDB) {
        PersistentManager.shared.context.delete(dataBase)
        PersistentManager.shared.saveContext()
    }
}

// MARK: - PARSE VIEWDATA FROM DATABASE -
extension FavoritePersistence {
    private func parseToDataBase(object: ResultViewData) {
        let favorite = FavoriteDB(context: PersistentManager.shared.context)
        favorite.sku = object.sku
        favorite.destinationName = object.destinationName
        favorite.offerName = object.offerName
        favorite.oldPrice = object.oldPrice
        favorite.newPrice = object.newPrice
        favorite.urlImageCard = object.urlImageCard
        favorite.freeCancellation = object.freeCancellation
        favorite.smallDescription = object.smallDescription
        favorite.descriptionFavorite = object.description
        favorite.type = object.type.rawValue
        favorite.stars = object.stars
        object.amenities.forEach { (element) in
            favorite.addToAmenities(self.parseAmenitiesFromDataBase(name: element))
        }
        object.gallery.forEach { (element) in
            favorite.addToGallery(self.parseGalleryFromDataBase(name: element))
        }
        PersistentManager.shared.saveContext()
    }
    
    private func parseAmenitiesFromDataBase(name: String) -> AmenitiesDB {
        let amenities = AmenitiesDB(context: PersistentManager.shared.context)
        amenities.name = name
        return amenities
    }
    
    private func parseGalleryFromDataBase(name: String) -> GalleryDB {
        let gallery = GalleryDB(context: PersistentManager.shared.context)
        gallery.name = name
        return gallery
    }
}

// MARK: - PARSE DATABASE FROM VIEWDATA -
extension FavoritePersistence {
    private func parseToViewData(favoriteDB: [FavoriteDB]) -> [ResultViewData] {
        var resultList = [ResultViewData]()
        favoriteDB.forEach { (favoriteRow) in
            var result = ResultViewData()
            result.sku = favoriteRow.sku ?? ""
            result.destinationName = favoriteRow.destinationName ?? ""
            result.offerName = favoriteRow.offerName ?? ""
            result.oldPrice = favoriteRow.oldPrice ?? ""
            result.newPrice = favoriteRow.newPrice ?? ""
            result.urlImageCard = favoriteRow.urlImageCard ?? ""
            result.freeCancellation = favoriteRow.freeCancellation
            result.smallDescription = favoriteRow.smallDescription ?? ""
            result.description = favoriteRow.descriptionFavorite ?? ""
            result.type = TypeFilter(rawValue: favoriteRow.type ?? "") ?? TypeFilter.hotel
            result.stars = favoriteRow.stars
            if let amenitiesDB = favoriteRow.amenities {
                amenitiesDB.forEach { (element) in
                    if let elementResult = element as? AmenitiesDB {
                        result.amenities.append(elementResult.name ?? "")
                    }
                }
            }
            if let galleryDB = favoriteRow.gallery {
                galleryDB.forEach { (element) in
                    if let elementResult = element as? GalleryDB {
                        result.gallery.append(elementResult.name ?? "")
                    }
                }
            }
            resultList.append(result)
        }
        return resultList
    }
}
