//
//  EditProfileResponse.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/14.
//

import Foundation
struct EditProfileResponse: Decodable {
    var isSuccess: Bool
    var code: Int
    var message: String
}
