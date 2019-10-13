//
//  BaseViewController.swift
//  Alpha
//
//  Created by Theo Mendes on 13/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit
import os.log

class BaseViewController: UIViewController {
    static var logEnabled: Bool = true

    var viewModel: BaseViewModel?

    init(viewModel: BaseViewModel) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)

        if BaseViewController.logEnabled {
            os_log("🎮 👶 %@", log: Logger.lifecycleLog(), type: .info, "\(self)")
        }
    }

    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    deinit {
        if BaseViewController.logEnabled {
            os_log("🎮 ⚰️ %@", log: Logger.lifecycleLog(), type: .info, "\(self)")
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        setupUI()
        setupConstraints()
        bindViewModel()
    }

    func setupUI() {
        updateUI()
    }

    func setupConstraints() {}

    func bindViewModel() {}

    func updateUI() {}

}
