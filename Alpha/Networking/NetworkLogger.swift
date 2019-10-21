//
//  NetworkLogger.swift
//  Alpha
//
//  Created by Theo Mendes on 21/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Moya
import os.log

final class NetworkLoggerPlugin: PluginType {
    public func willSend(_ request: RequestType, target: TargetType) {
        guard let requestURL = request.request?.url?.absoluteString else { return }

        os_log("📶 ⬆️ Request URL: %s | %@",
               log: Logger.networkingLog(),
               type: .info, "\(requestURL)", "\(self)")
    }

    public func didReceive(_ result: Result<Response, MoyaError>, target: TargetType) {
        switch result {
        case .success(let response):
            if 200..<400 ~= (response.statusCode) {
                os_log("📶 ⬇️ ✅ STATUS CODE: %d | %@",
                       log: Logger.networkingLog(),
                       type: .info, response.statusCode, "\(self)")
            }
        case .failure(let error):
            os_log("📶 ⬇️ ⚠️  %@ %@",
                   log: Logger.networkingLog(),
                   type: .error, "\(error)", "\(self)")
        }
    }
}
