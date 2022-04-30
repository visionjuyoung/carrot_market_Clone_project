//
//  BoardDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/30.
//

import Foundation
import Alamofire
class BoardDataManager {
    var userInfoManager = UserInfo.shared
    
    func registBoard(delegate: TownViewController, withRequest: RegistBoardRequest) {
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/board"
        
        let header : HTTPHeaders = [
            "Content-Type" : "multipart/form-data",
            "Authorization" : userInfoManager.jwt
        ]
        
        AF.upload(multipartFormData: { multipartFormData in
            multipartFormData.append(Data(withRequest.content.utf8), withName: "content")
            multipartFormData.append(Data(withRequest.phoneNumber.utf8), withName: "phoneNumber")
            
            multipartFormData.append(withRequest.files, withName: "file", fileName: withRequest.phoneNumber, mimeType: "image/png")
            
        }, to: url, usingThreshold: UInt64.init(), method: .patch, headers: header).responseJSON{(response) in
            if let err = response.error{
                print(err)
                return
            }
        }
    }
    
    func loadBoard(delegate: TownViewController) {
        
        let header : HTTPHeaders = [
            "Authorization" : userInfoManager.jwt
        ]
        
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/board"
        AF.request(url, method: .get, parameters: nil, headers: header).responseDecodable(of: ViewBoardResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                print(response.viewBoardResult)
            case .failure(let error):
                print(error)
            }
        }
    }
    
    func updateBoard(delegate: TownViewController, withRequest: UpdateBoardRequest) {
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/board"
        
        let header : HTTPHeaders = [
            "Content-Type" : "multipart/form-data",
        ]
        
        AF.upload(multipartFormData: { multipartFormData in
            multipartFormData.append(Data(String(withRequest.id).utf8), withName: "id")
            multipartFormData.append(Data(withRequest.content.utf8), withName: "content")
            //multipartFormData.append(Data(withRequest.boardCategory.utf8), withName: "boardCategory")
            
            multipartFormData.append(withRequest.files, withName: "file", fileName: String(withRequest.id), mimeType: "image/png")
            
        }, to: url, usingThreshold: UInt64.init(), method: .patch, headers: header).responseJSON{(response) in
            if let err = response.error{
                print(err)
                return
            }
        }
    }
    
    func deleteBoard(delegate: TownViewController, id: CLong) {
        
        let header : HTTPHeaders = [
            "Authorization" : userInfoManager.jwt
        ]
        
        AF.request("http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/board/\(id)", method: .delete, parameters: nil, headers: header).validate().responseDecodable(of: DeleteBoardResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                print("제거 성공")
            case .failure(let error):
                print(error)
            }
        }
    }

}

