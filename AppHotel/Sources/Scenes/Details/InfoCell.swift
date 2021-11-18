import UIKit

class InfoCell: UITableViewCell {
    let infoView: UIView = {
        let view = UIView()
        view.backgroundColor = .white
        view.setCodable()
        return view
    }()

    let imgPrice: UIImageView = {
        let img = UIImageView()
        img.image = UIImage(systemName: "dollarsign.square.fill")
        img.tintColor = .black
        img.setCodable()
        img.contentMode = .scaleAspectFit
        return img
    }()

    let lblPrice: UILabel = {
        let lbl = UILabel()
        lbl.font = UIFont.systemFont(ofSize: 12)
        lbl.textColor = .black
        lbl.setCodable()
        return lbl
    }()

    let lblAddress: UILabel = {
        let lbl = UILabel()
        lbl.font = UIFont.systemFont(ofSize: 12)
        lbl.textColor = .black
        lbl.setCodable()
        return lbl
    }()

    let imgType: UIImageView = {
        let img = UIImageView()
        img.setCodable()
        img.contentMode = .scaleAspectFit
        return img
    }()

    let lblType: UILabel = {
        let lbl = UILabel()
        lbl.font = UIFont.systemFont(ofSize: 12)
        lbl.textColor = .black
        lbl.setCodable()
        return lbl
    }()

    func setupUI() {
        selectionStyle = .none

        contentView.addSubview(infoView)

        infoView.addSubview(imgPrice)
        infoView.addSubview(lblPrice)
        infoView.addSubview(lblAddress)
        infoView.addSubview(imgType)
        infoView.addSubview(lblType)
        setupConstraints()
    }

    func setupConstraints() {
        // Info
        infoView.setEdgesToSuperview(constant: 10)

        // - Address
        lblAddress.setEdgesToSuperview(excluding: [.bottom])

        // Price View
        // - Icon
        imgPrice.setTop(to: lblAddress.bottomAnchor, constant: 5)
        imgPrice.setEdgesToSuperview(excluding: [.trailing, .top])
        // - Price
        lblPrice.setLeading(to: imgPrice.trailingAnchor, constant: 5)
        lblPrice.setCenterY(to: imgPrice.centerYAnchor)

        // Type View
        // - Icon
        imgType.setTop(to: lblAddress.bottomAnchor, constant: 5)
        imgType.setEdgesToSuperview(excluding: [.leading, .top])
        // - Label
        lblType.setTrailing(to: imgType.leadingAnchor, constant: 5)
        lblType.setCenterY(to: imgType.centerYAnchor)
    }

    func configure(result: SearchResult) {
        lblAddress.text = result.address
        lblPrice.text = "\(result.price)"
        lblType.text = result.category.name
        imgType.image = result.category.icon
        imgType.tintColor = result.category.color
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
