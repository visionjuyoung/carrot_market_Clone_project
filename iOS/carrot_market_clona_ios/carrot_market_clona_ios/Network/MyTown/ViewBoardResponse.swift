//
//  ViewBoardResponse.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/30.
//

import Foundation
struct ViewBoardResponse : Decodable {
    var isSuccess: Bool
    var code: Int
    var message: String
    var viewBoardResult: ViewBoardResult?
}

struct ViewBoardResult: Decodable {
    var id: CLong
    var content: String
    //boardcategory enum case 물어봐야함
    var imagePathList: [String]?
    var member: MemberInfo
}

struct MemberInfo: Decodable {
    var phoneNumber: String
    var name: String
    var address: String
    var uniqueNumber: String
    var filePath: String?
}
