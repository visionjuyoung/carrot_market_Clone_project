//
//  AddProductViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/15.
//

import UIKit

class AddProductViewController: UIViewController {

    @IBOutlet weak var cameraImage: UIView!
    
    @IBOutlet weak var negoButton: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()
        setInit()
    }
    
    func setInit() {
        cameraImage.layer.borderWidth = 1
        cameraImage.layer.borderColor = UIColor.systemGray4.cgColor
        cameraImage.layer.cornerRadius = 5
        negoButton.layer.borderWidth = 1
        negoButton.layer.borderColor = UIColor.systemGray4.cgColor
        negoButton.layer.cornerRadius = 7
    }
    
    @IBAction func closeButton(_ sender: UIBarButtonItem) {
        guard let vc = storyboard?.instantiateViewController(withIdentifier: "TabbarController") else { return }
        present(vc, animated: true, completion: nil)
    }
     
}
