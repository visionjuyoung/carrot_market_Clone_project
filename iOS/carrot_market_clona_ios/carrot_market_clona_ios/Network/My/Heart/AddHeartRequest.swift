//
//  AddHeartRequest.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/05.
//

import Foundation
struct AddHeartRequest: Encodable {
    var phoneNumber: String
    var productId: CLong
}
