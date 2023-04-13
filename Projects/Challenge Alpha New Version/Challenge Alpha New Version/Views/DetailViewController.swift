//
//  DetailViewController.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 13/04/23.
//

import UIKit

class DetailViewController: UIViewController {
    var hotel: HotelsInfoViewModel?
    lazy var scrollView: UIScrollView = {
       let scrollview = UIScrollView()
        scrollview.translatesAutoresizingMaskIntoConstraints = false
        scrollview.isScrollEnabled = true
       return scrollview
    }()
    lazy var cardImage: UIImageView = {
        let image = UIImageView()
        image.contentMode = .scaleToFill
        image.translatesAutoresizingMaskIntoConstraints = false
        image.layer.cornerRadius = 5
        image.layer.masksToBounds = true
        return image
    }()
    lazy var cardTitle: UILabel = {
       let label = UILabel()
        label.font = .systemFont(ofSize: 23, weight: .bold)
        label.textColor = .label
        label.numberOfLines = 0
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    lazy var cardDetail: UILabel = {
       let label = UILabel()
        label.textColor = .secondaryLabel
        label.font = .systemFont(ofSize: 18, weight: .regular)
        
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    lazy var cardDescription: UILabel = {
       let label = UILabel()
        label.textColor = .label
        label.numberOfLines = 0
        label.font = .systemFont(ofSize: 18, weight: .regular)
        label.textAlignment = .left
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    
    lazy var VStack: UIStackView = {
        let vstack = UIStackView()
        vstack.axis = .vertical
        vstack.translatesAutoresizingMaskIntoConstraints = false
        vstack.distribution = .fill
        return vstack
    }()
    lazy var hStack: UIStackView = {
        let hstack = UIStackView()
        hstack.axis = .horizontal
        hstack.translatesAutoresizingMaskIntoConstraints = false
        hstack.distribution = .fill
        return hstack
    }()
    
    lazy var favoriteButton: UIButton = {
        let favoriteBtn = UIButton()
        favoriteBtn.setImage(UIImage(systemName: "star"), for: .normal)
        return favoriteBtn
    }()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .white
        self.view.addSubview(scrollView)
        self.scrollView.addSubview(VStack)
        VStack.addArrangedSubview(cardImage)
        VStack.addArrangedSubview(hStack)
        hStack.addArrangedSubview(cardTitle)
        hStack.addArrangedSubview(favoriteButton)
        VStack.addArrangedSubview(cardDescription)
        configureConstraints()
        displayData()
    }
    
    private func configureConstraints(){
        let safeArea = view.safeAreaLayoutGuide
        NSLayoutConstraint.activate([
            scrollView.topAnchor.constraint(equalTo: safeArea.topAnchor),
            scrollView.leadingAnchor.constraint(equalTo: safeArea.leadingAnchor),
            scrollView.trailingAnchor.constraint(equalTo: safeArea.trailingAnchor),
            scrollView.bottomAnchor.constraint(equalTo: safeArea.bottomAnchor),
            
            VStack.topAnchor.constraint(equalTo: scrollView.topAnchor),
            VStack.leftAnchor.constraint(equalTo: scrollView.leftAnchor),
            VStack.rightAnchor.constraint(equalTo: scrollView.rightAnchor),
            VStack.bottomAnchor.constraint(equalTo: scrollView.bottomAnchor),
            VStack.widthAnchor.constraint(equalTo: safeArea.widthAnchor),
            
//            cardTitle.leadingAnchor.constraint(equalTo: VStack.leadingAnchor, constant: 16),
//            cardTitle.trailingAnchor.constraint(equalTo: VStack.trailingAnchor, constant: -16),
//            cardDescription.leadingAnchor.constraint(equalTo: VStack.leadingAnchor, constant: 16),
//            cardDescription.trailingAnchor.constraint(equalTo: VStack.trailingAnchor, constant: -16),
//
            cardImage.heightAnchor.constraint(equalToConstant: 250),
////            cardImage.widthAnchor.constraint(equalToConstant: 50),
//            cardImage.leadingAnchor.constraint(equalTo: VStack.leadingAnchor, constant: 16),
//            cardImage.rightAnchor.constraint(equalTo: VStack.rightAnchor, constant: -32),
        ])
    }
    
    private func displayData(){
        if let _hotel = hotel {
            cardTitle.text = _hotel.name
            cardDescription.text = _hotel.smallDescription
            getImageFrom(url: _hotel.imgGallery)
        }
    }
    func getImageFrom(url:String) {
        let url = URL(string:url)
        let data = try? Data(contentsOf: url!)
        DispatchQueue.main.async {
            self.cardImage.image  = UIImage(data: data!)
        }
    }
    
    
}
