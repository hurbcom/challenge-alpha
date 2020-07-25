//
//  HighlightsViewController.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

final class HighlightsViewController: BaseViewController {
    
    // MARK: Properties
    let viewModel = HighlightsViewModel()
    private enum Sections: Int, CaseIterable {
        case opportunity
        case vacation
        case marriage
        case luxury
    }
    let viewHighlightsNotFound: HighlightsNotFoundView = HighlightsNotFoundView.fromNib()
    
    // MARK: Outlets
    @IBOutlet weak var tableView: UITableView! {
        didSet{
            tableView.register(UINib(nibName: HighlightHorizontalCell().identifier, bundle: nil), forCellReuseIdentifier: HighlightHorizontalCell().identifier)
            tableView.register(UINib(nibName: HighlightVerticalCell().identifier, bundle: nil), forCellReuseIdentifier: HighlightVerticalCell().identifier)
            tableView.dataSource = self
            tableView.reloadData()
        }
    }
    
    // MARK: Overrides
    override func viewDidLoad() {
        super.viewDidLoad()
        
        showLoading()
        viewModel.fetchHighlights()
        bindEvents()
    }
    
    // MARK: Helpers
    private func bindEvents() {
        viewModel.shouldReloadData = { [weak self] in
            DispatchQueue.main.async {
                self?.closeLoading()
                self?.tableView.reloadData()
            }
        }
        
        viewModel.didFailure = { [weak self] error in
            debugPrint("==> Error: \(error ?? "")")
            self?.viewModel.highlights = nil
            DispatchQueue.main.async {
                self?.closeLoading()
                self?.tableView.backgroundView = self?.viewHighlightsNotFound
            }
        }
    }
    
    private func openDetail() {
        let viewController = DetailViewController.builder()
        navigationController?.pushViewController(viewController, animated: true)
    }
}

// MARK: Extensions
extension HighlightsViewController: UITableViewDataSource {
    func numberOfSections(in tableView: UITableView) -> Int {
        return Sections.allCases.count
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        guard let section = Sections(rawValue: section) else { return 0 }
        switch section {
        case .opportunity:
            return viewModel.highlights?.opportunity != nil ? 1 : 0
        case .vacation:
            return viewModel.highlights?.vacation != nil ? 1 : 0
        case .marriage:
            return viewModel.highlights?.marriage != nil ? 1 : 0
        case .luxury:
            return viewModel.highlights?.luxury != nil ? 1 : 0
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let section = Sections(rawValue: indexPath.section) else { return UITableViewCell() }
        
        switch section {
        case .opportunity:
            guard let section = viewModel.highlights?.opportunity else {return UITableViewCell()}
            return buildHighlightHorizontalCell(tableView, indexPath: indexPath, section: section)
        case .vacation:
            guard let section = viewModel.highlights?.vacation else {return UITableViewCell()}
            return buildHighlightVerticalCell(tableView, indexPath: indexPath, section: section)
        case .marriage:
            guard let section = viewModel.highlights?.marriage else {return UITableViewCell()}
            return buildHighlightVerticalCell(tableView, indexPath: indexPath, section: section)
        case .luxury:
            guard let section = viewModel.highlights?.luxury else {return UITableViewCell()}
            return buildHighlightHorizontalCell(tableView, indexPath: indexPath, section: section)
        }
    }
    
    private func buildHighlightHorizontalCell(_ tableView: UITableView, indexPath: IndexPath, section: Highlights.Section) -> HighlightHorizontalCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: HighlightHorizontalCell().identifier, for: indexPath) as? HighlightHorizontalCell else {
            return HighlightHorizontalCell()
        }
        cell.setup(with: section)
        cell.onClickCard = { [weak self] _ in
            self?.openDetail()
        }
        return cell
    }
    
    private func buildHighlightVerticalCell(_ tableView: UITableView, indexPath: IndexPath, section: Highlights.Section) -> HighlightVerticalCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: HighlightVerticalCell().identifier, for: indexPath) as? HighlightVerticalCell else {
            return HighlightVerticalCell()
        }
        cell.setup(with: section)
        cell.onClickCard = { [weak self] _ in
            self?.openDetail()
        }
        return cell
    }
}
