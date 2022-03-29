//
//  MyViewFirstSectionTableViewCell.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/07.
//

import UIKit

class MyViewFirstSectionTableViewCell: UITableViewCell {
    
    
    @IBOutlet weak var profileImage: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var addressLabel: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        profileImage.layer.cornerRadius = 30
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    func setData(name: String, address: String, code: String, image: String) {
        nameLabel.text = name
        addressLabel.text = "\(address) \(code)"
        
        let url = URL(string: "http://ec2-52-78-102-243.ap-northeast-2.compute.amazonaws.com:8080/api/img?filename=/home/ubuntu/upload/7ff4a46e-864d-4514-837a-e4789861a71f_%EC%A1%B0%EC%A1%B0%EC%A1%B0.png")
        let xurl = URL(string: "https://tour.chungbuk.go.kr/site/www/images/common/no_img.jpg")
        self.profileImage.load(url: url ?? xurl!)
    }
}

extension UIImageView {
    func load(url: URL) {
        DispatchQueue.global().async {
            [weak self] in if let data = try? Data(contentsOf: url){
                if let image = UIImage(data: data) {
                    DispatchQueue.main.async {
                        self?.image = image
                    }
                }
            }
        }
    }
}


