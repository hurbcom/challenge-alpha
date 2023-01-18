//
//  HUTextField.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 18/01/23.
//

import UIKit

final class HUTextField: UITextField {

    var textPadding = UIEdgeInsets(
        top: 8.0,
        left: 8.0,
        bottom: 8.0,
        right: 8.0
    )

    init() {
        super.init(frame: .zero)
        setupView()
    }

    required init?(coder: NSCoder) {
        return nil
    }

    private func setupView() {
        layer.borderColor = UIColor.systemGray.cgColor
        layer.borderWidth = 1.0
        layer.cornerRadius = 4.0
    }

    override func textRect(forBounds bounds: CGRect) -> CGRect {
        let rect = super.textRect(forBounds: bounds)
        return rect.inset(by: textPadding)
    }

    override func editingRect(forBounds bounds: CGRect) -> CGRect {
        let rect = super.editingRect(forBounds: bounds)
        return rect.inset(by: textPadding)
    }

//    override func leftViewRect(forBounds bounds: CGRect) -> CGRect {
//        <#code#>
//    }

}
