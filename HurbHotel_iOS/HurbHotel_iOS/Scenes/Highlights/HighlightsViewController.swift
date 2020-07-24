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
    private enum Sections: Int, CaseIterable {
        case opportunity
        case marriage
        case vacation
        case luxury
    }
    
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
    }
}

// MARK: Extensions
extension HighlightsViewController: UITableViewDataSource {
    func numberOfSections(in tableView: UITableView) -> Int {
        return Sections.allCases.count
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let section = Sections(rawValue: indexPath.section) else { return UITableViewCell() }
        
        switch section {
        case .opportunity, .luxury:
            return buildHighlightHorizontalCell(tableView, indexPath: indexPath)
        case .marriage, .vacation:
            return buildHighlightVerticalCell(tableView, indexPath: indexPath)
        }
    }
    
    private func buildHighlightHorizontalCell(_ tableView: UITableView, indexPath: IndexPath) -> HighlightHorizontalCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: HighlightHorizontalCell().identifier, for: indexPath) as? HighlightHorizontalCell else {
            return HighlightHorizontalCell()
        }
        cell.setup()
        return cell
    }
    
    private func buildHighlightVerticalCell(_ tableView: UITableView, indexPath: IndexPath) -> HighlightVerticalCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: HighlightVerticalCell().identifier, for: indexPath) as? HighlightVerticalCell else {
            return HighlightVerticalCell()
        }
        cell.setup()
        return cell
    }
}
