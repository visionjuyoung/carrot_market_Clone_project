//
//  SetProfileViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/14.
//

import UIKit

class SetProfileViewController: UIViewController {
    
    lazy var signUpDataManager: SignUpDataManager = SignUpDataManager()
    lazy var logInDataManager: LogInDataManager = LogInDataManager()
    
    var userInfoManager = UserInfo.shared
    
    var tempPhoneNum: String = ""
    var tempAddress: String = ""

    @IBOutlet weak var cameraButton: UIButton!
    @IBOutlet weak var profileImageView: UIImageView!
    @IBOutlet weak var nickNameTextField: UITextField!
    @IBOutlet weak var setButton: UIButton!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setInit()
    }
    
    func setInit() {
        cameraButton.layer.borderWidth = 1
        cameraButton.layer.borderColor = UIColor.systemGray4.cgColor
        cameraButton.layer.cornerRadius = 15
        profileImageView.layer.cornerRadius = 65
        profileImageView.layer.borderWidth = 1
        profileImageView.layer.borderColor = UIColor.systemGray4.cgColor
        
        self.nickNameTextField.addAction(UIAction(handler: { _ in
            if self.nickNameTextField.text?.count != 0 {
                self.setButton.backgroundColor = UIColor(named: "carrotMarketColor")
            }
        }), for: .editingChanged)
    }
    
    func presentAlbum() {
        let vc = UIImagePickerController()
        vc.sourceType = .photoLibrary
        vc.delegate = self
        vc.allowsEditing = true
        present(vc, animated: true, completion: nil)
    }
    
    func setActionSheet() {
        let alert = UIAlertController(title: nil, message: nil, preferredStyle: .actionSheet)
        let cancel = UIAlertAction(title: "취소", style: .cancel, handler: nil)
        let album = UIAlertAction(title: "앨범에서 선택", style: .default) { [weak self] (_) in
            self?.presentAlbum()
        }
        alert.addAction(cancel)
        alert.addAction(album)
        
        present(alert, animated: true, completion: nil)
    }
    
    @IBAction func tapView(_ sender: UITapGestureRecognizer) {
        nickNameTextField.resignFirstResponder()
    }
    
    
    @IBAction func setProfileImage(_ sender: UIButton) {
        setActionSheet()
    }
    
    @IBAction func doneButton(_ sender: UIButton) {
        print("회원가입 버튼눌림")
        let image = profileImageView.image
        let data: Data = (image?.pngData())!
        let param: SignUpRequest = SignUpRequest(phoneNumber: tempPhoneNum, address: tempAddress, name: nickNameTextField.text!, images: data)
        signUpDataManager.signUp(delegate: self, withRequest: param)
    }
    
    
    @IBAction func backButton(_ sender: UIButton) {
        dismiss(animated: true, completion: nil)
    }
    
}

extension SetProfileViewController: UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        dismiss(animated: true, completion: nil)
    }
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        if let image = info[.editedImage] as? UIImage {
            profileImageView.image = image
        }
        dismiss(animated: true, completion: nil)
    }
}

extension SetProfileViewController {
    func didSuccessSignUp() {
        let param: LogInRequest = LogInRequest(phoneNumber: tempPhoneNum, password: tempPhoneNum)
        logInDataManager.LogIn(delegate: self, parameter: param)
    }
    
    func didSuccessLogIn(logInResult: LogInResponse) {
        //싱글톤 객체에 로그인시 회원 관련 정보 모든 것 저장
        guard let temploginResult: LogInResult = logInResult.result else {
            return
        }
        guard let name: String = temploginResult.name else {
            return
        }
        guard let address: String = temploginResult.address else {
            return
        }
        guard let phoneNumber: String = temploginResult.phoneNumber else {
            return
        }
        guard let token: String = temploginResult.token else {
            return
        }
        guard let uniqueNumber: String = temploginResult.uniqueNumber else {
            return
        }
        guard let imagePath: String = temploginResult.filePath else {
            return
        }
        userInfoManager.name = name
        userInfoManager.address = address
        userInfoManager.phoneNumber = phoneNumber
        userInfoManager.jwt = token
        userInfoManager.userCode = uniqueNumber
        userInfoManager.imagePath = imagePath
        
        guard let vc = storyboard?.instantiateViewController(withIdentifier: "TabbarController") else {
            return
        }
        present(vc, animated: true, completion: nil)
    }
}
