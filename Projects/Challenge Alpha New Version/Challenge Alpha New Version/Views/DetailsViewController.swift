//
//  DetailViewController.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 13/04/23.
//

import UIKit

class DetailsViewController: UIViewController {
    
    var hotel:HotelsInfoViewModel?
    let scrollView = UIScrollView()
    let contentView = UIView()
    let imageView = UIImageView()
    let titleLabel = UILabel()
    let subtitleLabel = UILabel()
    let starButton = UIButton()
    let shareButton = UIButton()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        title = "Informações do produto"
        view.backgroundColor = .white
        configureUI()
        configureConstraints()
        displayData()
        
    }
    
    private func configureUI() {
        // Configure scrollView
        view.addSubview(scrollView)
        scrollView.addSubview(contentView)
        
        // Configure contentView
        contentView.addSubview(imageView)
        contentView.addSubview(titleLabel)
        contentView.addSubview(subtitleLabel)
        contentView.addSubview(starButton)
        contentView.addSubview(shareButton)
        
        // Configure imageView
        imageView.contentMode = .scaleAspectFit
        imageView.clipsToBounds = true
        
        // Configure titleLabel
        titleLabel.font = UIFont.systemFont(ofSize: 24, weight: .bold)
        titleLabel.numberOfLines = 0
        
        // Configure subtitleLabel
        subtitleLabel.font = UIFont.systemFont(ofSize: 16)
        subtitleLabel.numberOfLines = 0
        
        // Configure starButton
        starButton.setImage(UIImage(systemName: "star"), for: .normal)
        starButton.tintColor = .systemYellow
        
        // Configure shareButton
        shareButton.setImage(UIImage(systemName: "square.and.arrow.up"), for: .normal)
        shareButton.tintColor = .systemBlue
        
    }
    
    private func configureConstraints(){
        // Add constraints
        scrollView.translatesAutoresizingMaskIntoConstraints = false
        contentView.translatesAutoresizingMaskIntoConstraints = false
        imageView.translatesAutoresizingMaskIntoConstraints = false
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        subtitleLabel.translatesAutoresizingMaskIntoConstraints = false
        starButton.translatesAutoresizingMaskIntoConstraints = false
        shareButton.translatesAutoresizingMaskIntoConstraints = false
        
        let safeArea = view.safeAreaLayoutGuide
        //        let confCGFloat = safeArea.heightAnchor. * 2
        NSLayoutConstraint.activate([
            scrollView.topAnchor.constraint(equalTo: safeArea.topAnchor),
            scrollView.leadingAnchor.constraint(equalTo: safeArea.leadingAnchor),
            scrollView.trailingAnchor.constraint(equalTo: safeArea.trailingAnchor),
            scrollView.bottomAnchor.constraint(equalTo: safeArea.bottomAnchor),
            
            contentView.topAnchor.constraint(equalTo: scrollView.topAnchor),
            contentView.leadingAnchor.constraint(equalTo: scrollView.leadingAnchor),
            contentView.trailingAnchor.constraint(equalTo: scrollView.trailingAnchor),
            contentView.bottomAnchor.constraint(equalTo: scrollView.bottomAnchor),
            //            contentView.widthAnchor.constraint(equalTo: safeArea.widthAnchor),
            contentView.heightAnchor.constraint(equalToConstant: (1800)),
            contentView.widthAnchor.constraint(equalToConstant: 400),
            imageView.topAnchor.constraint(equalTo: contentView.topAnchor),
            imageView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor),
            imageView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor),
            imageView.heightAnchor.constraint(equalToConstant: 300),
            
            titleLabel.topAnchor.constraint(equalTo: starButton.bottomAnchor, constant: 3),
            titleLabel.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16),
            titleLabel.trailingAnchor.constraint(equalTo: contentView.trailingAnchor,constant: -16),
            subtitleLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 8),
            subtitleLabel.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 16),
            subtitleLabel.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -16),
            
            starButton.widthAnchor.constraint(equalToConstant: 38),
            starButton.heightAnchor.constraint(equalToConstant: 38),
            starButton.topAnchor.constraint(equalTo: imageView.bottomAnchor),
            starButton.trailingAnchor.constraint(equalTo: imageView.trailingAnchor,constant: -16),
            
            shareButton.widthAnchor.constraint(equalToConstant: 38),
            shareButton.heightAnchor.constraint(equalToConstant: 38),
            shareButton.topAnchor.constraint(equalTo: imageView.bottomAnchor),
            shareButton.leadingAnchor.constraint(equalTo: starButton.leadingAnchor, constant: -32)
            
        ])
    }
    
    private func displayData(){
        if let _hotel = hotel {
            titleLabel.text = _hotel.name
            subtitleLabel.text = _hotel.smallDescription
            getImageFrom(url: _hotel.imgGallery)
        }
    }
    func getImageFrom(url:String) {
        let url = URL(string:url)
        let data = try? Data(contentsOf: url!)
        DispatchQueue.main.async {
            self.imageView.image  = UIImage(data: data!)
        }
    }
}
