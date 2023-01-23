//
//  ScrollableView.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 23/01/23.
//

import UIKit

final class ScrollableView: UIScrollView {

    let contentView: UIView = {
        let contentView = UIView()
        contentView.translatesAutoresizingMaskIntoConstraints = false
        return contentView
    }()

    init() {
        super.init(frame: .zero)
        buildView()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        return nil
    }

}

extension ScrollableView: ViewCodeProtocol {

    func setupView() {

    }

    func setupLayoutConstraints() {

        let heightConstraint = contentView.heightAnchor.constraint(equalTo: heightAnchor)
        heightConstraint.priority = .defaultLow

        addSubview(contentView)
        NSLayoutConstraint.activate([
            contentView.topAnchor.constraint(equalTo: topAnchor),
            contentView.leadingAnchor.constraint(equalTo: leadingAnchor),
            contentView.bottomAnchor.constraint(equalTo: bottomAnchor),
            contentView.trailingAnchor.constraint(equalTo: trailingAnchor),
            contentView.widthAnchor.constraint(equalTo: widthAnchor),
            heightConstraint
        ])
    }

}
