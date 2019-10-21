//
//  BaseViewController.swift
//  Alpha
//
//  Created by Theo Mendes on 13/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit
import os.log
import RxRelay

/// Base class for all View Controllers of the project
class BaseViewController: UIViewController {
    // MARK: - Properties

    /// Variable to enable or disable view's logs
    static var logEnabled: Bool = true
    /// Controller's View Model
    var viewModel: BaseViewModel?

    let isLoading = BehaviorRelay(value: false)

    // MARK: - Lifecycle

    /**
    Initializes a new View Controller with View Model and logging

    - Parameters:
       - viewModel: The View Model that the VC will use

    - Returns: A Base View Controller with View Model
    */
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
    // MARK: - viewDidLoad

    override func viewDidLoad() {
        super.viewDidLoad()
        setupUI()
        setupConstraints()
        bindViewModel()
    }

    // MARK: - View methods

    /// Setup the view's UI, here you should put the **addSubView**
    func setupUI() {
        updateUI()
    }
    /// Setup all the constraints of the view
    func setupConstraints() {}
    /// Bind all the UI elements with the View Model
    func bindViewModel() {}
    /// Is called when the view should be updated
    func updateUI() {}
}
