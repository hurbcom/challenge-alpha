//
//  SuggestionsView.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 12/11/22.
//

import UIKit

final class SuggestionsView: UIView {
    
    // MARK: Properties
    private var suggestions: [String] = []
    var didSelectedSuggestion: ((String) -> ())?
    
    // MARK: Outlets
    @IBOutlet weak var tableView: UITableView!
    
    // MARK: Setup
    func setup(with suggestions: [String]) {
        self.suggestions = suggestions
        
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "cell")
        tableView.dataSource = self
        tableView.delegate = self
        tableView.reloadData()
    }
}

// MARK: Extensions
extension SuggestionsView: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return suggestions.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let suggestion = suggestions[indexPath.row]

        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
        cell.textLabel?.text = suggestion//.text
        cell.detailTextLabel?.text = suggestion//.country
        cell.selectionStyle = .none
        return cell
    }
}

extension SuggestionsView: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let selectedSuggestion = suggestions[indexPath.row]
        didSelectedSuggestion?(selectedSuggestion)
    }
}
