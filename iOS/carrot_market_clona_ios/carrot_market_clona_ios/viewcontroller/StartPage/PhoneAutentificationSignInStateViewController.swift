//
//  PhoneAutentificationSignInStateViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/10.
//

import UIKit

class PhoneAutentificationSignInStateViewController: UIViewController {
    
    lazy var phoneAutentificationDatamanager: PhoneCertificationDataManager = PhoneCertificationDataManager()
    lazy var phoneAutentificationConfirmDataManager: PhoneCertificationConfirmDataManager = PhoneCertificationConfirmDataManager()
    
    @IBOutlet weak var label1: UILabel!
    @IBOutlet weak var label2: UILabel!
    @IBOutlet weak var stacks: UIStackView!
    @IBOutlet weak var certNotionLabel: UIView!
    @IBOutlet weak var emailLabel: UILabel!
    
    @IBOutlet weak var phoneAutenticationTextField: UITextField!
    @IBOutlet weak var certTextField: UITextField!
    
    @IBOutlet weak var getCertButton: UIButton!
    @IBOutlet weak var confirmCertButton: UIButton!
    
    @IBOutlet weak var movingConstraint: NSLayoutConstraint!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setInit()
    }
    
    func setInit() {
        let attributedString = NSMutableAttributedString.init(string: "이메일로 계정 찾기")
        attributedString.addAttribute(NSAttributedString.Key.underlineStyle, value: 1, range:
            NSRange.init(location: 0, length: attributedString.length));
        emailLabel.attributedText = attributedString
        
        getCertButton.layer.cornerRadius = 5
        confirmCertButton.layer.cornerRadius = 5
        
        self.phoneAutenticationTextField.addAction(UIAction(handler: { _ in
            if self.phoneAutenticationTextField.text?.count == 11 {
                self.getCertButton.backgroundColor = UIColor.darkGray
            }
        }), for: .editingChanged)
        
        self.certTextField.addAction(UIAction(handler: { _ in
            if self.certTextField.text?.count == 6 {
                self.confirmCertButton.backgroundColor = UIColor(named: "carrotMarketColor")
            }
        }), for: .editingChanged)
    }

    @IBAction func getCert(_ sender: UIButton) {
        label1.isHidden = true
        label2.isHidden = true
        stacks.isHidden = true
        certTextField.isHidden = false
        certNotionLabel.isHidden = false
        confirmCertButton.isHidden = false
        movingConstraint.constant -= 130
        UIView.animate(withDuration: 0.1, animations: {
            self.view.layoutSubviews()
        })
    }
    
    @IBAction func confirmCert(_ sender: UIButton) {
        guard let vc = storyboard?.instantiateViewController(withIdentifier: "TabbarController") else { return }
        present(vc, animated: true, completion: nil)
    }
    
    @IBAction func pressBack(_ sender: UIButton) {
        dismiss(animated: true, completion: nil)
    }
}

extension PhoneAutentificationSignInStateViewController {
    func didSuccessCertification(phoneCertificationresult: PhoneCertificationResponse) {
        print(phoneCertificationresult)
    }
    
    func didFailureCertification(phoneCertificationresult: PhoneCertificationResponse) {
        print(phoneCertificationresult)
    }
    
    func didSuccessConfirmCertification(phoneCertificationConfirmResult: PhoneCertificationConfirmResponse) {
        print(phoneCertificationConfirmResult)
    }
    
    func didFailureConfirmCertification(phoneCertificationConfirmResult: PhoneCertificationConfirmResponse) {
        print(phoneCertificationConfirmResult)
    }
}
