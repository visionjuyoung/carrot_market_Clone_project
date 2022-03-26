//
//  SignUpDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/18.
//

import Foundation
import Alamofire

class SignUpDataManager {
    func signUp(delegate: SetProfileViewController, withRequest: SignUpRequest) {
        print("회원가입 api 시작")
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/auth/signup"
        
        let header : HTTPHeaders = [
            "Content-Type" : "multipart/form-data",
        ]
        
        AF.upload(multipartFormData: { multipartFormData in
            multipartFormData.append(Data(withRequest.phoneNumber.utf8), withName: "phoneNumber")
            multipartFormData.append(Data(withRequest.address.utf8), withName: "address")
            multipartFormData.append(Data(withRequest.name.utf8), withName: "name")
            
            multipartFormData.append(withRequest.images!, withName: "file", fileName: "\(withRequest.name).png", mimeType: "image/png")
        }, to: url, usingThreshold: UInt64.init(), method: .post, headers: header).responseJSON{(response) in
            print(response)
                       
            if let err = response.error{
                print(err)
                return
            }
            delegate.didSuccessSignUp()
            print("success")
        }
    }
}
