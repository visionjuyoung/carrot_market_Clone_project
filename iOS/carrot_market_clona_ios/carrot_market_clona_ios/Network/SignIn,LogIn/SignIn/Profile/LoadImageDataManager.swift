//
//  LoadImageDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/28.
//

import Foundation
import Alamofire
class LoadImageDataManager {
    func loadImage(delegate: SetProfileViewController, filepath: String) {
        
        let userInfoManager = UserInfo.shared
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080"
        let headers : HTTPHeaders = [ "Authorization" : userInfoManager.jwt]
                
        AF.request("\(url)/api/image?filename=\(filepath)", method: .get, parameters: nil, headers: headers).responseDecodable(of: LoadImageResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                print("success")
            case .failure(let error):
                print("error")
            }
        }
    }
}
