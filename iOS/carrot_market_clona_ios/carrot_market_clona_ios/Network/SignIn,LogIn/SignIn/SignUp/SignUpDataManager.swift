//
//  SignUpDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/18.
//

import Foundation
import Alamofire

class SignUpDataManager {
    func signUp(delegate: SetProfileViewController, parameter: SignUpRequest) {
        let url = "https://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com"
        AF.request("\(url)/api/auth/signup", method: .post, parameters: parameter, encoder: JSONParameterEncoder(), headers: nil).validate().responseDecodable(of: SignUpResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                if response.isSuccess {
                    delegate.didSuccessSignUp(signUpResult: response)
                } else {
                    print("error")
                }
            case .failure(let error):
                print(error)
            }
        }
    }
}
