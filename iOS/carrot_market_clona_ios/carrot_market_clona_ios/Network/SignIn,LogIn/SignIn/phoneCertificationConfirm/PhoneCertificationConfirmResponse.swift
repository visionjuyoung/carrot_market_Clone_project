//
//  PhoneCertificationConfirmResponse.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/16.
//

import Foundation
struct PhoneCertificationConfirmResponse: Decodable {
    var isSuccess: Bool?
    var code: Int?
    var message: String?
    var result: PhoneCertificationConfirmresult?
}

struct PhoneCertificationConfirmresult: Decodable {
    var phoneNumber: String
    var password: String
    var name: String
    var address: String
    var uniqueNumber: String?
    var filePath: String?
    var roleSet: [String]
}
