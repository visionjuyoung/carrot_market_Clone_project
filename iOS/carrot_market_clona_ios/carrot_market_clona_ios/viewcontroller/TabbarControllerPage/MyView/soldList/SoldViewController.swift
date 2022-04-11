//
//  SoldViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/04/11.
//

import UIKit
import Tabman
import Pageboy

class SoldViewController: TabmanViewController {
    
    private var viewControllers: Array<UIViewController> = []

    @IBOutlet weak var tabmanView: UIView!
    override func viewDidLoad() {
        super.viewDidLoad()
        setViewController()
        setTabManBar()

    }
    
    
    @IBAction func close(_ sender: UIBarButtonItem) {
        dismiss(animated: true)
    }
}

extension SoldViewController {
    func setViewController() {
        let vc1 = storyboard?.instantiateViewController(withIdentifier: "firstSoldViewController") as! firstSoldViewController
        let vc2 = storyboard?.instantiateViewController(withIdentifier: "secondSoldViewController") as! secondSoldViewController
        let vc3 = storyboard?.instantiateViewController(withIdentifier: "thirdSoldViewController") as! thirdSoldViewController
        
        viewControllers.append(vc1)
        viewControllers.append(vc2)
        viewControllers.append(vc3)
    }
    
    func setTabManBar() {
        self.dataSource = self
        
        let bar = TMBar.ButtonBar()
        bar.layout.transitionStyle = .snap
        bar.backgroundView.style = .clear
        bar.backgroundColor = .white
        bar.layout.alignment = .centerDistributed
        bar.layout.contentMode = .fit
        bar.buttons.customize{(button) in
            button.tintColor = .lightGray
            button.selectedTintColor = .black
            button.font = UIFont.systemFont(ofSize: 14)
        }
        bar.indicator.tintColor = .black
        addBar(bar, dataSource: self, at: .custom(view: tabmanView, layout: nil))
    }
}

extension SoldViewController: PageboyViewControllerDataSource, TMBarDataSource {
    func numberOfViewControllers(in pageboyViewController: PageboyViewController) -> Int {
        return viewControllers.count
    }
    
    func viewController(for pageboyViewController: PageboyViewController, at index: PageboyViewController.PageIndex) -> UIViewController? {
        switch index {
        case 0:
            return viewControllers[0]
        case 1:
            return viewControllers[1]
        case 2:
            return viewControllers[2]
        default:
            return UIViewController()
        }
    }
    
    func defaultPage(for pageboyViewController: PageboyViewController) -> PageboyViewController.Page? {
        return nil
    }
    
    func barItem(for bar: TMBar, at index: Int) -> TMBarItemable {
        switch index {
        case 0:
            return TMBarItem(title: "판매중")
        case 1:
            return TMBarItem(title: "거래완료")
        case 2:
            return TMBarItem(title: "숨김")
        default:
            return TMBarItem(title: "")
        }
    }
}
