import UIKit

extension String {
    var htmlToAttributedString: NSMutableAttributedString? {
        guard let data = data(using: .utf8) else { return nil }
        do {
            return try NSMutableAttributedString(
                data: data,
                options: [
                    .documentType: NSAttributedString.DocumentType.html,
                    .characterEncoding: String.Encoding.utf8.rawValue
                ],
                documentAttributes: nil)
        } catch {
            return nil
        }
    }

    var htmlToString: String {
        return htmlToAttributedString?.string ?? ""
    }
}

extension UILabel {
    func setHTML(_ htmlString: String, fontSize: CGFloat) {
        guard let attrString = htmlString.htmlToAttributedString else {
            text = htmlString
            return
        }

        attrString.addAttributes([
            .font: UIFont.systemFont(ofSize: fontSize)
        ], range: NSRange(location: 0, length: attrString.length))
        attributedText = attrString
    }
}
