//
//  MyViewSecondSectionTableViewCell.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/07.
//

import UIKit

class MyViewSecondSectionTableViewCell: UITableViewCell {
    
    @IBOutlet weak var cellMenuImageView: UIImageView!
    @IBOutlet weak var cellMenuTitleLabel: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
}
