//
//  ProductModifyRequest.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/12.
//

import Foundation
struct ProductModifyRequest: Encodable {
    var id: CLong
    var title: String
    var content: String
    var price: Int
    var files: [Data]?
}
