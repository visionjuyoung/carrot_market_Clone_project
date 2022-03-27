//
//  ShowListDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/27.
//

import Foundation
import Alamofire

class ShowListDataManager {
    func ShowList(delegate: HomeViewController, address: String, page: Int) {
        
        let userInfoManager = UserInfo.shared
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080"
        let headers : HTTPHeaders = ["Authorization" : userInfoManager.jwt]
        
        AF.request("\(url)/api/product/list?address=\(address)&page=\(page)", method: .get, parameters: nil, headers: headers).responseDecodable(of: ShowListResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                print(response.result)
                delegate.didSuccessShowList()
            case .failure(let error):
                print(error)
            }
        }
    }
}
