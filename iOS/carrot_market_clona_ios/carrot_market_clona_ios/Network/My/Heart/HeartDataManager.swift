//
//  HeartDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/05.
//

import Foundation
import Alamofire

class HeartDataManager {
    func loadHeart(delegate: HeartViewController, phoneNum: String) {
        let userInfoManager = UserInfo.shared
        
        let header : HTTPHeaders = [
            "Authorization" : userInfoManager.jwt
        ]
        
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/product/likes/\(phoneNum)"
        AF.request(url, method: .get, parameters: nil, headers: header).responseDecodable(of: LoadHeartResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                print(delegate.didSuccessLoadHeartList(response: response))
            case .failure(let error):
                print(error)
            }
        }
    }
}
