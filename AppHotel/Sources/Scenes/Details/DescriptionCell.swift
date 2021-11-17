import UIKit

class DescriptionCell: UITableViewCell {
    let paddedView: UIView = {
        let view = UIView()
        view.backgroundColor = .clear
        view.setCodable()
        return view
    }()

    let lblDescription: UILabel = {
        let lbl = UILabel()
        lbl.textColor = .lightGray
        lbl.numberOfLines = 0
        lbl.setCodable()
        return lbl
    }()

    func setupUI() {
        selectionStyle = .none

        contentView.addSubview(paddedView)
        paddedView.addSubview(lblDescription)
        setupConstraints()
    }

    func setupConstraints() {
        // Card View
        paddedView.setEdgesToSuperview(constant: 10)
        lblDescription.setEdgesToSuperview()
    }

    func configure(description: String) {
        lblDescription.setHTML(description, fontSize: 12)
    }

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupUI()
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setupUI()
    }
}
