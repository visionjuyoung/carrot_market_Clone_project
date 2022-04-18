//
//  EditProfileRequest.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/14.
//

import Foundation
struct EditProfileRequest: Encodable {
    var phoneNumber: String
    var address: String
    var name: String
    var file: Data?
}
