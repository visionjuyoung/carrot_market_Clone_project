//
//  RegistBoardResponse.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/30.
//

import Foundation

struct RegistBoardResponse : Decodable {
    var isSuccess: Bool
    var code: Int
    var message: String
    var Result: CLong
}
