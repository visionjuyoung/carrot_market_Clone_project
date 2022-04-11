//
//  ProductDetailViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/29.
//

import UIKit

class ProductDetailViewController: UIViewController {
    
    lazy var loadImageDataManager = LoadImageDataManager()
    lazy var productDetailDataManager = ProductDetailDataManager()
    lazy var heartDataManager = HeartDataManager()
    
    var userInfoManager = UserInfo.shared

    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var orderButton: UIButton!
    @IBOutlet weak var priceLabel: UILabel!
    @IBOutlet weak var heartButton: UIButton!
    
    
    var tempProductResult: ShowListResult = ShowListResult()
    var tempDetailResult: ProductDetailResult = ProductDetailResult()
    var tempHeartList: [LoadHeartResult] = []
    var heartIdList: [Int] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        priceLabel.text = "\(tempProductResult.price!)원"
        productDetailDataManager.loadProductInfo(delegate: self, id: tempProductResult.id!, phoneNum: userInfoManager.phoneNumber)
        heartDataManager.loadHeart(delegate: self, phoneNum: userInfoManager.phoneNumber)
        setInit()
    }
    
    func setInit() {
        orderButton.layer.cornerRadius = 5
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(UINib(nibName: "firstProductDetailTableViewCell", bundle: nil), forCellReuseIdentifier: "firstProductDetailTableViewCell")
        tableView.register(UINib(nibName: "secondProductDetailTableViewCell", bundle: nil), forCellReuseIdentifier: "secondProductDetailTableViewCell")
        tableView.register(UINib(nibName: "thirdProductDetailTableViewCell", bundle: nil), forCellReuseIdentifier: "thirdProductDetailTableViewCell")
        tableView.register(UINib(nibName: "firthProductDetailTableViewCell", bundle: nil), forCellReuseIdentifier: "firthProductDetailTableViewCell")
        tableView.register(UINib(nibName: "fifthProductDetailTableViewCell", bundle: nil), forCellReuseIdentifier: "fifthProductDetailTableViewCell")
    }
    
    @IBAction func pressHeart(_ sender: UIButton) {
        
        if heartButton.tintColor == UIColor.red {
            heartButton.tintColor = UIColor.systemGray
            let param = DeleteHeartRequest(phoneNumber: userInfoManager.phoneNumber, productId: tempProductResult.id!)
            heartDataManager.deleteHeart(delegate: self, parameter: param)
        } else {
            heartButton.tintColor = UIColor.red
            let param = AddHeartRequest(phoneNumber: userInfoManager.phoneNumber, productId: tempProductResult.id!)
            heartDataManager.addHeart(delegate: self, parameter: param)
        }
    }
    
    @IBAction func close(_ sender: UIBarButtonItem) {
        dismiss(animated: true)
    }
    
}

extension ProductDetailViewController: UITableViewDelegate, UITableViewDataSource {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 5
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        switch indexPath.section {
        case 0:
            guard let cell = tableView.dequeueReusableCell(withIdentifier: "firstProductDetailTableViewCell") as? firstProductDetailTableViewCell else { return UITableViewCell() }
            let url = loadImageDataManager.loadImage(filepath: tempProductResult.imagePath ?? "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAQlBMVEXz9Pbr7O+5vsq2u8j29/jv8PPf4efm6OzZ3OLj5eny8/X4+fq3vMnu7/LR09rIy9PJzNTBxM2+wcrU1tza3eOvs7/7UlVAAAAEC0lEQVR4nO3aC2+jOBiFYdt8YMDgC6T//6+ubdL0oqQrrXC7B513pGaa9MIzBhvIKKsvntLq6lGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiF9jocw/p6Tt71ethXNYuh9bYtPfX2oqlLT8m7ALrUexqbDvFj/81OSXbm65BaqxcO06W4ZI5MVAydR1fcstUL8gzA+u169mlIsIQz7gxufESwhdWowx3fD0K64gFN2ZUvoYRPmYXC4hnA6heQjzM/r9k0sI+0P4WPcqeb1/dgWhkliIdcop3cf0TryEUMluumTvg/bYaY8d9RrCsuB/2UVrSyVeRPjRB/C+o15HKNPx8RPQLOuFhDKUa4ivwEK8ynlpAZoluG9AUxfLSwgLMI9YPXv7NIJ3IfzV0/ej7zGAKeUX5+Chr4CPFX94Crw5p3VZSVrfqml/1mafAU1wPvX16nhKU8tt+MUz768HoXfdYvrjKj82HcU/EhpTZ9b+OA1vuQ2/InxarMRyjDZeLtrPNPPaP2ud6yq5NB7B3xjD55WX3V7XxJZboJrfLzXxNj5rmMsks+Rlv/VO2vied+xe3fP2UieZG/hxqJR/Oo/mjjm2P2bUppvQ+r2nVze7lZiuTDJlqm174vZ37x8Ooc6iMoW22/CH75DKt8dG8T1g/CjEj0L8ThbmFb6ea+teXP7rPNkpr3paJF9K5NcmNQ9TXR3KeYz0vei5bkO9/12+0f788/9D5wqnbXNJm7SPNnZJxz6FFEWS90aPfnP5j53KO4muc2XNT0NcnX4T53sl221LOrlTN0idLJTkhinYUeJoXXBz1F0Io4iPceu1NTfjfAhDHtwUghav7Wbm6HWYYham3TqXTh/E04XDXTi/3VQsBWu9T/uerF630UfvB9nXXo9KhejGyddmcQhCNSQjQW/Rj9ZItHEuGy8yhmD6zcchufx5cr3f3/Z9znuvjc5bPWofw57GLfjw/95LyyVDHkcRt+dLd8l7aWblvTR4H/vy9Cad93t+KR+R+WP+p3hb80G6lUmmPJUH8vSz1PNXi3r3U5frhtnmwdpveWj33evjtfzE/v5VMvpRpmkc9/XxjeffO21+fSifHtSXd0vfn375P6bOiSs+fhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIXz/AB9sJRWpTc1OAAAAAElFTkSuQmCC")
            cell.prodcutImageView.load(url: url)
            return cell
        case 1:
            guard let cell = tableView.dequeueReusableCell(withIdentifier: "secondProductDetailTableViewCell") as? secondProductDetailTableViewCell else { return UITableViewCell() }
            let url = loadImageDataManager.loadImage(filepath: userInfoManager.imagePath ?? "http://kaihuastudio.com/common/img/default_profile.png")
            cell.profileImageView.load(url: url)
            cell.userNameLabel.text = userInfoManager.name
            cell.locationLabel.text = userInfoManager.address
            return cell
        case 2:
            guard let cell = tableView.dequeueReusableCell(withIdentifier: "thirdProductDetailTableViewCell") as? thirdProductDetailTableViewCell else { return UITableViewCell() }
            cell.titleLabel.text = tempDetailResult.title ?? ""
            cell.contentTextView.text = tempDetailResult.content ?? ""
            return cell
        case 3:
            guard let cell = tableView.dequeueReusableCell(withIdentifier: "firthProductDetailTableViewCell") as? firthProductDetailTableViewCell else { return UITableViewCell() }
            return cell
        case 4:
            guard let cell = tableView.dequeueReusableCell(withIdentifier: "fifthProductDetailTableViewCell") as? fifthProductDetailTableViewCell else { return UITableViewCell() }
            return cell
        default:
            return UITableViewCell()
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if indexPath.section == 0 {
            return 400
        } else if indexPath.section == 1 {
            return 100
        } else if indexPath.section == 2 {
            return 300
        } else if indexPath.section == 3 {
            return 70
        } else {
            return 300
        }
    }
}

extension ProductDetailViewController {
    func didSuccessLoadProductInfo(result: ProductDetailResult?) {
        tempDetailResult = result!
        print(tempDetailResult)
        tableView.reloadData()
    }
    
    func didSucessLoadHeart(reseponse: [LoadHeartResult]) {
        tempHeartList = reseponse
        for i in tempHeartList {
            heartIdList.append(i.id)
        }
        
        for i in heartIdList {
            if i == tempProductResult.id! {
                heartButton.tintColor = UIColor.red
            }
        }
    }
    
    func didSuccessAddHeart(response: AddHeartResponse) {
        print("찜추가 성공")
    }
}
