//
//  SearchSuggestionsView.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

final class SearchSuggestionsView: UIView {
    
    // MARK: Properties
    private var suggestions: [Suggestions.Suggestion] = []
    var didSelectedSuggestion: ((Suggestions.Suggestion) -> ())?
    
    // MARK: Outlets
    @IBOutlet weak var tableView: UITableView! {
        didSet{
            tableView.register(UITableViewCell.self, forCellReuseIdentifier: "cell")
            tableView.dataSource = self
            tableView.delegate = self
        }
    }
    
    // MARK: Setup
    func setup(with suggestions: [Suggestions.Suggestion]) {
        self.suggestions = suggestions
        tableView.reloadData()
    }
}

// MARK: Extensions
extension SearchSuggestionsView: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return suggestions.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let suggestion = suggestions[indexPath.row]

        let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath)
        cell.textLabel?.text = suggestion.text
        cell.detailTextLabel?.text = suggestion.country
        cell.selectionStyle = .none
        return cell
    }
}

extension SearchSuggestionsView: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let selectedSuggestion = suggestions[indexPath.row]
        didSelectedSuggestion?(selectedSuggestion)
    }
}
