//
//  StartViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/10.
//

import UIKit

class StartViewController: UIViewController {

    @IBOutlet weak var startCarrot: UIButton!
    @IBOutlet weak var logIn: UIButton!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        
    }
    
    func setInit() {
        startCarrot.layer.cornerRadius = 5
    }
    
    @IBAction func pressStartCarrot(_ sender: UIButton) {
        guard let vc = storyboard?.instantiateViewController(withIdentifier: "SetLocationViewController") else {
            return
        }
        present(vc, animated: true, completion: nil)
    }
    
    @IBAction func pressLogIn(_ sender: UIButton) {
        guard let vc = storyboard?.instantiateViewController(withIdentifier: "PhoneAutentificationViewController") else{
            return
        }
        present(vc, animated: true, completion: nil)
    }
}
