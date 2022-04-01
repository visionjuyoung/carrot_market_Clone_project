//
//  SelectProductCategoryViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/27.
//

import UIKit

class SelectProductCategoryViewController: UIViewController {
    
    @IBOutlet weak var tableView: UITableView!
    
    let category: [String] = ["디지털기기", "생활가전", "가구/인테리어", "유아동", "생활/가공식품", "유아도서", "스포츠/레저", "여성잡화", "여성의류", "남성패션/잡화", "게임/취미", "뷰티/미용", "반려동물용품", "도서/티켓/음반", "식물", "기타 중고물품", "삽니다"]

    override func viewDidLoad() {
        super.viewDidLoad()
        setInit()
    }
    
    func setInit() {
        self.tableView.dataSource = self
        self.tableView.delegate = self
    }
    
    @IBAction func back(_ sender: UIBarButtonItem) {
        dismiss(animated: true)
    }
    
}

extension SelectProductCategoryViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return category.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "TitleCell") as? TitleCell else { return UITableViewCell() }
        cell.textLabel?.text = category[indexPath.row]
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        guard let vc = storyboard?.instantiateViewController(withIdentifier: "AddProductViewController") as? AddProductViewController else { return }
        dismiss(animated: true, completion: {
            print(self.category[indexPath.row])
        })
    
    }
}

class TitleCell: UITableViewCell {
    
    @IBOutlet weak var categotyLabel: UILabel!
    
    override func prepareForReuse() {
        
    }
}
