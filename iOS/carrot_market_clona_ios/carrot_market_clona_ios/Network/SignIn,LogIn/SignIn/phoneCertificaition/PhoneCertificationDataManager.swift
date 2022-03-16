//
//  PhoneCertificationDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/16.
//

import Foundation
import Alamofire

class PhoneCertificationDataManager {
    func certificatePhoneNum(delegate: PhoneAutentificationViewController, phoneNum: String) {
        let url = "https://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com"
        AF.request("\(url)/api/auth/certification?phoneNumber=\(phoneNum)", method: .get, parameters: nil, headers: nil).responseDecodable(of: PhoneCertificationResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                delegate.didSuccessCertification(phoneCertificationresult: response)
            case .failure(let error):
                print(error)
            }
        }
    }
    
    func certificatePhoneNum(delegate: PhoneAutentificationSignInStateViewController, phoneNum: String) {
        let url = "https://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com"
        AF.request("\(url)/api/auth/certification?phoneNumber=\(phoneNum)", method: .get, parameters: nil, headers: nil).responseDecodable(of: PhoneCertificationResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                delegate.didSuccessCertification(phoneCertificationresult: response)
            case .failure(let error):
                print(error)
            }
        }
    }
}
