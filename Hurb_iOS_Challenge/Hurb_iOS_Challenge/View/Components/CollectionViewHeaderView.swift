//
//  CollectionViewHeaderView.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 31/05/22.
//

import UIKit

class CollectionViewHeaderView: UICollectionReusableView {
    
    // MARK: - Properties
    var headerDividingLine = LineView()
    private var headerDividingLine2 = LineView()
    
    lazy var sectionHeaderLabel: UILabel = {
        let label = UILabel()
        label.font = UIFont.systemFont(ofSize: 16, weight: .semibold)
        label.textColor = .black
        label.textAlignment = .left
        return label
    }()
    
    lazy var stackView: UIStackView = {
        let stack = UIStackView(arrangedSubviews: [headerDividingLine,
                                                   sectionHeaderLabel,
                                                   headerDividingLine2])
        stack.axis = .vertical
        stack.spacing = 8
        stack.alignment = .leading
        stack.distribution = .fillProportionally
        return stack
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .lightGray
        setupView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

// MARK: - CodeView
extension CollectionViewHeaderView: CodeView {
    func buildViewHierarchy() {
        addSubview(stackView)
    }
    
    func setupConstraints() {
        stackView.anchor(top: topAnchor,
                         leading: leadingAnchor,
                         bottom: bottomAnchor,
                         trailling: trailingAnchor,
                         paddingLeading: 16,
                         paddingBottom: 8,
                         paddingTrailling: 16)
    }
    
    func setupAdditionalConfiguration() {
        /// Configure View.
        backgroundColor = .white
    }
}
