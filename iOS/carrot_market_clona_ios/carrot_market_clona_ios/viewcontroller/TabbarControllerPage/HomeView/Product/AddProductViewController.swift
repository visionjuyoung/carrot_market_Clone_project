//
//  AddProductViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/15.
//

import UIKit
import BSImagePicker
import Photos

class AddProductViewController: UIViewController {
    
    lazy var registerProductDataManager: RegisterProductDataManager = RegisterProductDataManager()

    var userInfoManager = UserInfo.shared
    
    @IBOutlet weak var cameraImage: UIView!
    
    @IBOutlet weak var negoButton: UIButton!
    @IBOutlet weak var imageView1: UIImageView!
    @IBOutlet weak var imageView2: UIImageView!
    @IBOutlet weak var imageView3: UIImageView!
    @IBOutlet weak var picksImageCountLabel: UILabel!
    @IBOutlet weak var categoryLabel: UILabel!
    @IBOutlet weak var titleTextField: UITextField!
    @IBOutlet weak var priceTextField: UITextField!
    @IBOutlet weak var contentTextView: UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setInit()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
    }
    
    func setInit() {
        cameraImage.layer.borderWidth = 1
        cameraImage.layer.borderColor = UIColor.systemGray4.cgColor
        cameraImage.layer.cornerRadius = 5
        negoButton.layer.borderWidth = 1
        negoButton.layer.borderColor = UIColor.systemGray4.cgColor
        negoButton.layer.cornerRadius = 7
    }
    
    func convertAssetToImages(selectedAssets: [PHAsset]) {
            
        let images: [UIImageView] = [ imageView1, imageView2, imageView3]
        
            if selectedAssets.count != 0 {
                picksImageCountLabel.text = "\(selectedAssets.count)/10"
                
                for i in 0..<selectedAssets.count {
                    
                    let imageManager = PHImageManager.default()
                    let option = PHImageRequestOptions()
                    option.isSynchronous = true
                    var thumbnail = UIImage()
                    
                    imageManager.requestImage(for: selectedAssets[i],
                                              targetSize: CGSize(width: 200, height: 200),
                                              contentMode: .aspectFill,
                                              options: option) { (result, info) in
                        thumbnail = result!
                    }
                    
                    let data = thumbnail.jpegData(compressionQuality: 0.7)
                    let newImage = UIImage(data: data!)
                    
                    images[i].image = newImage
                }
            }
            
        }
    
    @IBAction func imagePick(_ sender: UIButton) {
        let imagePicker = ImagePickerController()
        imagePicker.settings.selection.max = 10
        imagePicker.settings.fetch.assets.supportedMediaTypes = [.image]
               
        presentImagePicker(imagePicker, select: { (asset) in
                   // User selected an asset. Do something with it. Perhaps begin processing/upload?
               print("select")
           }, deselect: { (asset) in
                   // User deselected an asset. Cancel whatever you did when asset was selected.
               print("deselect")
           }, cancel: { (assets) in
                   // User canceled selection.
               print("cancel")
           }, finish: { (assets) in
                   // User finished selection assets.
               print("finish")
               self.convertAssetToImages(selectedAssets: imagePicker.selectedAssets)
            })
    }
    
    @IBAction func selectCategory(_ sender: UIButton) {
        guard let vc = storyboard?.instantiateViewController(withIdentifier: "SelectProductCategoryViewController") as? SelectProductCategoryViewController else { return }
        present(vc, animated: true)
    }
    
    @IBAction func doneButton(_ sender: UIBarButtonItem) {
        let images: [UIImage?] = [imageView1.image, imageView2.image,imageView3.image]
        var tempImages: [Data] = []
        for img in images {
            tempImages.append((img?.pngData())!)
        }
        let param = RegisterProductRequest(title: titleTextField.text!, content: contentTextView.text!, address: userInfoManager.address, price: Int(priceTextField.text!)!, phoneNumber: userInfoManager.phoneNumber, file: tempImages)
        registerProductDataManager.signUp(delegate: self, withRequest: param)
    }
    
    
    @IBAction func closeButton(_ sender: UIBarButtonItem) {
        guard let vc = storyboard?.instantiateViewController(withIdentifier: "TabbarController") else { return }
        present(vc, animated: true, completion: nil)
    }

}

extension AddProductViewController {
    func didSuccessRegistProduct() {
        guard let vc = storyboard?.instantiateViewController(withIdentifier: "HomeViewController") as? HomeViewController else { return }
        present(vc, animated: true)
    }
}
