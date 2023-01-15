import UIKit

extension UIImageView {
    func from(url string: String) {
        guard let url = URL(string: string) else { return }

        URLSession.shared.dataTask(with: url) { data, response, error in
            DispatchQueue.main.async { [weak self] in
                guard let self, let data else { return }
                self.image = UIImage(data: data)
            }
        }.resume()
    }
}
