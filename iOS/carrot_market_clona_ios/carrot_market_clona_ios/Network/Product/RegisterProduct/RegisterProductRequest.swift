//
//  RegisterProductRequest.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/30.
//

import Foundation

struct RegisterProductRequest: Encodable {
    var title: String
    var content: String
    var address: String
    var price: Int
    var phoneNumber: String
    var file: [Data]?
}
