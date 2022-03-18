//
//  PhoneAutentificationViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/10.
//

import UIKit

class PhoneAutentificationViewController: UIViewController {
    
    lazy var phoneCertificationDataManager: PhoneCertificationDataManager = PhoneCertificationDataManager()
    lazy var phoneAutentificationConfirmDataManager: PhoneCertificationConfirmDataManager = PhoneCertificationConfirmDataManager()
    lazy var logInDataManager: LogInDataManager = LogInDataManager()
    
    var tempAddress: String = ""

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
            if self.certTextField.text?.count == 4 {
                self.certConfirmButton.backgroundColor = UIColor(named: "carrotMarketColor")
            }
        }), for: .editingChanged)
    }
    
    @IBAction func pressGetCert(_ sender: UIButton) {
        phoneCertificationDataManager.certificatePhoneNum(delegate: self, phoneNum: phoneNumberTextField.text!)
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
        let param: PhoneCertificationConfirmRequest = PhoneCertificationConfirmRequest(phoneNumber: phoneNumberTextField.text!, number: certTextField.text!)
        phoneAutentificationConfirmDataManager.certificatePhoneNum(delegate: self, parameter: param)
        
    }
    
    @IBAction func pressBack(_ sender: UIButton) {
        dismiss(animated: true, completion: nil)
    }
}

extension PhoneAutentificationViewController {
    func didSuccessCertification(phoneCertificationresult: PhoneCertificationResponse) {
        print(phoneCertificationresult.result)
    }
    
    func didFailureCertification(phoneCertificationresult: PhoneCertificationResponse) {
        print(phoneCertificationresult.message)
    }
    
    func didSuccessConfirmCertification(phoneCertificationConfirmResult: PhoneCertificationConfirmResponse) { //200
        let param: LogInRequest = LogInRequest(phoneNumber: phoneNumberTextField.text!, password: phoneNumberTextField.text!)
        logInDataManager.LogIn(delegate: self, parameter: param)
    }
    
    func didFailureConfirmCertification(phoneCertificationConfirmResult: PhoneCertificationConfirmResponse) { //201
        guard let vc = storyboard?.instantiateViewController(withIdentifier: "SetProfileViewController") as? SetProfileViewController else {
            return
        }
        vc.tempPhoneNum = phoneNumberTextField.text!
        vc.tempAddress = tempAddress
        present(vc, animated: true, completion: nil)
    }
    
    func didSuccessLogIn(logInResult: LogInResponse) {
        //로그인 결과 값을 싱글톤에 저장해야함
        guard let vc = storyboard?.instantiateViewController(withIdentifier: "TabbarController") else {
            return
        }
        present(vc, animated: true, completion: nil)
    }
}
