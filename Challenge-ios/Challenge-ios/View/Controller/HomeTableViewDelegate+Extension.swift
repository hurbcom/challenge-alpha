//
//  HomeTableViewDelegate+Extension.swift
//  Challenge-ios
//
//  Created by Andre Dias on 01/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation
import UIKit

extension HomeViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        
        let headerView = UIView.init(frame: CGRect.init(x: 0, y: 0, width: tableView.frame.width, height: 40))
        headerView.backgroundColor = .white
        var labelString = ""
        let label = UILabel()
        label.frame = CGRect.init(x: 5, y: 5, width: headerView.frame.width-10, height: headerView.frame.height-10)
       if section == 0 {
            labelString = Constants.fiveStarsHotelsText
        } else if section == 1 {
            labelString = Constants.fourStarsHotelsText
        } else if section == 2 {
            labelString = Constants.threeStarsHotelsText
        } else if section == 3 {
            labelString = Constants.packagesText
        }
        
        let attributes: [NSAttributedString.Key : Any] = [
                  NSAttributedString.Key.foregroundColor: UIColor.black,
                  NSAttributedString.Key.font: UIFont(name: Fonts.montserratRegular, size: 16.0)!]
        label.attributedText = NSAttributedString(string: labelString, attributes: attributes)

        headerView.addSubview(label)

        return headerView

    }
}
