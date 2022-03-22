//
//  UserInfo.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/22.
//

import Foundation
class UserInfo {
    static let shared = UserInfo()
    
    var phoneNumber : String
    var address : String
    var userCode: String
    var jwt: String
    //image 변수
    
    private init() {
        phoneNumber = ""
        address = ""
        userCode = ""
        jwt = ""
    }
}
