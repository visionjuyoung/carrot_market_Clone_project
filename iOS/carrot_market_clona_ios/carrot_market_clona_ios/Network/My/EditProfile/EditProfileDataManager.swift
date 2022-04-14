//
//  EditProfileDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/14.
//

import Foundation
import Alamofire

class EditProfileDataManager {
    func productModify(delegate: EditProfileViewController, withRequest: EditProfileRequest) {
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/auth/edit"
        
        let header : HTTPHeaders = [
            "Content-Type" : "multipart/form-data",
        ]
        
        AF.upload(multipartFormData: { multipartFormData in
            multipartFormData.append(Data(withRequest.phoneNumber.utf8), withName: "phoneNumber")
            multipartFormData.append(Data(withRequest.address.utf8), withName: "address")
            multipartFormData.append(Data(withRequest.name.utf8), withName: "name")
            
            multipartFormData.append(withRequest.file!, withName: "file", fileName: withRequest.name, mimeType: "image/png")
            
        }, to: url, usingThreshold: UInt64.init(), method: .patch, headers: header).responseJSON{(response) in
            if let err = response.error{
                print(err)
                return
            }
            delegate.didSuccessEditProfile()
        }
    }
}
