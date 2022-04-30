//
//  DeleteBoardResponse.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/30.
//

import Foundation
struct DeleteBoardResponse: Decodable {
    var isSuccess: Bool
    var code: Int
    var message: String
}
