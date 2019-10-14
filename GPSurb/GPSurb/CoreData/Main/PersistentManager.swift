//
//  PersistentManager.swift
//  GPSurb
//
//  Created by Gilson Santos on 14/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation
import CoreData

class PersistentManager {
    // MARK: - SINGLETON -
        static let shared = PersistentManager()
        
        private lazy var persistentContainer: NSPersistentContainer = {
            let container = NSPersistentContainer(name: "Tecnol")
            container.loadPersistentStores(completionHandler: { (_, error) in
                if let error = error as NSError? {
                    print("Unresolved error \(error), \(error.userInfo)")
                }
            })
            container.viewContext.mergePolicy = NSMergeByPropertyObjectTrumpMergePolicy
            container.viewContext.undoManager = nil
            container.viewContext.shouldDeleteInaccessibleFaults = true
            container.viewContext.automaticallyMergesChangesFromParent = true
            return container
        }()
        
        lazy var context: NSManagedObjectContext = {
            return self.persistentContainer.viewContext
        }()
        
        private init() {}
    }

    // MARK: - CODE DATA SAVING SUPPORT -
    extension PersistentManager {
        func saveContext () {
            let context = self.persistentContainer.viewContext
            if context.hasChanges {
                do {
                    try context.save()
                } catch {
                    let nserror = error as NSError
                    print("Unresolved error \(nserror), \(nserror.userInfo)")
                }
            }
        }
    }
