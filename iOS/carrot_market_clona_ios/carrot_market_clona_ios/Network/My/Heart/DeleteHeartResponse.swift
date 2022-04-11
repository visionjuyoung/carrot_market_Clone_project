//
//  DeleteHeartResponse.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/08.
//

import Foundation
struct DeleteHeartResponse : Decodable {
    var isSucess: Bool?
    var code: Int?
    var message: String?
}
