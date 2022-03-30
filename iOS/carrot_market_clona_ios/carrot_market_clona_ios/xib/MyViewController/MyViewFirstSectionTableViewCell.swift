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
    
    func setData(name: String, address: String, code: String, image: URL) {
        nameLabel.text = name
        addressLabel.text = "\(address) \(code)"
        
        self.profileImage.load(url: image )
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


