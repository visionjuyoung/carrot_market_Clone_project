//
//  ProductDetailResponse.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/05.
//

import Foundation

struct ProductDetailResponse: Decodable {
    var isSuccess: Bool
    var code: Int
    var message: String
    var result: ProductDetailResult?
}

struct ProductDetailResult: Decodable {
    var id : CLong?
    var title: String?
    var price: Int?
    var content: String?
    var address: String?
    var views: Int?
    var likes: Int?
    var chats: Int?
    var phoneNumber: String?
    var imagePathList: [String]?
    var tradeStatus: String?
    var member: Member?
}

struct Member: Decodable {
    var phoneNumber: String
    var name: String
    var address: String
}
