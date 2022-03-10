//
//  PhoneAutentificationViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/10.
//

import UIKit

class PhoneAutentificationViewController: UIViewController {

    @IBOutlet weak var emailLabel: UILabel!
    @IBOutlet weak var phoneNumberTextField: UITextField!
    @IBOutlet weak var certButton: UIButton!
    @IBOutlet weak var movingConstraint: NSLayoutConstraint!
    @IBOutlet weak var label1: UILabel!
    @IBOutlet weak var label2: UILabel!
    @IBOutlet weak var certTextField: UITextField!
    @IBOutlet weak var stacks: UIStackView!
    @IBOutlet weak var certNotionLabel: UILabel!
    @IBOutlet weak var certConfirmButton: UIButton!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setInit()
    }
    
    func setInit() {
        let attributedString = NSMutableAttributedString.init(string: "이메일로 계정 찾기")
        attributedString.addAttribute(NSAttributedString.Key.underlineStyle, value: 1, range:
            NSRange.init(location: 0, length: attributedString.length));
        emailLabel.attributedText = attributedString
        
        certButton.layer.cornerRadius = 5
        certConfirmButton.layer.cornerRadius = 5
        
        self.phoneNumberTextField.addAction(UIAction(handler: { _ in
            if self.phoneNumberTextField.text?.count == 11 {
                self.certButton.backgroundColor = UIColor.darkGray
            }
        }), for: .editingChanged)
        
        self.certTextField.addAction(UIAction(handler: { _ in
            if self.certTextField.text?.count == 6 {
                self.certConfirmButton.backgroundColor = UIColor.darkGray
            }
        }), for: .editingChanged)
    }
    
    @IBAction func pressGetCert(_ sender: UIButton) {
        //인증번호 요청 api 사용 코드 작성
        label1.isHidden = true
        label2.isHidden = true
        stacks.isHidden = true
        certTextField.isHidden = false
        certNotionLabel.isHidden = false
        certConfirmButton.isHidden = false
        movingConstraint.constant -= 130
        UIView.animate(withDuration: 0.1, animations: {
            self.view.layoutSubviews()
        })
    }
    
    @IBAction func confirmCert(_ sender: UIButton) {
        //인증번호 확인 api 사용 후 성공시 이동 코드 작성
    }
    
    @IBAction func pressBack(_ sender: UIButton) {
        dismiss(animated: true, completion: nil)
    }
}
