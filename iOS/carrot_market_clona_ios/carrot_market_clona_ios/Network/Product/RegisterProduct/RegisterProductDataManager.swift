//
//  RegisterProductDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/30.
//

import Foundation
import Alamofire

class RegisterProductDataManager {
    func signUp(delegate: AddProductViewController, withRequest: RegisterProductRequest) {
        
        let userInfoManager = UserInfo.shared
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/product"
        
        let header : HTTPHeaders = [
            "Content-Type" : "multipart/form-data",
            "Authorization" : userInfoManager.jwt
        ]
        
        AF.upload(multipartFormData: { multipartFormData in
            multipartFormData.append(Data(withRequest.title.utf8), withName: "title")
            multipartFormData.append(Data(withRequest.content.utf8), withName: "content")
            multipartFormData.append(Data(withRequest.address.utf8), withName: "address")
            multipartFormData.append("\(withRequest.price)".data(using: .utf8)!, withName: "price")
            multipartFormData.append(Data(withRequest.phoneNumber.utf8), withName: "phoneNumber")
            
            if let imageArray = withRequest.file {
                for images in imageArray {
                    let ran = Int.random(in: 0...1000)
                    multipartFormData.append(images,withName: "files", fileName: "\(withRequest.title)\(ran)", mimeType: "image/png")
                }
            }
            
        }, to: url, usingThreshold: UInt64.init(), method: .post, headers: header).responseDecodable(of: RegisterProductResponse.self){(response) in
            if let err = response.error{
                print(err)
                return
            }
            delegate.didSuccessRegistProduct()
        }
    }
}
