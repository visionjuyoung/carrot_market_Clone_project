//
//  ProductModifyDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/12.
//

import Foundation
import Alamofire

class ProductModifyDataManager {
    var userInfoManager = UserInfo.shared
    
    func productModify(delegate: ProductModifyViewController, withRequest: ProductModifyRequest) {
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/product"
        
        let header : HTTPHeaders = [
            "Content-Type" : "multipart/form-data",
        ]
        
        AF.upload(multipartFormData: { multipartFormData in
            multipartFormData.append("\(withRequest.id)".data(using: .utf8)!, withName: "id")
            multipartFormData.append(Data(withRequest.title.utf8), withName: "title")
            multipartFormData.append(Data(withRequest.content.utf8), withName: "content")
            multipartFormData.append("\(withRequest.price)".data(using: .utf8)!, withName: "price")
            
            if let imageArray = withRequest.files {
                for images in imageArray {
                    let ran = Int.random(in: 0...1000)
                    multipartFormData.append(images,withName: "files", fileName: "\(withRequest.title)\(ran)", mimeType: "image/png")
                }
            }
            
        }, to: url, usingThreshold: UInt64.init(), method: .patch, headers: header).responseJSON{(response) in
            if let err = response.error{
                print(err)
                return
            }
            delegate.didSuccessModifyProduct()
        }
    }
    
    func deleteProduct(delegate: firstSoldViewController, id: CLong) {
        
        let userInfoManager = UserInfo.shared
        
        let header : HTTPHeaders = [
            "Authorization" : userInfoManager.jwt
        ]
        
        let url = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/product/\(id)"
        AF.request(url, method: .delete, parameters: nil, headers: header).responseDecodable(of: ProductDetailResponse.self) { (response) in
            switch response.result {
            case .success(let response):
                delegate.didSuccessDeleteProduct()
            case .failure(let error):
                print(error)
            }
        }
    }
}
