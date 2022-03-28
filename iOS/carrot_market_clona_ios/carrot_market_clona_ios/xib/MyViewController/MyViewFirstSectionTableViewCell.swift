//
//  MyViewFirstSectionTableViewCell.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/07.
//

import UIKit

class MyViewFirstSectionTableViewCell: UITableViewCell {
    
    
    @IBOutlet weak var profileImage: UIImageView!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
        profileImage.layer.cornerRadius = 15
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
