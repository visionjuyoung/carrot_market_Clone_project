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
    lazy var logInDataManager: LogInDataManager = LogInDataManager()
    
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
            if self.certTextField.text?.count == 4 {
                self.confirmCertButton.backgroundColor = UIColor(named: "carrotMarketColor")
            }
        }), for: .editingChanged)
    }

    @IBAction func getCert(_ sender: UIButton) {
        phoneAutentificationDatamanager.certificatePhoneNum(delegate: self, phoneNum: phoneAutenticationTextField.text!)
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
        let param: PhoneCertificationConfirmRequest = PhoneCertificationConfirmRequest(phoneNumber: phoneAutenticationTextField.text!, number: certTextField.text!)
        phoneAutentificationConfirmDataManager.certificatePhoneNum(delegate: self, parameter: param)
    }
    
    @IBAction func pressBack(_ sender: UIButton) {
        dismiss(animated: true, completion: nil)
    }
}

extension PhoneAutentificationSignInStateViewController {
    func didSuccessCertification(phoneCertificationresult: PhoneCertificationResponse) {
        guard let tempCertResult: String = phoneCertificationresult.result else {
            return
        }
        print("인증번호 : \(tempCertResult)")
    }
    
    func didSuccessConfirmCertification(phoneCertificationConfirmResult: PhoneCertificationConfirmResponse) { //200
        let param: LogInRequest = LogInRequest(phoneNumber: phoneAutenticationTextField.text!, password: phoneAutenticationTextField.text!)
        logInDataManager.LogIn(delegate: self, parameter: param)
    }
    
    func didFailureConfirmCertification(phoneCertificationConfirmResult: PhoneCertificationConfirmResponse) { //201
        print(phoneCertificationConfirmResult)
        let alert = UIAlertController(title: "등록되지 않은 사용자", message: "회원가입이 필요합니다", preferredStyle: .alert)
        let action = UIAlertAction(title: "확인", style: .cancel, handler: { _ in
            guard let vc = self.storyboard?.instantiateViewController(withIdentifier: "SetLocationViewController") as? SetLocationViewController else {
                return
            }
            self.present(vc, animated: true, completion: nil)
        })
        alert.addAction(action)
        present(alert, animated: true)
    }
    
    func didSuccessLogIn(logInResult: LogInResponse) {
        guard let temploginResult: String = logInResult.result else {
            return
        }
        print(logInResult)
        print(temploginResult)
        //로그인 결과 값을 싱글톤에 저장해야함
        
//        guard let vc = storyboard?.instantiateViewController(withIdentifier: "TabbarController") else {
//            return
//        }
//        present(vc, animated: true, completion: nil)
    }
}
