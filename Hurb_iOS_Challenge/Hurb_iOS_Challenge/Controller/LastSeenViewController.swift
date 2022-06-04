//
//  LastSeenViewController.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 22/05/22.
//

import UIKit

class LastSeenViewController: UIViewController {
    // MARK: - Properties
    var lastSeenContainerView = LastSeenContainerView()
    
    
    // MARK: - Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        setupView()
    }
    
    // MARK: - Helper Methods
    private func fetchLocalLestSeenHotels() {
        DataPersistenceManager.shared.fetchHotelsFromDataBaseLocal { [weak self] result in
            switch result {
            case .success(let hotels):
                self?.lastSeenContainerView.lastSeenHotels = hotels
                DispatchQueue.main.async {
                    self?.lastSeenContainerView.lastSeenTableView.reloadData()
                }
                print("DEBUG: - SUCESSO AO CARREGAR A LISTA LOCAL DE HOTÉIS VISTOS POR ÚLTIMO.")
            case .failure(let error):
                print("DEBUG: - \(error.localizedDescription)")
            }
        }
    }
}

extension LastSeenViewController: CodeView {
    func buildViewHierarchy() {
        view.addSubview(lastSeenContainerView)
    }
    
    func setupConstraints() {
        lastSeenContainerView.anchor(top: view.topAnchor,
                                     leading: view.leadingAnchor,
                                     bottom: view.bottomAnchor,
                                     trailling: view.trailingAnchor)
    }
    
    func setupAdditionalConfiguration() {
        /// Configure View.
        view.backgroundColor = .white
        /// Fetch Local Data.
        fetchLocalLestSeenHotels()
        /// Configure NotificationCenter.
        NotificationCenter.default.addObserver(forName: Notification.Name(Constants.NotificationCenterNames.NOTIFICATION_CENTER_LAST_SEEN_ADDED),
                                               object: nil,
                                               queue: nil) { _ in
            self.fetchLocalLestSeenHotels()
        }
    }
}
