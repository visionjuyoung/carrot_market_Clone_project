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
        let url = "https://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com"
        AF.request("\(url)/api/login", method: .get, parameters: parameter, headers: nil).responseDecodable(of: LogInResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                delegate.didSuccessLogIn(logInResult: response)
            case .failure(let error):
                print(error)
            }
        }
    }
    
    func LogIn(delegate: PhoneAutentificationSignInStateViewController, parameter: LogInRequest) {
        let url = "https://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com"
        AF.request("\(url)/api/login", method: .get, parameters: parameter, headers: nil).responseDecodable(of: LogInResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                delegate.didSuccessLogIn(logInResult: response)
            case .failure(let error):
                print(error)
            }
        }
    }
    
    func LogIn(delegate: SetProfileViewController, parameter: LogInRequest) {
        let url = "https://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com"
        AF.request("\(url)/api/login", method: .get, parameters: parameter, headers: nil).responseDecodable(of: LogInResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                delegate.didSuccessLogIn(logInResult: response)
            case .failure(let error):
                print(error)
            }
        }
    }
}
