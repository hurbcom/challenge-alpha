import Foundation

final class SuggestionsViewModel {
    // MARK: Properties
    
    let suggestions: [Suggestion]
    
    // MARK: Init
    
    init(model: [Suggestion]) {
        self.suggestions = model
    }
}
