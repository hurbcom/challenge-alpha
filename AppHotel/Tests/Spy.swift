@propertyWrapper
struct Spy<Element> {
    private(set) var value: Element
    private(set) var history: [Element] = []

    var count: Int {
        return history.count
    }

    var wrappedValue: Element {
        get { value }
        set {
            history.append(newValue)
            value = newValue
        }
    }

    public var projectedValue: Self {
        get { self }
        set { self = newValue }
    }

    init(wrappedValue: Element) {
        self.value = wrappedValue
    }
}
