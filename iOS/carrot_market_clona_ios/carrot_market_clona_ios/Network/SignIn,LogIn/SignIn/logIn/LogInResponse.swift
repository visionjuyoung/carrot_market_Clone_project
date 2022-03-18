//
//  LogInResponse.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/17.
//

import Foundation
struct LogInResponse: Decodable {
    var isSuccess: Bool
    var code: Int
    var message: String
    var result: String
}
