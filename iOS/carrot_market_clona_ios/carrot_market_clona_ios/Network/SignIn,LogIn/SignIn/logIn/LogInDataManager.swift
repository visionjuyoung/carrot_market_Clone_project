//
//  LogInDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/17.
//

import Foundation
import Alamofire

class LogInDataManager {
    func LogIn(delegate: PhoneAutentificationViewController, parameter: LogInRequest) {
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080"
        AF.request("\(url)/api/login", method: .post, parameters: parameter, encoder: JSONParameterEncoder(), headers: nil).validate().responseDecodable(of: LogInResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                delegate.didSuccessLogIn(logInResult: response)
            case .failure(let error):
                print(error)
            }
        }
    }
    
    func LogIn(delegate: PhoneAutentificationSignInStateViewController, parameter: LogInRequest) {
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080"
        AF.request("\(url)/api/login", method: .post, parameters: parameter, encoder: JSONParameterEncoder(), headers: nil).validate().responseDecodable(of: LogInResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                delegate.didSuccessLogIn(logInResult: response)
            case .failure(let error):
                print(error)
            }
        }
    }
    
    func LogIn(delegate: SetProfileViewController, parameter: LogInRequest) {
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080"
        AF.request("\(url)/api/login", method: .post, parameters: parameter, encoder: JSONParameterEncoder(), headers: nil).validate().responseDecodable(of: LogInResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                delegate.didSuccessLogIn(logInResult: response)
            case .failure(let error):
                print(error)
            }
        }
    }
}
