import UIKit

class BannerCell: UITableViewCell {
    let imgPhoto: UIImageView = {
        let img = UIImageView()
        img.setCodable()
        img.contentMode = .scaleAspectFill
        img.clipsToBounds = true
        img.tintColor = .black
        return img
    }()

    func setupUI() {
        selectionStyle = .none

        contentView.addSubview(imgPhoto)
        setupConstraints()
    }

    func setupConstraints() {
        imgPhoto.setEdgesToSuperview()
        imgPhoto.setHeight(150)
    }

    func configure(coverImage: String) {
        imgPhoto.loadImage(url: coverImage)
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
