import UIKit

final class ProductListView: UIView {
    let tableView = UITableView()
    let inputField = InputFieldView()
    let hurbBlue = UIColor(red: 42/255, green: 99/255, blue: 241/255, alpha: 1)

    init() {
        super.init(frame: .zero)
        backgroundColor = .white

        tableView.separatorStyle = .none
        tableView.translatesAutoresizingMaskIntoConstraints = false
        inputField.translatesAutoresizingMaskIntoConstraints = false

        addSubview(inputField)
        addSubview(tableView)

        NSLayoutConstraint.activate([
            inputField.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor),
            inputField.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 20),
            inputField.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -20),
            inputField.heightAnchor.constraint(equalToConstant: 45),

            tableView.topAnchor.constraint(equalTo: inputField.bottomAnchor, constant: 10),
            tableView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 10),
            tableView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -10),
            tableView.bottomAnchor.constraint(equalTo: bottomAnchor, constant: -10)
        ])
    }

    required init?(coder: NSCoder) { nil }
}
