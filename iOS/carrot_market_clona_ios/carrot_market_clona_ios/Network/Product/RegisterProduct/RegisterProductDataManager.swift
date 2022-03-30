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
        print("register 시작")
        
        let userInfoManager = UserInfo.shared
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/product/register"
        
        let header : HTTPHeaders = [
            "Content-Type" : "multipart/form-data",
            "Authorization" : userInfoManager.jwt
        ]
        
        AF.upload(multipartFormData: { multipartFormData in
            multipartFormData.append(Data(withRequest.title.utf8), withName: "title")
            multipartFormData.append(Data(withRequest.content.utf8), withName: "content")
            multipartFormData.append(Data(withRequest.address.utf8), withName: "address")
            multipartFormData.append(Data(String(withRequest.price).utf8), withName: "price")
            multipartFormData.append(Data(withRequest.phoneNumber.utf8), withName: "phoneNumber")
            
            if let images = withRequest.file {
                var i = 0
                for img in images {
                    i = i+1
                    let num = Int.random(in: 0...100)
                    multipartFormData.append(img, withName: "file", fileName: "\(userInfoManager.userCode)\(i)\(num).png", mimeType: "image/png")
                }
            }
        }, to: url, usingThreshold: UInt64.init(), method: .post, headers: header).responseJSON{(response) in
            if let err = response.error{
                print(err)
                return
            }
            print("상품 등록 성공")
        }
    }
}