//
//  EditProfileViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/14.
//

import UIKit

class EditProfileViewController: UIViewController {
    
    lazy var editProfileDataManager: EditProfileDataManager = EditProfileDataManager()
    
    let userInfoManager = UserInfo.shared
    
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
    
    @IBAction func tabView(_ sender: Any) {
        nickNameTextField.resignFirstResponder()
    }
    
    @IBAction func setProfileImage(_ sender: UIButton) {
        setActionSheet()
    }
    
    @IBAction func editButton(_ sender: UIButton) {
        let image = profileImageView.image
        let data: Data = (image?.pngData())!
        let param = EditProfileRequest(phoneNumber: userInfoManager.phoneNumber, address: userInfoManager.address, name: nickNameTextField.text!, file: data)
        editProfileDataManager.productModify(delegate: self, withRequest: param)
    }
    
    @IBAction func close(_ sender: UIButton) {
        dismiss(animated: true)
    }
}

extension EditProfileViewController: UIImagePickerControllerDelegate, UINavigationControllerDelegate {
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

extension EditProfileViewController {
    func didSuccessEditProfile() {
        print("수정 성공")
        dismiss(animated: true)
    }
}
