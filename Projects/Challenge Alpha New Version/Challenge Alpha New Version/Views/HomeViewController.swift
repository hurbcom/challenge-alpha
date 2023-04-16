//
//  HomeViewController.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 12/04/23.
//

import UIKit

class HomeViewController: UIViewController {
    var HotelListVM: HotelsInfoListViewModel!
    var searchPerformed: Bool = false
    lazy var activityIndicatorView = UIActivityIndicatorView(style: .large)
    lazy var titleLabel: UILabel = {
        let label = UILabel()
        label.font = .systemFont(ofSize: 23, weight: .black)
        label.textColor = .label
        label.numberOfLines = 0
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    lazy var tableView:UITableView = {
        let tableView = UITableView(frame: .zero, style: .insetGrouped)
        tableView.delegate = self
        tableView.dataSource = self
        tableView.register(CardTableViewCell.self, forCellReuseIdentifier: CardTableViewCell.cellID)
        return tableView
    }()

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        searchPerformed ? activityIndicator(isAnimating: false) :  activityIndicator(isAnimating: true)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        
        configureUI()
    }

    private func configureUI() {
        searchPerformer()
        
        titleLabel.text = "Hoteis em \nRio de Janeiro"
        
        view.backgroundColor = .white
        
        view.addSubview(titleLabel)
        view.addSubview(tableView)
        
        configConstraints()
        configureTableView()
    }

    private func configureTableView() {
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.isScrollEnabled = true
        tableView.separatorStyle = .singleLine
        tableView.backgroundColor = .clear
    }

    // constraints
    private func configConstraints() {
        let safeArea = view.safeAreaLayoutGuide
        NSLayoutConstraint.activate([
            self.titleLabel.topAnchor.constraint(equalTo: safeArea.topAnchor),
            self.titleLabel.leadingAnchor.constraint(equalTo: safeArea.leadingAnchor, constant: 16),
            self.titleLabel.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -16),
            self.tableView.topAnchor.constraint(equalTo: self.titleLabel.bottomAnchor),
            self.tableView.leadingAnchor.constraint(equalTo: safeArea.leadingAnchor),
            self.tableView.trailingAnchor.constraint(equalTo: safeArea.trailingAnchor),
            self.tableView.bottomAnchor.constraint(equalTo: safeArea.bottomAnchor)
        ])
    }
    
    func activityIndicator(isAnimating:Bool) {
        let container = UIView()
        container.frame = CGRect(x: 0, y: 0, width: 0, height: 0)
        activityIndicatorView.center = self.view.center
        view.addSubview(activityIndicatorView)
        activityIndicatorView.color = .black
        self.view.addSubview(container)
        isAnimating ? activityIndicatorView.startAnimating() : activityIndicatorView.stopAnimating()

        
    }
    //    MARK: - Perrform
        func searchPerformer(){
            Services.servicesNetwork.performSearchFetch(query: "rio de janeiro") { result in
                if !result.isEmpty {
                    self.HotelListVM = HotelsInfoListViewModel(resultList: result)
                        DispatchQueue.main.async {
                            self.tableView.reloadData()
                            self.activityIndicator(isAnimating: false)
                            self.searchPerformed = true
                        }
                }
            }
        }
}
extension HomeViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.HotelListVM?.numberOfRowsInSection(section) ?? 0
    }
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: CardTableViewCell.cellID, for: indexPath) as! CardTableViewCell
        if let data = self.HotelListVM?.resultAt(index: indexPath.row) {
            cell.setCellData(selectedHotel: data)

        }
        
        return cell
    }
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 250
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let hotel = HotelListVM.resultAt(index: indexPath.row)
        let vc = DetailsViewController()
        vc.hotel = hotel
        self.navigationController?.pushViewController(vc, animated: true)
    }
        
}
