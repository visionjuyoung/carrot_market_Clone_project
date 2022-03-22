//
//  SignUpDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/18.
//

import Foundation
import Alamofire

class SignUpDataManager {
    func signUp(delegate: SetProfileViewController, withRequest: SignUpRequest, imgData: Data) {
        let url = "https://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com/api/auth/signup"
        
        let header: [String: String] = [
            "Content-Type" : "Multipart/form-data"
        ]
        
        AF.upload(multipartFormData: { multipartFormData in
            multipartFormData.append(Data(withRequest.phoneNumber.utf8), withName: "phoneNumber")
            multipartFormData.append(Data(withRequest.address.utf8), withName: "address")
            multipartFormData.append(Data(withRequest.name.utf8), withName: "name")
            
            multipartFormData.append(imgData, withName: "file", fileName: "\(withRequest.name).png", mimeType: "image/png")
        }, to: url, method: .post).response { response in
//            switch response.result {
////            case .success(let response):
////                //delegate.didSuccessSignUp(signUpResult: response)
////            case .failure(let error):
////                print(error)
////
//            }
        }
    }
}
