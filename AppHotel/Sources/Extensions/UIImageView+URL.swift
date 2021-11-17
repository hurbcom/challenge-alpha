import UIKit
import Kingfisher

extension UIImageView {
    func loadImage(url path: String) {
        let url = URL(string: path)
        kf.setImage(
            with: url,
            placeholder: UIImage(systemName: "moon.zzz"),
            options: [
                .transition(.fade(1))
            ])
    }
}

