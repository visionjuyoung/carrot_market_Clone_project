//
//  SellProductDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/11.
//

import Foundation
import Alamofire

class SellProductDataManager {
    
    func loadHeart(delegate: firstSoldViewController, phoneNum: String) {
        
        let userInfoManager = UserInfo.shared
        
        let header : HTTPHeaders = [
            "Authorization" : userInfoManager.jwt
        ]
        
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/product/\(phoneNum)/list"
        AF.request(url, method: .get, parameters: nil, headers: header).responseDecodable(of: SellProductResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                delegate.didSucessLoadSellProductList(result: response.result)
            case .failure(let error):
                print(error)
            }
        }
    }
}
