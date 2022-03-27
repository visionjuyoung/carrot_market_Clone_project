//
//  SignUpRequest.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/18.
//

import Foundation
struct SignUpRequest: Encodable {
    var phoneNumber: String
    var address: String
    var name: String
    var images: Data?
}
