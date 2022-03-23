//
//  LogInResponse.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/17.
//

import Foundation
struct LogInResponse: Decodable {
    var code: Int?
    var message: String?
    var isSuccess: Bool?
    var result: LogInResult?
}

struct LogInResult: Decodable {
    var phoneNumber: String?
    var address: String?
    var name: String?
    var filePath: String?
    var uniqueNumber : String?
    var token: String?
}
