//
//  ChattingViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/03.
//

import UIKit

class ChattingViewController: UIViewController {
    
    @IBOutlet weak var tableView: UITableView!
    
    var ThereIsChatting: Bool = false // 채팅이 있는지 없는지 확인 나중에 채팅불러오는 API 로 변경해야 함

    override func viewDidLoad() {
        super.viewDidLoad()
        setInit()
    }
    
    func setInit() {
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(UINib(nibName: "ChattingViewTableViewCell", bundle: nil), forCellReuseIdentifier: "ChattingViewTableViewCell")
        if ThereIsChatting {
            tableView.isHidden = false
        } else {
            tableView.isHidden = true
        }
    }
}

extension ChattingViewController: UITableViewDelegate, UITableViewDataSource{
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ChattingViewTableViewCell", for: indexPath)
        return cell
    }
    
    
}
