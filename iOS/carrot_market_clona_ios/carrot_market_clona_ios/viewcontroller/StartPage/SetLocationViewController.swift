//
//  SetLocationViewController.swift
//  carrot_market_clona_ios
//
//  Created by 김주영 on 2022/03/08.
//

import UIKit
import CoreLocation

class SetLocationViewController: UIViewController, CLLocationManagerDelegate{
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var presentLocationSearchButton: UIButton!
    
    var locationMagager = CLLocationManager()
    var nearbyLocation: [String] = []
    var saveLocation: [String] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setInit()
    }

    func setInit() {
        locationMagager.delegate = self
        locationMagager.desiredAccuracy = kCLLocationAccuracyBest
        locationMagager.requestWhenInUseAuthorization()
        
        tableView.dataSource = self
        tableView.delegate = self
        tableView.register(UINib(nibName: "SetLocationViewTableViewCell", bundle: nil), forCellReuseIdentifier: "SetLocationViewTableViewCell")
        
        if CLLocationManager.locationServicesEnabled() {
            locationMagager.startUpdatingLocation()
            //print(locationMagager.location?.coordinate)
        }
        
        presentLocationSearchButton.layer.cornerRadius = 5
    }
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        guard let location = locations.first else {
            return
        }
        for i in (0...10) {
            var locate = CLLocation(latitude: location.coordinate.latitude, longitude: location.coordinate.longitude)
            
            if i != 0 {
                let num1 = location.coordinate.latitude + Double.random(in: (0.005 ... 0.02))
                let num2 = location.coordinate.longitude + Double.random(in: (0.005 ... 0.02))
                locate = CLLocation(latitude: num1, longitude: num2)
            }
            
            let geocoder = CLGeocoder()
            let local: Locale = Locale(identifier: "Ko-kr")

            geocoder.reverseGeocodeLocation(locate, preferredLocale: local) { (place, error) in
                        if let address: [CLPlacemark] = place {
                            guard let locality: String = address.last?.locality else {
                                return
                            }
                            
                            guard let sublocality: String = address.last?.subLocality else {
                                return
                            }
                            
                            guard let present: String = address.last?.name else {
                                return
                            }
                            
                            self.nearbyLocation.append("\(locality) \(sublocality) \(present)")
                            self.saveLocation.append(sublocality)
                        }
                }
        }
    }
    
    func locationManager(_ manager: CLLocationManager, didFailWithError error: Error) {
        print(error)
    }
    
    @IBAction func Pressback(_ sender: UIButton) {
        dismiss(animated: true, completion: nil)
    }
    
    @IBAction func searchPresentLocation(_ sender: UIButton) {
        self.tableView.reloadData()
    }
    
}

extension SetLocationViewController: UITableViewDelegate, UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return nearbyLocation.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "SetLocationViewTableViewCell", for: indexPath) as? SetLocationViewTableViewCell else {
            return UITableViewCell()
        }
        cell.locationLabel.text = nearbyLocation[indexPath.row]
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        guard let vc = storyboard?.instantiateViewController(withIdentifier: "PhoneAutentificationViewController") as? PhoneAutentificationViewController else { return }
        vc.tempAddress = saveLocation[indexPath.row]
        present(vc, animated: true, completion: nil)
    }
}
