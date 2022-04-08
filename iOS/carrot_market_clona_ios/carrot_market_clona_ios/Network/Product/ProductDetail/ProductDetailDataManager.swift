//
//  ProductDetailDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/05.
//

import Foundation
import Alamofire

class ProductDetailDataManager {
    func loadProductInfo(delegate: ProductDetailViewController, id: CLong, phoneNum: String) {
        
        let userInfoManager = UserInfo.shared
        
        let header : HTTPHeaders = [
            "Authorization" : userInfoManager.jwt
        ]
        
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/product/\(id)?phoneNumber=\(phoneNum)"
        AF.request(url, method: .get, parameters: nil, headers: header).responseDecodable(of: ProductDetailResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                delegate.didSuccessLoadProductInfo(result: response.result)
            case .failure(let error):
                print(error)
            }
        }
    }
}
