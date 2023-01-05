//
//  HotelsViewController.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 05/01/23.
//  Copyright (c) 2023 ___ORGANIZATIONNAME___. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//

import UIKit

protocol HotelsDisplayLogic: AnyObject {
    
    func displaySomething(viewModel: Hotels.Something.ViewModel)
}

class HotelsViewController: UIViewController {
    
    var interactor: HotelsBusinessLogic?
    var router: (NSObjectProtocol & HotelsRoutingLogic & HotelsDataPassing)?
    
//    private lazy var dataSource: UICollectionViewDiffableDataSource<CollectionViewSection<SolutionsSection>.ID, CollectionViewItem.ID>! = nil
//    private lazy var sectionsStore: AnyModelStore<CollectionViewSection<SolutionsSection>> = AnyModelStore([])
//    private lazy var itemsStore: AnyModelStore<CollectionViewItem> = AnyModelStore([])
    
    @IBOutlet private weak var collectionView: UICollectionView!
    
    // MARK: View lifecycle
    
    override func viewDidLoad() {
        super.viewDidLoad()
        doSomething()
    }
    
    // MARK: Routing
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        super.prepare(for: segue, sender: sender)
        
    }
    
    // MARK: Do something
    
    func doSomething() {
        
        let request = Hotels.Something.Request()
        interactor?.doSomething(request: request)
    }
}

extension HotelsViewController: HotelsDisplayLogic {
    
    func displaySomething(viewModel: Hotels.Something.ViewModel) {
        //nameTextField.text = viewModel.name
    }
}
