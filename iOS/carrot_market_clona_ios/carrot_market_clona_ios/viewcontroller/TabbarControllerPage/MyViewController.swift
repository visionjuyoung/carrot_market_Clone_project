import UIKit

class MyViewController: UIViewController {
    
    lazy var loadImageDataManager: LoadImageDataManager = LoadImageDataManager()
    
    @IBOutlet weak var tableView: UITableView!
    
    var userInfoManager = UserInfo.shared
    
    let cellNamesList: MyCarrotCellList = MyCarrotCellList()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setInit()
        print(userInfoManager.jwt)
        print(userInfoManager.imagePath)
    }

    func setInit(){
        tableView.delegate = self
        tableView.dataSource = self
        tableView.register(UINib(nibName: "MyViewFirstSectionTableViewCell", bundle: nil), forCellReuseIdentifier: "MyViewFirstSectionTableViewCell")
        tableView.register(UINib(nibName: "MyViewSecondSectionTableViewCell", bundle: nil), forCellReuseIdentifier: "MyViewSecondSectionTableViewCell")
    }
}

extension MyViewController: UITableViewDelegate, UITableViewDataSource {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 6
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if section == 0 {
            return 1
        } else if section == 1 {
            return 4
        } else if section == 2 {
            return 4
        } else if section == 3 {
            return 2
        } else if section == 4 {
            return 3
        } else if section == 5 {
            return 5
        } else {
            return 0
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        switch indexPath.section {
        case 0:
            guard let cell = tableView.dequeueReusableCell(withIdentifier: "MyViewFirstSectionTableViewCell", for: indexPath) as? MyViewFirstSectionTableViewCell else { return UITableViewCell() }
            loadImageDataManager.loadImage(delegate: self, filepath: userInfoManager.imagePath)
            cell.setData(name: userInfoManager.name, address: userInfoManager.address, code: userInfoManager.userCode , image: userInfoManager.imagePath)
            return cell
            
        case 1...5:
            guard let cell = tableView.dequeueReusableCell(withIdentifier: "MyViewSecondSectionTableViewCell", for: indexPath) as? MyViewSecondSectionTableViewCell else {
                return UITableViewCell()
            }
            if indexPath.section == 1 {
                cell.cellMenuTitleLabel.text = cellNamesList.firstSection[indexPath.row]
            } else if indexPath.section == 2 {
                cell.cellMenuTitleLabel.text = cellNamesList.secondSection[indexPath.row]
            } else if indexPath.section == 3 {
                cell.cellMenuTitleLabel.text = cellNamesList.thirdSection[indexPath.row]
            } else if indexPath.section == 4 {
                cell.cellMenuTitleLabel.text = cellNamesList.firthSection[indexPath.row]
            } else if indexPath.section == 5 {
                cell.cellMenuTitleLabel.text = cellNamesList.fifthSection[indexPath.row]
            }
            return cell
        default:
            return UITableViewCell()
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if indexPath.section == 0 {
            return 300
        } else {
            return 50
        }
    }
}

extension MyViewController {
    func didSuccessLoadImage() {
        print("success")
    }
}