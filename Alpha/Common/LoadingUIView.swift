//
//  LoadingUIView.swift
//  Alpha
//
//  Created by Theo Mendes on 20/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit
import SnapKit

public class LoadingUIView: UIView {

    var loadingImage: UIImageView = {
        let view = UIImageView(frame: .zero)
        view.image = Asset.hotel.image
        view.contentMode = .scaleAspectFill
        return view
    }()

    var loadingLabel: UILabel = {
        let label = UILabel(frame: .zero)
        label.textColor = .black
        label.textAlignment = .center
        label.text = L10n.Loading.text
        label.numberOfLines = 2
        label.font = .systemFont(ofSize: 28.0, weight: .bold)
        return label
    }()

    var stackView: UIStackView = {
        let view = UIStackView()
        view.axis = .vertical
        view.distribution = .equalSpacing
        view.alignment = .center
        return view
    }()

    override init(frame: CGRect) {
        super.init(frame: frame)
        makeUI()
    }

    required public init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func makeUI() {
        stackView.addArrangedSubview(loadingImage)
        stackView.addArrangedSubview(loadingLabel)
        self.addSubview(stackView)
        updateUI()
    }

    func updateUI() {
        setNeedsDisplay()

        loadingImage.snp.makeConstraints { (make) in
            make.width.equalTo(125)
            make.height.equalTo(106)
        }

        stackView.snp.makeConstraints { (make) in
            make.center.equalToSuperview()
            make.left.equalToSuperview().offset(46.0)
            make.right.equalToSuperview().offset(-46.0)
        }
    }

    func getCenter() -> CGPoint {
        return convert(center, from: superview)
    }
}

