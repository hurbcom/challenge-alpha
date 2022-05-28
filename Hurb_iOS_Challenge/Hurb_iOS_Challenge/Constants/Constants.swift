//
//  Constants.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 22/05/22.
//

import UIKit


struct Constants {
    // MARK: - JSON Files Names Constant
    struct JSONFilesNames {
        static let HOTEL_JSON_FILE_NAME = "hotel"
        static let PACKAGE_JSON_FILE_NAME = "package"
        static let JSON_TYPE = "json"
    }
    
    // MARK: - HomeCollectionView Constraints
    struct HomeCollectionViewConstraints {
        static let HOME_COLLECTIONVIEW_DIMENTIONS_WITH_PADDING = CGSize(width: UIScreen.main.bounds.width - 32, height: 300)
    }
    
    // MARK: - UIAlerts Messages
    struct UIAlertsMessages {
        static let SERVICE_ERROR_MESSAGE_TITLE = "Erro de Conexão"
    }
}
