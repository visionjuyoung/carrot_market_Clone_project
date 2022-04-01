//
//  RegisterProductResponse.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/30.
//

import Foundation

struct RegisterProductResponse:Decodable {
    var isSuccess: Bool?
    var code: Int?
    var message: String?
    var result: CLong?
}
