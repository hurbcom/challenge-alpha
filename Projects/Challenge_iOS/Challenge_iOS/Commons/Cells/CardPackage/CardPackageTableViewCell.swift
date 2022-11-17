//
//  CardPackageTableViewCell.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 13/11/22.
//

import UIKit

class CardPackageTableViewCell: UITableViewCell {
    static let identifier = "CardPackageTableViewCell"
    
    // MARK: Properties
    private var model: SearchResultModel?
    var didClicked: (() -> Void)?
    
    // MARK: Outlets
    @IBOutlet weak var viewContent: UIView!
    @IBOutlet weak var collectionViewGallery: UICollectionView!
    @IBOutlet weak var pageControl: UIPageControl!
    @IBOutlet weak var labelAddress: UILabel!
    @IBOutlet weak var labelName: UILabel!
    @IBOutlet weak var labelDailys: UILabel!
    @IBOutlet weak var labelPersons: UILabel!
    @IBOutlet weak var labelValue: UILabel!

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
        collectionViewGallery
            .register(UINib(nibName: ImageCollectionViewCell.identifier,
                            bundle: nil),
                      forCellWithReuseIdentifier: ImageCollectionViewCell.identifier)
        
        let cellSize = CGSize(width: (UIScreen.main.bounds.width - 32), height: 200)
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .horizontal
        layout.itemSize = cellSize
        layout.sectionInset = UIEdgeInsets(top: 0, left: 0, bottom: 0, right: 0)
        layout.minimumLineSpacing = 0
        layout.minimumInteritemSpacing = 0
        self.collectionViewGallery.setCollectionViewLayout(layout, animated: false)
        collectionViewGallery.dataSource = self
        collectionViewGallery.delegate = self
        collectionViewGallery.reloadData()
    }
    
    func setupWith(model: SearchResultModel) {
        self.model = model
        collectionViewGallery.reloadData()
        pageControl.numberOfPages = model.gallery.count
        labelName.text = model.name
        labelAddress.text = model.getAddressFormatted()
        labelDailys.text = model.getDailysLabel()
        labelPersons.text = model.getPersonsLabel()
        labelValue.text = model.getAmount()
    }
}

// MARK: Extensions
extension CardPackageTableViewCell: UICollectionViewDataSource {
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

extension CardPackageTableViewCell: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, willDisplay cell: UICollectionViewCell, forItemAt indexPath: IndexPath) {
        pageControl.currentPage = indexPath.item
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        didClicked?()
    }
}
