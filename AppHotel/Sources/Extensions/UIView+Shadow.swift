import UIKit

extension UIView {
    func setShadow(color: UIColor = .black, animated: Bool = false) {
        UIView.animate(withDuration: animated ? 0.3 : 0) {
            self.layer.shadowColor = color.cgColor
            self.layer.shadowOpacity = 0.5
            self.layer.shadowOffset = .zero
            self.layer.shadowRadius = 2
        }
    }
}
