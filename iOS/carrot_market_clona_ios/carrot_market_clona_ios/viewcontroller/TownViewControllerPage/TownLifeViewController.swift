//
//  TownLifeViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/18.
//

import UIKit

class TownLifeViewController: UIViewController, UITextViewDelegate {

    @IBOutlet weak var contentTextView: UITextView!
    @IBOutlet weak var modelView: UIView!
    @IBOutlet weak var modelViewBottomLayout: NSLayoutConstraint!
    @IBOutlet weak var backView: UIView!
    @IBOutlet weak var tableView: UITableView!
    
    let textViewPlaceHolder = "우리 동네 관련된 질문이나 이야기를 해보세요."
    let original = ["동네질문", "동네사건사고"]
    let myFavorite = ["동네사진전","동네맛집","취미생활","분실/실종센터","해주세요","일상","고양이","강아지","건강","살림","인테리어","교육/학원","출산/육아","기타"]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setInit()
        contentTextView.text = textViewPlaceHolder
        contentTextView.textColor = .lightGray
        contentTextView.delegate = self
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        let spacing: CGFloat = 0.0
        backView.isHidden = false
        modelViewBottomLayout.constant = spacing
        UIView.animate(withDuration: 1, animations: {
            self.view.layoutIfNeeded()
        })
    }
    
    func setInit(){
        tableView.dataSource = self
        tableView.delegate = self
    }
    
    func textViewDidBeginEditing(_ contentTextView: UITextView) {
        if contentTextView.text == textViewPlaceHolder {
            contentTextView.text = nil
            contentTextView.textColor = .black
        }
    }
    
    func textViewDidEndEditing(_ contentTextView: UITextView) {
        if contentTextView.text.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty {
            contentTextView.text = textViewPlaceHolder
            contentTextView.textColor = .lightGray
        }
    }
    
    @IBAction func tabView(_ sender: UITapGestureRecognizer) {
        let spacing: CGFloat = -1000.0
        backView.isHidden = true
        modelViewBottomLayout.constant = spacing
        UIView.animate(withDuration: 1, animations: {
            self.view.layoutIfNeeded()
        })
    }
    
    @IBAction func setCategoryButton(_ sender: UIButton) {
        if modelViewBottomLayout.constant == -1000.0 {
            let spacing: CGFloat = 0.0
            backView.isHidden = false
            modelViewBottomLayout.constant = spacing
            UIView.animate(withDuration: 1, animations: {
                self.view.layoutIfNeeded()
            })
        } else {
            let spacing: CGFloat = -1000.0
            backView.isHidden = true
            modelViewBottomLayout.constant = spacing
            UIView.animate(withDuration: 1, animations: {
                self.view.layoutIfNeeded()
            })
        }
    }
    
    @IBAction func tabBackStateButton(_ sender: UIButton) {
        let spacing: CGFloat = -1000.0
        backView.isHidden = true
        modelViewBottomLayout.constant = spacing
        UIView.animate(withDuration: 1, animations: {
            self.view.layoutIfNeeded()
        })
    }
}

extension TownLifeViewController: UITableViewDelegate, UITableViewDataSource {
    func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        switch section {
        case 0:
            return original.count
        case 1:
            return myFavorite.count
        default:
            return 0
        }
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        switch section {
        case 0:
            return "기본 주제"
        case 1:
            return "내 관심 주제"
        default:
            return ""
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "tempCell") as? tempCell else { return UITableViewCell()}
        if indexPath.section == 0 {
            cell.textLabel?.text = original[indexPath.row]
        } else if  indexPath.section == 1 {
            cell.textLabel?.text = myFavorite[indexPath.row]
        }
        return cell
    }
}

class tempCell: UITableViewCell {
    
}
