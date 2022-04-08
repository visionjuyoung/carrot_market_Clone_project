//
//  HeartDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/05.
//

import Foundation
import Alamofire

class HeartDataManager {
    let userInfoManager = UserInfo.shared
    
    func loadHeart(delegate: HeartViewController, phoneNum: String) {
        
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
    
    func addHeart(delegate: ProductDetailViewController, parameter: AddHeartRequest) {
        
        let header : HTTPHeaders = [
            "Authorization" : userInfoManager.jwt
        ]
        
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080"
        
        AF.request("\(url)/api/likes", method: .post, parameters: parameter, encoder: JSONParameterEncoder(), headers: header).validate().responseDecodable(of: AddHeartResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                delegate.didSuccessAddHeart(response: response)
            case .failure(let error):
                print(error)
            }
        }
    }
    
    func deleteHeart(delegate: ProductDetailViewController, parameter: DeleteHeartRequest) {
        
        let header : HTTPHeaders = [
            "Authorization" : userInfoManager.jwt
        ]
        
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080"
        
        AF.request("\(url)/api/likes", method: .delete, parameters: parameter, encoder: JSONParameterEncoder(), headers: header).validate().responseDecodable(of: DeleteHeartResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                print(response.message)
            case .failure(let error):
                print(error)
            }
        }
    }
}
