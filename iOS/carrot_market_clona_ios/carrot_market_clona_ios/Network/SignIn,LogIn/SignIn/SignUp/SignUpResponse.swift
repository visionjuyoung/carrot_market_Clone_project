//
//  SignUpResponse.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/18.
//

import Foundation
struct SignUpResponse: Decodable {
    var isSuccess: Bool
    var code: Int
    var message: String
    var result: String
}
