//
//  HotelSearchViewController.swift
//  HotelSearchiOS
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import UIKit

import HotelSearch

final public class HotelSearchViewModel {
    
    var text: String = ""
    
    private let hotelSearcher: HotelSearcher
    private let searchSuffix = "&page="
    private var currentPage = 1
    
    public init(hotelSearcher: HotelSearcher) {
        self.hotelSearcher = hotelSearcher
    }
    
    func searchHotel() {
        let searchText = self.text + self.searchSuffix + String(describing: self.currentPage)
        self.hotelSearcher.searchHotel(with: searchText.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) ?? searchText) { [weak self] result in
            switch result {
            case let .success(hotels):
                print(hotels)
            case let .failure(error):
                print(error)
            }
        }
    }
    
}

final public class HotelSearchViewController: UIViewController {
    
    // MARK: - IBOutlets
    
    @IBOutlet weak var viewInput: UIView!
    @IBOutlet weak var textField: UITextField!
    @IBOutlet weak var btnSearch: UIButton!
    @IBOutlet weak var collectionView: UICollectionView!
    
    // MARK: - IBActions
    
    @IBAction func btnSearchAction(_ sender: UIButton) {
        self.viewModel.searchHotel()
    }
    
    // MARK: - Properties
    
    private let viewModel: HotelSearchViewModel
    
    // MARK: - Life Cycle
    
    public init(viewModel: HotelSearchViewModel) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: Bundle(for: HotelSearchViewController.self))
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    public override func viewDidLoad() {
        super.viewDidLoad()
        self.setupUI()
    }

}

private extension HotelSearchViewController {
    
    // MARK: - Setup
    
    func setupUI() {
        self.viewInput.layer.cornerRadius = 4
        
        self.textField.addTarget(self, action: #selector(textFieldDidChange(_:)), for: .editingChanged)
    }
    
    // MARK: - Objc methods
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        self.viewModel.text = textField.text ?? ""
    }
    
}
