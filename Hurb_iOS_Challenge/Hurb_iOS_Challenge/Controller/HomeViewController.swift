//
//  HomeViewController.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 12/05/22.
//

import UIKit

class HomeViewController: UIViewController {
    // MARK: - Properties
    private var homeContainerView = HomeContainerView()
    
    // MARK: - Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        setupView()
        fetchHomeData()
    }
    
    // MARK: - Helper Methods
    
    private func fetchHomeData() {
        NetworkingService.shared.fetchHotels { [weak self] result in
            switch result {
            case .success(let hotelsResult):
                self?.homeContainerView.hotelsResult = hotelsResult
            case .failure(let error):
                self?.showAlert(message: error.localizedDescription, title: Constants.UIAlertsMessages.SERVICE_ERROR_MESSAGE_TITLE)
            }
        }
    }
}

extension HomeViewController: CodeView {
    func buildViewHierarchy() {
        view.addSubview(homeContainerView)
    }
    
    func setupConstraints() {
        homeContainerView.anchor(top: view.topAnchor,
                                  leading: view.leadingAnchor,
                                  bottom: view.bottomAnchor,
                                  trailling: view.trailingAnchor)
    }
}

