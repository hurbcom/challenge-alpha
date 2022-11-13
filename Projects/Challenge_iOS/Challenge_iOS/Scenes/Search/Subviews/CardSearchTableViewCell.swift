//
//  CardSearchTableViewCell.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 13/11/22.
//

import UIKit

final class CardSearchTableViewCell: UITableViewCell {
    static let identifier = "CardSearchTableViewCell"
    
    // MARK: Properties
    private var model: SearchModel?
    
    // MARK: Outlets
    @IBOutlet weak var viewContent: UIView!
    @IBOutlet weak var collectionViewImages: UICollectionView!
    @IBOutlet weak var labelName: UILabel!
    @IBOutlet weak var labelNumberDailys: UILabel!
    @IBOutlet weak var labelPrice: UILabel!
    
    // MARK: Overrides
    override func awakeFromNib() {
        super.awakeFromNib()
        
        setupUI()
    }
    
    // MARK: setup
    private func setupUI() {
        viewContent.layer.cornerRadius = 5
        setupCollectionView()
    }
    
    private func setupCollectionView() {
        collectionViewImages
            .register(UINib(nibName: ImageCollectionViewCell.identifier,
                            bundle: nil),
                      forCellWithReuseIdentifier: ImageCollectionViewCell.identifier)
        
        let cellSize = CGSize(width: (UIScreen.main.bounds.width - 32), height: 180)
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .horizontal
        layout.itemSize = cellSize
        layout.sectionInset = UIEdgeInsets(top: 0,
                                           left: 0,
                                           bottom: 0,
                                           right: 0)
        layout.minimumLineSpacing = 8
        layout.minimumInteritemSpacing = 8
        self.collectionViewImages.setCollectionViewLayout(layout, animated: false)
        
        collectionViewImages.dataSource = self
        collectionViewImages.reloadData()
    }
    
    func setupWith(model: SearchModel) {
        self.model = model
        collectionViewImages.reloadData()
        labelName.text = model.name
        let amount = NumberFormatter.localizedString(from: (model.price?.amount ?? 0.0) as NSNumber, number: .currency)
        labelPrice.text = amount
    }
}

extension CardSearchTableViewCell: UICollectionViewDataSource {
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
