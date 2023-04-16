//
//  CoreDataManager.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 15/04/23.
//

import Foundation
import CoreData
import UIKit
class CoreDataManager {
    static let Manager = CoreDataManager()
    var context = (UIApplication.shared.delegate as! AppDelegate).persistentContainer.viewContext
    private var favoritesArray : [SavedItem] = []
    
    //carregar favoritos
    func getItemsFromCoreData()-> [SavedItem] {
        let request: NSFetchRequest<SavedItem> = SavedItem.fetchRequest()
        do {
            let savedItems = try context.fetch(request)
            favoritesArray = savedItems
        } catch  {
            print("erro ao carregar dados do coredata" + error.localizedDescription)
        }
        return favoritesArray
    }
    
    // adicionar aos favoritos
    func createNewFavorite(titulo:String, descricao:String,img:String,categoria:String) {
        let newSavedItem = SavedItem(context: self.context)
        newSavedItem.titulo =  titulo
        newSavedItem.descricao =  descricao
        newSavedItem.urlDaImagem = img
        newSavedItem.categoria = categoria
        saveOnCoreData()
    }
    
    public func saveOnCoreData() {
        do {
            try context.save()
            print("salvo")
        } catch  {
            print(error.localizedDescription)
        }
    }
    
    func deleteItem(onArray model:[SavedItem], at indexPath:Int) {
        context.delete(model[indexPath])
        var items = model
        items.remove(at: indexPath)
    }

}
