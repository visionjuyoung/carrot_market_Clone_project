//
//  SelectHomeDetailViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/15.
//

import UIKit

class SelectHomeDetailViewController: UIViewController {
    
    @IBOutlet weak var firstView: UIView!
    @IBOutlet weak var secondView: UIView!
    @IBOutlet weak var closeButton: UIButton!
    @IBOutlet weak var addProductButton: UIButton!
    

    override func viewDidLoad() {
        super.viewDidLoad()
        setInit()
    }
    
    func setInit() {
        firstView.layer.cornerRadius = 15
        secondView.layer.cornerRadius = 15
        closeButton.layer.cornerRadius = 30
    }
    
    @IBAction func addProduct(_ sender: UIButton) {
        guard let vc = storyboard?.instantiateViewController(withIdentifier: "AddProductViewController") as? AddProductViewController else { return }
        present(vc, animated: true, completion: nil)
    }
    
    @IBAction func closeButton(_ sender: UIButton) {
        dismiss(animated: false, completion: nil)
    }
    
}
