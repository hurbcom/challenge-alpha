//
//  HUViewController.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 17/01/23.
//

import UIKit

class HUViewController: UIViewController {

    private let viewModel: HUViewModelProtocol

    init(viewModel: HUViewModelProtocol) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        setupView()
        viewModel.loadData()
    }

    private func setupView() {
        view.backgroundColor = .white
    }

}
