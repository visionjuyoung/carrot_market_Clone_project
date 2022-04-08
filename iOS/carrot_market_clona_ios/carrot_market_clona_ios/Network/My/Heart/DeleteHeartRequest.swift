//
//  DeleteHeartRequest.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/08.
//

import Foundation
struct DeleteHeartRequest: Encodable {
    var phoneNumber: String
    var productId: CLong
}
