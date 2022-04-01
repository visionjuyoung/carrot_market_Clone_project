//
//  ProductDetailViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/29.
//

import UIKit

class ProductDetailViewController: UIViewController {

    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

    }
    
    func setInit() {
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(UINib(nibName: "firstProductDetailTableViewCell", bundle: nil), forCellReuseIdentifier: "firstProductDetailTableViewCell")
        tableView.register(UINib(nibName: "secondProductDetailTableViewCell", bundle: nil), forCellReuseIdentifier: "secondProductDetailTableViewCell")
        tableView.register(UINib(nibName: "thirdProductDetailTableViewCell", bundle: nil), forCellReuseIdentifier: "thirdProductDetailTableViewCell")
        tableView.register(UINib(nibName: "firthProductDetailTableViewCell", bundle: nil), forCellReuseIdentifier: "firthProductDetailTableViewCell")
        tableView.register(UINib(nibName: "fifthProductDetailTableViewCell", bundle: nil), forCellReuseIdentifier: "fifthProductDetailTableViewCell")
    }
}

extension ProductDetailViewController: UITableViewDelegate, UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "firstProductDetailTableViewCell") as? firstProductDetailTableViewCell else {
            return UITableViewCell()
        }
        return cell
    }
    
    
}
