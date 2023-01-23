//
//  HomeViewCell.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 16/01/23.
//

import UIKit
import HUGraphQL

class HomeViewCell: UITableViewCell {
    
    static let identifier = "HomeViewCell"
    var viewModel = ResultViewModel()
    typealias Result = HUGraphQL.SearchQuery.Data.Search.Result
    var url = URL(string: "https://www.google.com.br")
    
    // MARK: - Properties UI
    let homeCell: HomeVIew = {
        let homeCell = HomeVIew()
        homeCell.isAccessibilityElement = true
        homeCell.translatesAutoresizingMaskIntoConstraints = false
        return homeCell
    }()
    
    // MARK: - init
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        backgroundColor = .clear
        setupView()
    }
    
 func configureCell(with hotel: Result) {
     homeCell.setupCell(hotel: hotel)
}
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    
    //MARK: - Functions
    func setupURL(hotel: Result){
        url = URL(string: hotel.url)
    }
    
    @objc private func presentShareSheet(){
        guard let url = url else { return }
        
        let av = UIActivityViewController(activityItems: [url], applicationActivities: nil)
        UIApplication.shared.windows.first?.rootViewController?.present(av, animated: true)
        if UIDevice.current.userInterfaceIdiom == .phone{
            av.popoverPresentationController?.sourceView = UIApplication.shared.windows.first
            av.popoverPresentationController?.sourceRect = CGRect(x: UIScreen.main.bounds.width / 2.1, y: UIScreen.main.bounds.height / 1.0, width: 200, height: 200)
        }
    }
}

extension HomeViewCell: ViewCodableProtocol {
    func setupView() {
        buildViewHierarchy()
        setupConstraints()
        setupAdditionalConfiguration()
    }

    func buildViewHierarchy() {
        self.addSubview(homeCell)
    }
    
    func setupConstraints() {
        NSLayoutConstraint.activate([
            homeCell.topAnchor.constraint(equalTo: self.topAnchor),
            homeCell.bottomAnchor.constraint(equalTo: self.bottomAnchor),
            homeCell.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 20),
            homeCell.trailingAnchor.constraint(equalTo: self.trailingAnchor, constant: -20),
        ])
    }

    func setupAdditionalConfiguration() {}
}
