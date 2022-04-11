//
//  AddHeartResponse.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/05.
//

import Foundation
struct AddHeartResponse: Decodable {
    var isSuccess: Bool?
    var code: Int?
    var message: String?
    var result: CLong?
}
