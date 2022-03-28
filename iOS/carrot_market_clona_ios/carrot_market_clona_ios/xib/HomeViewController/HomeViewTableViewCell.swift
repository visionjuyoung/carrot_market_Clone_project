//
//  HomeViewTableViewCell.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/05.
//

import UIKit

class HomeViewTableViewCell: UITableViewCell {

    @IBOutlet weak var productImageView: UIImageView!
    @IBOutlet weak var messageImage: UIImageView!
    @IBOutlet weak var messageCount: UILabel!
    @IBOutlet weak var heartImage: UIImageView!
    @IBOutlet weak var heartCount: UILabel!
    @IBOutlet weak var productName: UILabel!
    @IBOutlet weak var addressWithTime: UILabel!
    @IBOutlet weak var price: UILabel!
    
    
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        productImageView.layer.cornerRadius = 5
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

    }
    
}
