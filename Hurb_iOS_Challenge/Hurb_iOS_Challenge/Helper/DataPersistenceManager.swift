//
//  DataPersistenceManager.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 02/06/22.
//

import UIKit
import CoreData

class DataPersistenceManager {
    
    // MARK: - Properties
    
    enum DataBaseError: Error {
        case failToSaveHotelLocally
        case failToFetchLocalHotel
        case failedToDeleteData
        
        var errorDescription: String {
            switch self {
            case .failToSaveHotelLocally:
                return "Falha ao adicionar item à lista."
            case .failToFetchLocalHotel:
                return "Falha ao carregar os itens da lista."
            case .failedToDeleteData:
                return "Falha ao deletar o item da lista."
            }
        }
    }
    
    static let shared = DataPersistenceManager()
    
    
    // MARK: - Helper Methods
    
    func addHotelToLastSeenList(model: HotelResult, completion: @escaping(Result<Void, Error>) -> Void ) {
        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else { return }
        let context = appDelegate.persistentContainer.viewContext
        let hotel = LastSeenHotel(context: context)
        
        hotel.isHotel = model.isHotel ?? false
        hotel.url = model.url
        hotel.tags = model.tags?.first
        hotel.stars = Int16(model.stars ?? 0)
        hotel.name = model.name
        hotel.image = model.image
        hotel.smallDescription = model.smallDescription
        hotel.resultDescription = model.resultDescription
        hotel.huFreeCancelation = model.huFreeCancellation ?? false
        
        do {
            try context.save()
            print("DEBUG: ✅ SAVE HOTEL IN DATABASE SUCCESSED ✅")
            completion(.success(()))
        }
        catch {
            completion(.failure(DataBaseError.failToSaveHotelLocally))
            AlertUtils.showAlert(message: "Falha ao adicionar item à lista. Por favor, tente novamente.")
            print("DEBUG: ❌ SAVE HOTEL IN DATABASE FAILED ❌ WITH ERROR - ", error.localizedDescription)
        }
    }
    
    func fetchHotelsFromDataBaseLocal(completion: @escaping(Result<[LastSeenHotel], Error>) -> Void){
        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else { return }

        let context = appDelegate.persistentContainer.viewContext

        let request: NSFetchRequest<LastSeenHotel>

        request = LastSeenHotel.fetchRequest()

        do {
            let moviesResponse = try context.fetch(request)
            completion(.success(moviesResponse))
            print("DEBUG: ✅ FETCH HOTEL IN DATABASE SUCCESSED ✅ - ", moviesResponse)
        }
        catch {
            completion(.failure(DataBaseError.failToFetchLocalHotel))
            print("DEBUG: ❌ FETCH HOTEL IN DATABASE FAILED ❌")
            AlertUtils.showAlert(message: "Falha ao carregar a lista. Por favor, tente novamente.")
        }
    }
    
    func deleteTitleWith(model: LastSeenHotel, completion: @escaping (Result<Void, Error>)-> Void) {
        
        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else {
            return
        }
        
        let context = appDelegate.persistentContainer.viewContext
        
        
        context.delete(model)
        
        do {
            try context.save()
            print("DEBUG: ✅ DELETE DATABASE HOTEL SUCCESS ✅")
            completion(.success(()))
        } catch {
            print("DEBUG: ❌ DELETE DATABASE HOTEL FAIL ❌")
            completion(.failure(DataBaseError.failedToDeleteData))
        }
        
    }
}

