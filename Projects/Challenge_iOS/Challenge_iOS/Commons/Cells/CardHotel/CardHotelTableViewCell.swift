//
//  CardHotelTableViewCell.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 13/11/22.
//

import UIKit

final class CardHotelTableViewCell: UITableViewCell {
    static let identifier = "CardHotelTableViewCell"
    
    // MARK: Properties
    private var model: SearchResultModel?
    
    // MARK: Outlets
    @IBOutlet weak var viewContent: UIView!
    @IBOutlet weak var collectionViewImages: UICollectionView!
    @IBOutlet weak var labelStars: UILabel!
    @IBOutlet weak var labelName: UILabel!
    @IBOutlet weak var labelAddress: UILabel!
    @IBOutlet weak var labelValue: UILabel!
    @IBOutlet weak var labelSecondAmenitie: UILabel!
    @IBOutlet weak var pageControl: UIPageControl!
    @IBOutlet weak var viewSecondAmenitie: UIStackView!
    
    // MARK: Overrides
    override func awakeFromNib() {
        super.awakeFromNib()
        setupUI()
    }
    
    // MARK: setup
    private func setupUI() {
        selectionStyle = .none
        viewContent.layer.cornerRadius = 8
        setupCollectionView()
    }
    
    private func setupCollectionView() {
        collectionViewImages
            .register(UINib(nibName: ImageCollectionViewCell.identifier,
                            bundle: nil),
                      forCellWithReuseIdentifier: ImageCollectionViewCell.identifier)
        
        let cellSize = CGSize(width: (UIScreen.main.bounds.width - 32), height: 200)
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .horizontal
        layout.itemSize = cellSize
        layout.sectionInset = UIEdgeInsets(top: 0,
                                           left: 0,
                                           bottom: 0,
                                           right: 0)
        layout.minimumLineSpacing = 0
        layout.minimumInteritemSpacing = 0
        self.collectionViewImages.setCollectionViewLayout(layout, animated: false)
        
        collectionViewImages.dataSource = self
        collectionViewImages.delegate = self
        collectionViewImages.reloadData()
    }
    
    func setupWith(model: SearchResultModel) {
        self.model = model
        collectionViewImages.reloadData()
        pageControl.numberOfPages = model.gallery.count
        labelStars.text = model.getStarsDescription()
        labelName.text = model.name
        labelAddress.text = model.getAddressFormatted()
        labelValue.text = model.getAmount()
        let secondAmenitie = model.getSecondAmenitie()
        labelSecondAmenitie.text = secondAmenitie
        viewSecondAmenitie.isHidden = secondAmenitie == nil
    }
}

// MARK: Extensions
extension CardHotelTableViewCell: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView,
                        numberOfItemsInSection section: Int) -> Int {
        model?.gallery.count ?? 0
    }
    
    func collectionView(_ collectionView: UICollectionView,
                        cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let imageURL = model?.gallery[indexPath.item].url ?? ""
        
        if let cell = collectionView
            .dequeueReusableCell(withReuseIdentifier: ImageCollectionViewCell.identifier,
                                 for: indexPath) as? ImageCollectionViewCell {
            cell.setup(with: imageURL)
            return cell
        }
        return UICollectionViewCell()
    }
}

extension CardHotelTableViewCell: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, willDisplay cell: UICollectionViewCell, forItemAt indexPath: IndexPath) {
        pageControl.currentPage = indexPath.item
    }
}
