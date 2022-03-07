//
//  TownViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/03.
//

import UIKit

class TownViewController: UIViewController {

    @IBOutlet weak var addPostButton: UIButton!
    @IBOutlet weak var tableView: UITableView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setInit()
        // Do any additional setup after loading the view.
    }
    
    func setInit() {
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(UINib(nibName: "TownViewTableViewCell", bundle: nil), forCellReuseIdentifier: "TownViewTableViewCell")
        addPostButton.layer.cornerRadius = 30
    }
}

extension TownViewController: UITableViewDataSource, UITableViewDelegate {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 50
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "TownViewTableViewCell", for: indexPath)
        return cell
    }
    
    
}
