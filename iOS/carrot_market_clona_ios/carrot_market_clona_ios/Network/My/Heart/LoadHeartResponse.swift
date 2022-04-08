//
//  LoadHeartResponse.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/05.
//

import Foundation
struct LoadHeartResponse: Decodable {
    var isSuccess: Bool
    var code: Int
    var message: String
    var result: [LoadHeartResult]?
}

struct LoadHeartResult: Decodable {
    var id : CLong
    var title: String
    var address: String
    var price: Int
    var likes: Int
    var chats: Int
    var tradeStatus: String
    var imagePath: String?
}
