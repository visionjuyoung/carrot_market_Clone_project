//
//  LoginRequest.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/17.
//

import Foundation
struct LogInRequest: Encodable {
    var phoneNumber: String
    var password: String
}
