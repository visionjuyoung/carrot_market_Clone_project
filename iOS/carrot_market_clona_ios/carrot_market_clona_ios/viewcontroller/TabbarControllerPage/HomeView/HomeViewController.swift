//
//  HomeViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/03.
//

import UIKit

class HomeViewController: UIViewController {
    
    lazy var showListDataManager: ShowListDataManager = ShowListDataManager()
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var addProductButton: UIButton!
    
    var userInfoManager = UserInfo.shared
    
    var tempListResult: [ShowListResult] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setInit()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        showListDataManager.ShowList(delegate: self, address: userInfoManager.address)
    }
    
    func setInit() {
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(UINib(nibName: "HomeViewTableViewCell", bundle: nil), forCellReuseIdentifier: "HomeViewTableViewCell")
        addProductButton.layer.cornerRadius = 30
    }
    
    @IBAction func addProductMenu(_ sender: UIButton) {
        guard let vc = storyboard?.instantiateViewController(withIdentifier: "SelectHomeDetailViewController") as? SelectHomeDetailViewController else { return }
        present(vc, animated: false, completion: nil)
    }
    
}

extension HomeViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return tempListResult.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "HomeViewTableViewCell", for: indexPath) as? HomeViewTableViewCell else {
            return UITableViewCell()
        }
        cell.price.text = "\(tempListResult[indexPath.row].price ?? 0)"
        cell.productName.text = "\(tempListResult[indexPath.row].title ?? "" )"
        cell.addressWithTime.text = "\(tempListResult[indexPath.row].address ?? "" )"
        return cell
    }
}

extension HomeViewController {
    func didSuccessShowList(showListResult: ShowListResponse) {
        tempListResult = showListResult.result
        tableView.reloadData()
    }
}
