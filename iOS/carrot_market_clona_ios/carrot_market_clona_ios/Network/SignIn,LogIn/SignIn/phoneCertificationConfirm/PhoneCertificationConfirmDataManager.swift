//
//  PhoneCertificationConfirmDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/16.
//

import Foundation
import Alamofire

class PhoneCertificationConfirmDataManager {
    func certificatePhoneNum(delegate: PhoneAutentificationViewController,parameter:PhoneCertificationConfirmRequest) {
        let url = "https://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com"
        AF.request("\(url)/api/auth/certification", method: .post, parameters: parameter, encoder: JSONParameterEncoder(), headers: nil).validate().responseDecodable(of: PhoneCertificationConfirmResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                if response.isSuccess {
                    switch response.code {
                    case 200:
                        print("200")
                        delegate.didSuccessConfirmCertification(phoneCertificationConfirmResult: response)
                    case 201: print("201") //기존에 존재하지 않는 유저
                    default: print("error")
                    }
                } else {
                    print("error")
                }
            case .failure(let error):
                print(error)
            }
        }
    }
    
    func certificatePhoneNum(delegate: PhoneAutentificationSignInStateViewController,parameter:PhoneCertificationConfirmRequest) {
        let url = "https://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com"
        AF.request("\(url)/api/auth/certification", method: .post, parameters: parameter, encoder: JSONParameterEncoder(), headers: nil).validate().responseDecodable(of: PhoneCertificationConfirmResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                if response.isSuccess {
                    switch response.code {
                    case 200: print("200") //기존에 존재하는 유저
                    case 201: print("201") //기존에 존재하지 않는 유저
                    default: print("error")
                    }
                } else {
                    print("error")
                }
            case .failure(let error):
                print(error)
            }
        }
    }
}
