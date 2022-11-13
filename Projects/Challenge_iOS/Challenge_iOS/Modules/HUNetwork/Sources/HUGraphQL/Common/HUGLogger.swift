//
//  File.swift
//  
//
//  Created by Theo Mendes on 04/11/21.
//

import Foundation
import os.log

/// Contains pre-defined OSLog categories
struct HUGLoggerCategory {
    /// Generic app logging category
    static var module: String { return "HUNetwork | HUGraphQL" }
    /// Networking logging category
    static var networking: String { return "HUNetwork | Networking" }
    /// Lifecycle logging category
    static var lifecycle: String { return "HUNetwork | Lifecycle" }
}

/// Wrapper class for os_log function
struct HUGLogger {
    /// Create OSLog with subsystem and category
    static func osLog(subsystem: String = "br.com.hotelurbano.HUNetwork", category: String) -> OSLog {
        return OSLog(subsystem: subsystem, category: category)
    }

    /// Create app log
    static func moduleLog() -> OSLog {
        return OSLog(subsystem: "com.hurb.HUNetwork", category: HUGLoggerCategory.module)
    }

    /// Create networking log
    static func networkingLog() -> OSLog {
        return OSLog(subsystem: "com.hurb.HUNetwork", category: HUGLoggerCategory.networking)
    }

    /// Create lifecycle log
    static func lifecycleLog() -> OSLog {
        return OSLog(subsystem: "com.hurb.HUNetwork", category: HUGLoggerCategory.lifecycle)
    }
}
