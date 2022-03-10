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
        phoneNumberTextField.addTarget(self, action: #selector(self.textFieldDidChange(_:)), for: .editingChanged)
    }
    
    func setInit() {
        let attributedString = NSMutableAttributedString.init(string: "이메일로 계정 찾기")
        attributedString.addAttribute(NSAttributedString.Key.underlineStyle, value: 1, range:
            NSRange.init(location: 0, length: attributedString.length));
        emailLabel.attributedText = attributedString
    }
    
    @objc func textFieldDidChange(_ sender: Any?) {
            if self.phoneNumberTextField.text?.count == 11 {
                certButton.layer.borderColor = UIColor.darkGray.cgColor
        }
    }
    
    @IBAction func pressGetCert(_ sender: UIButton) {
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
    
    
    @IBAction func pressBack(_ sender: UIButton) {
        dismiss(animated: true, completion: nil)
    }
}
