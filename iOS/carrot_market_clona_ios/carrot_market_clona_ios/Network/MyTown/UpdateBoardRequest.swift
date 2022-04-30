//
//  UpdateBoardRequest.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/30.
//

import Foundation
struct UpdateBoardRequest: Encodable {
    var id: CLong
    var content: String
    //enum
    var files: Data
}
