//
//  ListViewController.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 21/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import UIKit

protocol ListViewProtocol {
    func setupView()->Void
}

class ListViewController: UIViewController {

    @IBOutlet weak var collectionView: UICollectionView!
    @IBOutlet weak var activityIndicatorView: UIActivityIndicatorView!
    @IBOutlet weak var errorView: UIStackView!
    
    var viewModel: ListViewModel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        self.viewModel = ListViewModel(view: self)
        self.setup()
        self.customize()
        self.setupCollectionView()
        self.viewModel.getHotels(with: "buzios")
    }
}

// MARK: - Setup Methods

extension ListViewController {
    
    func setup() {
        self.title = "Hotéis"
        self.collectionView.hide()
        self.errorView.hide()
    }
    
    func customize() {
        self.view.backgroundColor = Theme.backgroundColor
        self.collectionView.backgroundColor = Theme.backgroundColor
        self.customizeNavigationController()
    }
    
    @IBAction func tryAgainButtonTapped() {
        self.viewModel.getHotels(with: "buzios")
    }
    
}


// MARK: - Collection View Methods

extension ListViewController: UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout {
    
    func setupCollectionView() {
        self.collectionView.delegate = self
        self.collectionView.dataSource = self
        self.collectionView.contentInset = UIEdgeInsets(top: 8, left: 0, bottom: 0, right: 0)
        self.collectionView.register(UINib(nibName: ListHotelCollectionViewCell.identifier, bundle: nil), forCellWithReuseIdentifier: ListHotelCollectionViewCell.identifier)
    }
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return self.viewModel.sortedHotels.count
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let destination = StoryboardHelper.detailVC as! HotelDetailViewController
        destination.selectedHotel = self.viewModel.sortedHotels[indexPath.section][indexPath.row]
        self.navigationController?.pushViewController(destination, animated: true)
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.viewModel.sortedHotels[section].count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: ListHotelCollectionViewCell.identifier, for: indexPath) as! ListHotelCollectionViewCell
        cell.config(with: self.viewModel.sortedHotels[indexPath.section][indexPath.row])//[indexPath.row])
        return cell
    }
    
   func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {

    
      switch kind {
      case UICollectionView.elementKindSectionHeader:
        guard let headerView = collectionView.dequeueReusableSupplementaryView( ofKind: kind, withReuseIdentifier: "ListHotelHeaderCollectionReusableView", for: indexPath) as? ListHotelHeaderCollectionReusableView
          else {
            fatalError("Invalid view type")
        }

        let searchTerm = self.viewModel.sortedHotelsTitles[indexPath.section]
        headerView.sectionTitleLabel.text = searchTerm
        return headerView
      default:
        assert(false, "Invalid element type")
      }
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, referenceSizeForHeaderInSection section: Int) -> CGSize {
        if self.viewModel.sortedHotels[section].count == 0 {
            return .zero
        } else {
            return CGSize(width: self.collectionView.frame.width, height: 40)
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let width = self.collectionView.frame.width
        return CGSize(width: width, height: 130)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, insetForSectionAt section: Int) -> UIEdgeInsets {
        if self.viewModel.sortedHotels[section].count == 0 {
            return .zero
        } else {
            return UIEdgeInsets(top: 0, left: 0, bottom: 16, right: 0)
        }
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return 16
    }
    
}


// MARK: - Protocol Methods

extension ListViewController: ListViewProtocol {
    
    func setupView() {
        
        self.activityIndicatorView.stopAnimating()
        self.errorView.hide()
        self.collectionView.hide()
        
        switch self.viewModel.status {
        case .loading:
            self.activityIndicatorView.startAnimating()
        case .loaded:
            self.collectionView.reloadData()
            self.collectionView.show()
        case .error:
            self.errorView.show()
        default:
            print("default")
        }
        
    }
    
}
