//
//  ShowListResponse.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/27.
//

import Foundation
struct ShowListResponse: Decodable {
    var isSuccess: Bool
    var code: Int
    var message: String
    var result: [ShowListResult]
}

struct ShowListResult: Decodable {
    var id: CLong?
    var title: String?
    var address: String?
    var price: Int?
    var likes: Int?
    var chats: Int?
    var tradeStatus: String?
    //var modDate: TimeZone?
    var imagePath: String?
}
