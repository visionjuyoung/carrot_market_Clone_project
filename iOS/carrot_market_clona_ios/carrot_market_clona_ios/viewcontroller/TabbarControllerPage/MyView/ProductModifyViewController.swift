//
//  ProductModifyViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/12.
//

import UIKit

class ProductModifyViewController: UIViewController {
    
    lazy var loadProductInfoDataManager: ProductDetailDataManager = ProductDetailDataManager()
    lazy var loadImageDataManager: LoadImageDataManager = LoadImageDataManager()
    lazy var modifyProductDataManager: ProductModifyDataManager = ProductModifyDataManager()
    
    var userInfoManager = UserInfo.shared
    var tempImages: [UIImage] = []
    
    @IBOutlet weak var productImage1: UIImageView!
    @IBOutlet weak var productImage2: UIImageView!
    @IBOutlet weak var productImage3: UIImageView!
    @IBOutlet weak var titleLabel: UITextField!
    @IBOutlet weak var priceLabel: UITextField!
    @IBOutlet weak var contentLabel: UITextView!
    
    var tempID: Int = 0
    let defaultUrl: String = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAQlBMVEXz9Pbr7O+5vsq2u8j29/jv8PPf4efm6OzZ3OLj5eny8/X4+fq3vMnu7/LR09rIy9PJzNTBxM2+wcrU1tza3eOvs7/7UlVAAAAEC0lEQVR4nO3aC2+jOBiFYdt8YMDgC6T//6+ubdL0oqQrrXC7B513pGaa9MIzBhvIKKsvntLq6lGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiF9jocw/p6Tt71ethXNYuh9bYtPfX2oqlLT8m7ALrUexqbDvFj/81OSXbm65BaqxcO06W4ZI5MVAydR1fcstUL8gzA+u169mlIsIQz7gxufESwhdWowx3fD0K64gFN2ZUvoYRPmYXC4hnA6heQjzM/r9k0sI+0P4WPcqeb1/dgWhkliIdcop3cf0TryEUMluumTvg/bYaY8d9RrCsuB/2UVrSyVeRPjRB/C+o15HKNPx8RPQLOuFhDKUa4ivwEK8ynlpAZoluG9AUxfLSwgLMI9YPXv7NIJ3IfzV0/ej7zGAKeUX5+Chr4CPFX94Crw5p3VZSVrfqml/1mafAU1wPvX16nhKU8tt+MUz768HoXfdYvrjKj82HcU/EhpTZ9b+OA1vuQ2/InxarMRyjDZeLtrPNPPaP2ud6yq5NB7B3xjD55WX3V7XxJZboJrfLzXxNj5rmMsks+Rlv/VO2vied+xe3fP2UieZG/hxqJR/Oo/mjjm2P2bUppvQ+r2nVze7lZiuTDJlqm174vZ37x8Ooc6iMoW22/CH75DKt8dG8T1g/CjEj0L8ThbmFb6ea+teXP7rPNkpr3paJF9K5NcmNQ9TXR3KeYz0vei5bkO9/12+0f788/9D5wqnbXNJm7SPNnZJxz6FFEWS90aPfnP5j53KO4muc2XNT0NcnX4T53sl221LOrlTN0idLJTkhinYUeJoXXBz1F0Io4iPceu1NTfjfAhDHtwUghav7Wbm6HWYYham3TqXTh/E04XDXTi/3VQsBWu9T/uerF630UfvB9nXXo9KhejGyddmcQhCNSQjQW/Rj9ZItHEuGy8yhmD6zcchufx5cr3f3/Z9znuvjc5bPWofw57GLfjw/95LyyVDHkcRt+dLd8l7aWblvTR4H/vy9Cad93t+KR+R+WP+p3hb80G6lUmmPJUH8vSz1PNXi3r3U5frhtnmwdpveWj33evjtfzE/v5VMvpRpmkc9/XxjeffO21+fSifHtSXd0vfn375P6bOiSs+fhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIXz/AB9sJRWpTc1OAAAAAElFTkSuQmCC"

    override func viewDidLoad() {
        super.viewDidLoad()
        print(tempID)
        loadProductInfoDataManager.loadProductInfo(delegate: self, id: tempID, phoneNum: userInfoManager.phoneNumber)
    }
    
    @IBAction func editButton(_ sender: UIBarButtonItem) {
        let images: [UIImageView] = [productImage1, productImage2, productImage3]
        var tempImages: [Data] = []
        for i in images{
            if i.image != nil {
                tempImages.append((i.image!.pngData())!)
            }
        }
        let param: ProductModifyRequest = ProductModifyRequest(id: tempID, title: titleLabel.text!, content: contentLabel.text!, price: Int(priceLabel.text!)!, files: tempImages)
        modifyProductDataManager.productModify(delegate: self, withRequest: param)
    }
    
    @IBAction func close(_ sender: UIBarButtonItem) {
        dismiss(animated: true)
    }
    
}

extension ProductModifyViewController {
    func didSuccessLoadProductInfo(response: ProductDetailResponse) {
        print(response)
        if response.result?.imagePathList != nil {
            if response.result?.imagePathList?.count == 1 {
                let url = loadImageDataManager.loadImage(filepath: response.result?.imagePathList![0] ?? defaultUrl)
                productImage1.load(url: url)
            } else if response.result?.imagePathList?.count == 2 {
                let url = loadImageDataManager.loadImage(filepath: response.result?.imagePathList![0] ?? defaultUrl)
                productImage2.load(url: url)
                let url1 = loadImageDataManager.loadImage(filepath: response.result?.imagePathList![1] ?? defaultUrl)
                productImage3.load(url: url1)
            } else if response.result?.imagePathList?.count == 3 {
                let url = loadImageDataManager.loadImage(filepath: response.result?.imagePathList![0] ?? defaultUrl)
                productImage1.load(url: url)
                let url1 = loadImageDataManager.loadImage(filepath: response.result?.imagePathList![1] ?? defaultUrl)
                productImage2.load(url: url1)
                let url2 = loadImageDataManager.loadImage(filepath: response.result?.imagePathList![1] ?? defaultUrl)
                productImage3.load(url: url2)
            }
        }
        titleLabel.text = response.result?.title!
        contentLabel.text = response.result?.content
        priceLabel.text = "\(response.result!.price!)"
        contentLabel.isEditable = true
    }
    
    func didSuccessModifyProduct() {
        dismiss(animated: true)
    }
}
