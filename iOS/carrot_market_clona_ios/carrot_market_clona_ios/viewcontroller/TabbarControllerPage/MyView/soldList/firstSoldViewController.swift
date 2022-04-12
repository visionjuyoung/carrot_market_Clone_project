//
//  firstSoldViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/11.
//

import UIKit

class firstSoldViewController: UIViewController {
    
    lazy var sellProductDataManager: SellProductDataManager = SellProductDataManager()
    lazy var loadImageDataManager: LoadImageDataManager = LoadImageDataManager()
    lazy var productModifyDataManager: ProductModifyDataManager = ProductModifyDataManager()
    var userInfoManger = UserInfo.shared

    var tempSellList : [SellProductResult] = []
    
    @IBOutlet weak var noProductImageView: UIImageView!
    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setInit()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        sellProductDataManager.loadHeart(delegate: self, phoneNum: userInfoManger.phoneNumber)
    }
    
    func setInit() {
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(UINib(nibName: "SellProductTableViewCell", bundle: nil), forCellReuseIdentifier: "SellProductTableViewCell")
    }
}

extension firstSoldViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return tempSellList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "SellProductTableViewCell") as? SellProductTableViewCell else { return UITableViewCell()}
        cell.titleLabel.text = tempSellList[indexPath.row].title
        let url = loadImageDataManager.loadImage(filepath: tempSellList[indexPath.row].imagePath ?? "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAQlBMVEXz9Pbr7O+5vsq2u8j29/jv8PPf4efm6OzZ3OLj5eny8/X4+fq3vMnu7/LR09rIy9PJzNTBxM2+wcrU1tza3eOvs7/7UlVAAAAEC0lEQVR4nO3aC2+jOBiFYdt8YMDgC6T//6+ubdL0oqQrrXC7B513pGaa9MIzBhvIKKsvntLq6lGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiF9jocw/p6Tt71ethXNYuh9bYtPfX2oqlLT8m7ALrUexqbDvFj/81OSXbm65BaqxcO06W4ZI5MVAydR1fcstUL8gzA+u169mlIsIQz7gxufESwhdWowx3fD0K64gFN2ZUvoYRPmYXC4hnA6heQjzM/r9k0sI+0P4WPcqeb1/dgWhkliIdcop3cf0TryEUMluumTvg/bYaY8d9RrCsuB/2UVrSyVeRPjRB/C+o15HKNPx8RPQLOuFhDKUa4ivwEK8ynlpAZoluG9AUxfLSwgLMI9YPXv7NIJ3IfzV0/ej7zGAKeUX5+Chr4CPFX94Crw5p3VZSVrfqml/1mafAU1wPvX16nhKU8tt+MUz768HoXfdYvrjKj82HcU/EhpTZ9b+OA1vuQ2/InxarMRyjDZeLtrPNPPaP2ud6yq5NB7B3xjD55WX3V7XxJZboJrfLzXxNj5rmMsks+Rlv/VO2vied+xe3fP2UieZG/hxqJR/Oo/mjjm2P2bUppvQ+r2nVze7lZiuTDJlqm174vZ37x8Ooc6iMoW22/CH75DKt8dG8T1g/CjEj0L8ThbmFb6ea+teXP7rPNkpr3paJF9K5NcmNQ9TXR3KeYz0vei5bkO9/12+0f788/9D5wqnbXNJm7SPNnZJxz6FFEWS90aPfnP5j53KO4muc2XNT0NcnX4T53sl221LOrlTN0idLJTkhinYUeJoXXBz1F0Io4iPceu1NTfjfAhDHtwUghav7Wbm6HWYYham3TqXTh/E04XDXTi/3VQsBWu9T/uerF630UfvB9nXXo9KhejGyddmcQhCNSQjQW/Rj9ZItHEuGy8yhmD6zcchufx5cr3f3/Z9znuvjc5bPWofw57GLfjw/95LyyVDHkcRt+dLd8l7aWblvTR4H/vy9Cad93t+KR+R+WP+p3hb80G6lUmmPJUH8vSz1PNXi3r3U5frhtnmwdpveWj33evjtfzE/v5VMvpRpmkc9/XxjeffO21+fSifHtSXd0vfn375P6bOiSs+fhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIXz/AB9sJRWpTc1OAAAAAElFTkSuQmCC")
        cell.productImageView.load(url: url)
        cell.addressLabel.text = tempSellList[indexPath.row].address
        cell.priceLabel.text = "\(tempSellList[indexPath.row].price)원"
        cell.modifyButton.addTarget(self, action: #selector(pressModifyBtn(_button:)), for: .touchUpInside)
        cell.modifyButton.tag = tempSellList[indexPath.row].id
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 170
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 5
    }
}

extension firstSoldViewController {
    @objc func pressModifyBtn(_button: UIButton) {
        let actionSheetController = UIAlertController(title: nil, message: nil, preferredStyle: .actionSheet)
        let actionDefault = UIAlertAction(title: "게시글 수정", style: .default, handler: { action in
            guard let vc = self.storyboard?.instantiateViewController(withIdentifier: "ProductModifyViewController") as? ProductModifyViewController else {
                return
            }
            vc.tempID = _button.tag
            self.present(vc, animated: true)
        })
        let actionDelete = UIAlertAction(title: "삭제", style: .destructive, handler: { action in
            self.productModifyDataManager.deleteProduct(delegate: self, id: _button.tag)
        })
        let actionCancle = UIAlertAction(title: "닫기", style: .cancel, handler: nil)
        actionSheetController.addAction(actionDefault)
        actionSheetController.addAction(actionDelete)
        actionSheetController.addAction(actionCancle)
        self.present(actionSheetController, animated: true)

    }
}

extension firstSoldViewController {
    func didSucessLoadSellProductList(result: [SellProductResult]?) {
        tempSellList = result!
        tableView.reloadData()
        print(tempSellList)
    }
    
    func didSuccessDeleteProduct() {
        sellProductDataManager.loadHeart(delegate: self, phoneNum: userInfoManger.phoneNumber)
        tableView.reloadData()
    }
}
