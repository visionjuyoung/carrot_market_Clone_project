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
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080"
        AF.request("\(url)/api/auth/certification", method: .post, parameters: parameter, encoder: JSONParameterEncoder(), headers: nil).validate().responseDecodable(of: PhoneCertificationConfirmResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                guard let check: Bool = response.isSuccess else { return }
                if check {
                    switch response.code {
                    case 200:
                        delegate.didSuccessConfirmCertification(phoneCertificationConfirmResult: response.result)
                    case 201:
                        delegate.didFailureConfirmCertification(phoneCertificationConfirmResult: response.result)
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
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080"
        AF.request("\(url)/api/auth/certification", method: .post, parameters: parameter, encoder: JSONParameterEncoder(), headers: nil).validate().responseDecodable(of: PhoneCertificationConfirmResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                guard let check: Bool = response.isSuccess else { return }
                if check {
                    switch response.code {
                    case 200: //기존에 존재하는 유저
                        print("200")
                        delegate.didSuccessConfirmCertification(phoneCertificationConfirmResult: response)
                    case 201: //기존에 존재하지 않는 유저
                        print("201")
                        //로그인 페이지에서 회원이 존재하지 않는 경우여서 주소에 대한 처리 논의가 필요
                        delegate.didFailureConfirmCertification(phoneCertificationConfirmResult: response)
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
