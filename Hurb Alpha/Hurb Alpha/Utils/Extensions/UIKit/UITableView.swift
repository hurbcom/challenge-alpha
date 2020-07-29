//
//  UITableView.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 28/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import UIKit

public extension UITableView {
    
    /** Shortcut: Register a cell with his Default name and identifier on the main bundle. */
    func registerCell<T: UITableViewCell>(cellClass: T.Type) {
        self.register(T.self, forCellReuseIdentifier: T.defaultIdentifier)
    }
    
    func registerHeaderFooter<T: UITableViewHeaderFooterView>(cellClass: T.Type) {
        self.register(T.self, forHeaderFooterViewReuseIdentifier: T.defaultIdentifier)
    }
    
    /** Shortcut: Dequeue a cell with his default Class Name. Example: MyCustomCell.self */
    func dequeue<T: UITableViewCell>(cellClass: T.Type, indexPath: IndexPath) -> T {
        return self.dequeue(withIdentifier: cellClass.defaultIdentifier, indexPath: indexPath)
    }
    
    func dequeue<T: UITableViewHeaderFooterView>(cellClass: T.Type) -> T {
        return self.dequeueReusableHeaderFooterView(withIdentifier: cellClass.defaultIdentifier) as! T
    }
    
    /** Dequeue a cell with automatic casting */
    private func dequeue<T: UITableViewCell>(withIdentifier id: String, indexPath: IndexPath) -> T {
        return self.dequeueReusableCell(withIdentifier: id, for: indexPath) as! T
    }
    
    func beginRefreshing() {
      guard let refreshControl = refreshControl, !refreshControl.isRefreshing else {
        return
      }
      refreshControl.beginRefreshing()
      refreshControl.sendActions(for: .valueChanged)

      let contentOffset = CGPoint(x: 0, y: -refreshControl.frame.height)
      setContentOffset(contentOffset, animated: true)
    }
    
    func endRefreshing() {
      refreshControl?.endRefreshing()
    }
}
