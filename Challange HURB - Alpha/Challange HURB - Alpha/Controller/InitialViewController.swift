//
//  ViewController.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 26/10/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import UIKit
import SnapKit

class InitialViewController: UIViewController, StoryboardInitializable {
    
    static var storyboardName: String = "Main"
    
    static var storyboardID: String = "InitialViewController"

    weak var coordinator: MainCoordinator?
    
    private var backgroundImage: UIImageView = {
        let view = UIImageView(frame: .zero)
        view.image = UIImage(named: "HurbLoadingScreen")
        view.contentMode = .scaleAspectFill
        return view
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupSubviews()
    }

    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
    }
    
    func setupSubviews() {
        self.view.addSubview(backgroundImage)
        setupConstraints()
    }
    
    func setupConstraints() {
        backgroundImage.snp.makeConstraints { (make) in
            make.center.equalToSuperview()
            make.width.equalToSuperview()
            make.right.equalToSuperview()
        }
    }

}

