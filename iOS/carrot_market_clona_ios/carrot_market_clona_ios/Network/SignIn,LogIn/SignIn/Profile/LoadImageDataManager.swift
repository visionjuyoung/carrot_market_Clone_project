//
//  LoadImageDataManager.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/28.
//

import Foundation

class LoadImageDataManager {
    func loadImage(filepath: String) -> URL {
        
        let urlString = "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/img?filename=\(filepath)"
        let encodedString = urlString.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
        let url = URL(string: encodedString)!
                
       return url
    }
}
