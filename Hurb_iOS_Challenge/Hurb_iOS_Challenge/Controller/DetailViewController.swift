//
//  DetailViewController.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 29/05/22.
//

import UIKit

class DetailViewController: UIViewController {
    
    // MARK: - Properties
    var detailContainerView = DetailContainerView()
    
    private var scrollView: UIScrollView = {
        let scrollView = UIScrollView()
        scrollView.backgroundColor = .clear
        scrollView.showsVerticalScrollIndicator = false
        return scrollView
    }()
    
    // MARK: - Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        setupView()
    }
}

extension DetailViewController: CodeView {
    func buildViewHierarchy() {
        view.addSubview(scrollView)
        scrollView.addSubview(detailContainerView)
    }
    
    func setupConstraints() {
        scrollView.anchor(top: view.topAnchor,
                          leading: view.leadingAnchor,
                          bottom: view.bottomAnchor,
                          trailling: view.trailingAnchor)
        
        detailContainerView.anchor(top: scrollView.topAnchor,
                                   leading: scrollView.leadingAnchor,
                                   bottom: scrollView.bottomAnchor,
                                   trailling: scrollView.trailingAnchor)
    }
    
    func setupAdditionalConfiguration() {
        view.backgroundColor = .white
        navigationItem.title = "Hotel"
    }
}
