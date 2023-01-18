import UIKit

final class ProductListView: UIView {
    let tableView = UITableView()
    let inputField = InputFieldView()
    let segmentedControl = UISegmentedControl(items: ["Hoteis", "Pacotes"])
    let hurbBlue = UIColor(red: 42/255, green: 99/255, blue: 241/255, alpha: 1)

    init() {
        super.init(frame: .zero)
        backgroundColor = .white
        disableAutoresizingMaskIntoConstraints()
        addSubViews()
        setupUI()
        setupConstraints()
    }
    
    private func disableAutoresizingMaskIntoConstraints() {
        tableView.translatesAutoresizingMaskIntoConstraints = false
        inputField.translatesAutoresizingMaskIntoConstraints = false
        segmentedControl.translatesAutoresizingMaskIntoConstraints = false
    }
    
    private func addSubViews() {
        addSubview(segmentedControl)
        addSubview(inputField)
        addSubview(tableView)
    }

    private func setupUI() {
        segmentedControl.selectedSegmentIndex = 0
        segmentedControl.selectedSegmentTintColor = hurbBlue
        segmentedControl.setTitleTextAttributes([
            .foregroundColor: UIColor.darkGray
        ], for: .normal)

        segmentedControl.setTitleTextAttributes([
            .foregroundColor: UIColor.white
        ], for: .selected)
        
        tableView.separatorStyle = .none
    }
    
    required init?(coder: NSCoder) { nil }
    
    private func setupConstraints() {
        NSLayoutConstraint.activate([
            segmentedControl.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor),
            segmentedControl.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 20),
            segmentedControl.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -20),
            segmentedControl.heightAnchor.constraint(equalToConstant: 45),
            
            inputField.topAnchor.constraint(equalTo: segmentedControl.bottomAnchor, constant: 10),
            inputField.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 20),
            inputField.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -20),
            inputField.heightAnchor.constraint(equalToConstant: 45),

            tableView.topAnchor.constraint(equalTo: inputField.bottomAnchor, constant: 10),
            tableView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 10),
            tableView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -10),
            tableView.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -10)
        ])
    }
}
