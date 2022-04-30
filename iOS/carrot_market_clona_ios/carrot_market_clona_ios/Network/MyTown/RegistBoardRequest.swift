//
//  RegistBoardRequest.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/30.
//

import Foundation
struct RegistBoardRequest: Encodable {
    var phoneNumber: String
    var content: String
    // enum 성훈이한테 case 물어봐야함
    var files: Data
}
