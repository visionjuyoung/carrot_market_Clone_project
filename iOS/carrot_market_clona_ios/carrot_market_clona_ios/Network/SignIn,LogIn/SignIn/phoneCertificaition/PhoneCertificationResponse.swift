//
//  PhoneCertificationResponse.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/16.
//

import Foundation

struct PhoneCertificationResponse: Decodable {
    var isSucess: Bool?
    var code: Int?
    var message: String?
    var result: String?
}
