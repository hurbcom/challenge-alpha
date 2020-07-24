//
//  HighlightsViewController.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

final class HighlightsViewController: BaseViewController {
    
    // MARK: Outlets
    @IBOutlet weak var tableView: UITableView! {
        didSet{
            tableView.register(UINib(nibName: HighlightHorizontalCell().identifier, bundle: nil), forCellReuseIdentifier: HighlightHorizontalCell().identifier)
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
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        if let cell = tableView.dequeueReusableCell(withIdentifier: HighlightHorizontalCell().identifier, for: indexPath) as? HighlightHorizontalCell {
            
            return cell
        }
        
        return UITableViewCell()
    }
}
