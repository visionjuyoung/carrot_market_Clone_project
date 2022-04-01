//
//  HomeViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/03.
//

import UIKit

class HomeViewController: UIViewController {
    
    lazy var showListDataManager: ShowListDataManager = ShowListDataManager()
    lazy var loadImageDataManager: LoadImageDataManager = LoadImageDataManager()
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var addProductButton: UIButton!
    
    var userInfoManager = UserInfo.shared
    
    var tempListResult: [ShowListResult] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        print(userInfoManager.jwt)
        setInit()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        showListDataManager.ShowList(delegate: self, address: userInfoManager.address)
    }
    
    func setInit() {
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(UINib(nibName: "HomeViewTableViewCell", bundle: nil), forCellReuseIdentifier: "HomeViewTableViewCell")
        addProductButton.layer.cornerRadius = 30
    }
    
    @IBAction func addProductMenu(_ sender: UIButton) {
        guard let vc = storyboard?.instantiateViewController(withIdentifier: "SelectHomeDetailViewController") as? SelectHomeDetailViewController else { return }
        present(vc, animated: false, completion: nil)
    }
    
}

extension HomeViewController: UITableViewDelegate, UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return tempListResult.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "HomeViewTableViewCell", for: indexPath) as? HomeViewTableViewCell else {
            return UITableViewCell()
        }
        cell.price.text = "\(tempListResult[indexPath.row].price ?? 0)원"
        cell.productName.text = "\(tempListResult[indexPath.row].title ?? "" )"
        cell.addressWithTime.text = "\(tempListResult[indexPath.row].address ?? "" )"
        
        let url = loadImageDataManager.loadImage(filepath: tempListResult[indexPath.row].imagePath ?? "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAOEAAADhCAMAAAAJbSJIAAAAQlBMVEXz9Pbr7O+5vsq2u8j29/jv8PPf4efm6OzZ3OLj5eny8/X4+fq3vMnu7/LR09rIy9PJzNTBxM2+wcrU1tza3eOvs7/7UlVAAAAEC0lEQVR4nO3aC2+jOBiFYdt8YMDgC6T//6+ubdL0oqQrrXC7B513pGaa9MIzBhvIKKsvntLq6lGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiB+F+FGIH4X4UYgfhfhRiF9jocw/p6Tt71ethXNYuh9bYtPfX2oqlLT8m7ALrUexqbDvFj/81OSXbm65BaqxcO06W4ZI5MVAydR1fcstUL8gzA+u169mlIsIQz7gxufESwhdWowx3fD0K64gFN2ZUvoYRPmYXC4hnA6heQjzM/r9k0sI+0P4WPcqeb1/dgWhkliIdcop3cf0TryEUMluumTvg/bYaY8d9RrCsuB/2UVrSyVeRPjRB/C+o15HKNPx8RPQLOuFhDKUa4ivwEK8ynlpAZoluG9AUxfLSwgLMI9YPXv7NIJ3IfzV0/ej7zGAKeUX5+Chr4CPFX94Crw5p3VZSVrfqml/1mafAU1wPvX16nhKU8tt+MUz768HoXfdYvrjKj82HcU/EhpTZ9b+OA1vuQ2/InxarMRyjDZeLtrPNPPaP2ud6yq5NB7B3xjD55WX3V7XxJZboJrfLzXxNj5rmMsks+Rlv/VO2vied+xe3fP2UieZG/hxqJR/Oo/mjjm2P2bUppvQ+r2nVze7lZiuTDJlqm174vZ37x8Ooc6iMoW22/CH75DKt8dG8T1g/CjEj0L8ThbmFb6ea+teXP7rPNkpr3paJF9K5NcmNQ9TXR3KeYz0vei5bkO9/12+0f788/9D5wqnbXNJm7SPNnZJxz6FFEWS90aPfnP5j53KO4muc2XNT0NcnX4T53sl221LOrlTN0idLJTkhinYUeJoXXBz1F0Io4iPceu1NTfjfAhDHtwUghav7Wbm6HWYYham3TqXTh/E04XDXTi/3VQsBWu9T/uerF630UfvB9nXXo9KhejGyddmcQhCNSQjQW/Rj9ZItHEuGy8yhmD6zcchufx5cr3f3/Z9znuvjc5bPWofw57GLfjw/95LyyVDHkcRt+dLd8l7aWblvTR4H/vy9Cad93t+KR+R+WP+p3hb80G6lUmmPJUH8vSz1PNXi3r3U5frhtnmwdpveWj33evjtfzE/v5VMvpRpmkc9/XxjeffO21+fSifHtSXd0vfn375P6bOiSs+fhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIX4U4kchfhTiRyF+FOJHIXz/AB9sJRWpTc1OAAAAAElFTkSuQmCC")
        cell.productImageView.load(url: url)
        
        
        if tempListResult[indexPath.row].likes == 0 {
            cell.heartImage.isHidden = true
            cell.heartCount.isHidden = true
        } else {
            cell.heartCount.text = "\(tempListResult[indexPath.row].likes ?? 0 )"
        }
        
        if tempListResult[indexPath.row].chats == 0 {
            cell.messageCount.isHidden = true
            cell.messageImage.isHidden = true
        } else {
            cell.messageCount.text = "\(tempListResult[indexPath.row].chats ?? 0 )"
        }
        return cell
    }
}

extension HomeViewController {
    func didSuccessShowList(showListResult: ShowListResponse) {
        tempListResult = showListResult.result
        for i in tempListResult {
            print(i)
        }
        tableView.reloadData()
    }
}
