//
//  HUTextField.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 18/01/23.
//

import UIKit

final class HUTextField: UITextField {

    var textPadding: UIEdgeInsets

    init() {
        self.textPadding = .zero
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

}
