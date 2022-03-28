//
//  ShowListDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/27.
//

import Foundation
import Alamofire

class ShowListDataManager {
    func ShowList(delegate: HomeViewController, address: String) {
        
        let userInfoManager = UserInfo.shared
        
        let urlString = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/product/list?address=\(address)"
        let encodedString = urlString.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
        let url = URL(string: encodedString)!
        
        let headers : HTTPHeaders = ["Authorization" : userInfoManager.jwt]
        
        AF.request(url, method: .get, encoding: URLEncoding.queryString, headers: headers).responseDecodable(of: ShowListResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                delegate.didSuccessShowList(showListResult: response)
            case .failure(let error):
                print(error)
            }
        }
    }
}
