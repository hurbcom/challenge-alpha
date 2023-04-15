//
//  DetailViewController.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 13/04/23.
//

import UIKit

class DetailsViewController: UIViewController {
    
    var hotel:HotelsInfoViewModel?
    var package:PackageInfoViewModel?
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
        starButton.addTarget(self, action: #selector(favBtnPressed), for: .touchUpInside)
        
        // Configure shareButton
        shareButton.setImage(UIImage(systemName: "square.and.arrow.up"), for: .normal)
        shareButton.tintColor = .systemBlue
        shareButton.addTarget(self, action: #selector(shareButtonPressed), for: .touchUpInside)
        
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
            subtitleLabel.text = _hotel.Description
            getImageFrom(url: _hotel.imgGallery)
        }
        if let package = package {
            titleLabel.text = package.name
            subtitleLabel.text = removeHtmlTagsFromText(text:  package.Description)
            getImageFrom(url: package.imgGallery)
        }
    }
    func getImageFrom(url:String) {
        let url = URL(string:url)
        let data = try? Data(contentsOf: url!)
        DispatchQueue.main.async {
            self.imageView.image  = UIImage(data: data!)
        }
    }
    
    
    func removeHtmlTagsFromText(text:String) -> String {
        let htmlString = text
        let attributedString = try? NSAttributedString(data: htmlString.data(using: .utf8)!, options: [.documentType: NSAttributedString.DocumentType.html], documentAttributes: nil)
        let plainString = attributedString?.string
        let decomposedString = plainString?.decomposedStringWithCanonicalMapping
        return decomposedString!
    }
    
    func shareURL() {
        if let hotel = hotel {
            let activityViewController = UIActivityViewController(activityItems: [hotel.url], applicationActivities: nil)
            present(activityViewController, animated: true, completion: nil)
        }
        if let package = package {
            let activityViewController = UIActivityViewController(activityItems: [package.url], applicationActivities: nil)
            present(activityViewController, animated: true, completion: nil)
        }
    }
    
    @objc private func shareButtonPressed() {
        shareURL()
    }
    
    @objc private func favBtnPressed(){
        self.starButton.setImage(UIImage(systemName: "star.fill"), for: .normal)
        if let hotel = hotel {
            CoreDataManager.Manager.createNewFavorite(titulo: hotel.name, descricao: hotel.Description, img: hotel.imgGallery, categoria: hotel.prodtype)
        }
        if let package = package {
            CoreDataManager.Manager.createNewFavorite(titulo: package.name, descricao: package.Description, img: package.imgGallery, categoria: package.prodtype)
        }
    }

}