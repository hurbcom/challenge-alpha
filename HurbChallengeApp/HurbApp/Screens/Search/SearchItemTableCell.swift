//
//  SearchItemTableCell.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 20/01/23.
//

import UIKit

final class SearchItemTableCell: UITableViewCell {

    static let reuseIdentifier: String = "SearchItemTableCell"

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

}
