//
//  ViewController.swift
//  ProjectOne
//
//  Created by Raymond Kim on 2/10/24.
//

import UIKit
import AVFoundation

class ViewController: UIViewController, AVAudioPlayerDelegate {

    @IBOutlet weak var computerCard: UIImageView!
    @IBOutlet weak var userCard: UIImageView!
    @IBOutlet weak var betLabel: UITextField!
    @IBOutlet weak var whoWins: UILabel!
    @IBOutlet weak var computerTotalLabel: UILabel!
    @IBOutlet weak var userTotalLabel: UILabel!
    @IBOutlet weak var potLabel: UILabel!
    var cardArrayCount = 0
    var cardArray: [UIImage] = []
    var computerTotal = 100
    var userTotal = 100
    var userBet = 0
    var computerBet = 0
    var pot = 0
    var player: AVAudioPlayer!
    var timer = Timer()
    var seconds = 0
    var totalSeconds = 10
    @IBOutlet weak var countdownBar: UIProgressView!
    @IBOutlet weak var countdownLabel: UITextField!
    @IBOutlet weak var switcher: UISwitch!
    @IBOutlet weak var coverLabel: UILabel!
    var switchOn = true
    override func viewDidLoad() {
        countdownBar.progress = 1
        super.viewDidLoad()
        computerCard.layer.masksToBounds = true
        computerCard.layer.borderWidth = 1.5
        computerCard.layer.borderColor = UIColor.black.cgColor
        computerCard.layer.cornerRadius = computerCard.bounds.width / 10
        userCard.layer.masksToBounds = true
        userCard.layer.borderWidth = 1.5
        userCard.layer.borderColor = UIColor.black.cgColor
        userCard.layer.cornerRadius = userCard.bounds.width / 10
        cardArray = [UIImage(imageLiteralResourceName: "2_of_clubs"),
                         UIImage(imageLiteralResourceName: "2_of_diamonds"),
                         UIImage(imageLiteralResourceName: "2_of_hearts"),
                         UIImage(imageLiteralResourceName: "2_of_spades"),
                         UIImage(imageLiteralResourceName: "3_of_clubs"),
                         UIImage(imageLiteralResourceName: "3_of_diamonds"),
                         UIImage(imageLiteralResourceName: "3_of_hearts"),
                         UIImage(imageLiteralResourceName: "3_of_spades"),
                         UIImage(imageLiteralResourceName: "4_of_clubs"),
                         UIImage(imageLiteralResourceName: "4_of_diamonds"),
                         UIImage(imageLiteralResourceName: "4_of_hearts"),
                         UIImage(imageLiteralResourceName: "4_of_spades"),
                         UIImage(imageLiteralResourceName: "5_of_clubs"),
                         UIImage(imageLiteralResourceName: "5_of_diamonds"),
                         UIImage(imageLiteralResourceName: "5_of_hearts"),
                         UIImage(imageLiteralResourceName: "5_of_spades"),
                         UIImage(imageLiteralResourceName: "6_of_clubs"),
                         UIImage(imageLiteralResourceName: "6_of_diamonds"),
                         UIImage(imageLiteralResourceName: "6_of_hearts"),
                         UIImage(imageLiteralResourceName: "6_of_spades"),
                         UIImage(imageLiteralResourceName: "7_of_clubs"),
                         UIImage(imageLiteralResourceName: "7_of_diamonds"),
                         UIImage(imageLiteralResourceName: "7_of_hearts"),
                         UIImage(imageLiteralResourceName: "7_of_spades"),
                         UIImage(imageLiteralResourceName: "8_of_clubs"),
                         UIImage(imageLiteralResourceName: "8_of_diamonds"),
                         UIImage(imageLiteralResourceName: "8_of_hearts"),
                         UIImage(imageLiteralResourceName: "8_of_spades"),
                         UIImage(imageLiteralResourceName: "9_of_clubs"),
                         UIImage(imageLiteralResourceName: "9_of_diamonds"),
                         UIImage(imageLiteralResourceName: "9_of_hearts"),
                         UIImage(imageLiteralResourceName: "9_of_spades"),
                         UIImage(imageLiteralResourceName: "10_of_clubs"),
                         UIImage(imageLiteralResourceName: "10_of_diamonds"),
                         UIImage(imageLiteralResourceName: "10_of_hearts"),
                         UIImage(imageLiteralResourceName: "10_of_spades"),
                         UIImage(imageLiteralResourceName: "jack_of_clubs"),
                         UIImage(imageLiteralResourceName: "jack_of_diamonds"),
                         UIImage(imageLiteralResourceName: "jack_of_hearts"),
                         UIImage(imageLiteralResourceName: "jack_of_spades"),
                         UIImage(imageLiteralResourceName: "queen_of_clubs"),
                         UIImage(imageLiteralResourceName: "queen_of_diamonds"),
                         UIImage(imageLiteralResourceName: "queen_of_hearts"),
                         UIImage(imageLiteralResourceName: "queen_of_spades"),
                         UIImage(imageLiteralResourceName: "king_of_clubs"),
                         UIImage(imageLiteralResourceName: "king_of_diamonds"),
                         UIImage(imageLiteralResourceName: "king_of_hearts"),
                         UIImage(imageLiteralResourceName: "king_of_spades"),
                         UIImage(imageLiteralResourceName: "ace_of_clubs"),
                         UIImage(imageLiteralResourceName: "ace_of_diamonds"),
                         UIImage(imageLiteralResourceName: "ace_of_hearts"),
                         UIImage(imageLiteralResourceName: "ace_of_spades"),
                         UIImage(imageLiteralResourceName: "black_joker")]
        cardArrayCount = cardArray.count // 53
    }
    
    @IBAction func Bet(_ sender: UIButton) {
        playSound(forRes: "click1", src: "wav")
        let computerNum = Int.random(in: 0..<cardArrayCount)
        let userNum = Int.random(in: 0..<cardArrayCount)
        computerCard.image = cardArray[computerNum]
        userCard.image = cardArray[userNum]
        if computerNum > userNum {
            computerTotal += pot
            whoWins.text = "Computer wins \(pot)!"
            pot = 0
        } else if computerNum < userNum {
            userTotal += pot
            whoWins.text = "User wins \(pot)!"
            pot = 0
        } else {
            if (userTotal == 0 && computerTotal == 0) {
                whoWins.text = "Tie!"
                return
            }
            whoWins.text = "Pot stays"
        }
        computerBet = 0
        userBet = 0
        computerTotalLabel.text = "Total: \(computerTotal)"
        userTotalLabel.text = "Total: \(userTotal)"
        if userTotal == 0 {
            whoWins.text = "User lost!"
        } else if computerTotal == 0 {
            whoWins.text = "Computer lost!"
        }
        potLabel.text = "Pot: \(pot)"
    }
    
    func getBetAmount() {
        if let amount = Int(betLabel.text!) {
            if amount > userTotal || amount > computerTotal {
                betLabel.text = "Not enough!"
            } else {
                userBet = amount
                computerBet = amount
                userTotal -= userBet
                computerTotal -= computerBet
                pot += computerBet + userBet
            }
        } else {
            betLabel.text = "Digits!"
        }
    }
    
    @IBAction func lockIn(_ sender: UIButton) {
        getBetAmount()
        userTotalLabel.text = "Total: \(userTotal)"
        computerTotalLabel.text = "Total: \(computerTotal)"
        potLabel.text = "Pot: \(userBet + computerBet)"
        guard let s = countdownLabel.text else {
            return
        }
        if let s = Int(s) {
            seconds = s - 1
            timer.invalidate()
            timer = Timer.scheduledTimer(timeInterval: 1.0, target: self, selector: #selector(updateTimer), userInfo: nil, repeats: true)
        }
    }
    
    @IBAction func Reset(_ sender: Any) {
        resetVoid()
    }
    
    func resetTimer() {
        timer.invalidate()
        countdownLabel.text = "10"
        if let p = player {
            p.stop()
        }
        countdownBar.progress = 1
    }
    
    func resetVoid() {
        userTotal = 100
        computerTotal = 100
        userBet = 0
        computerBet = 0
        pot = 0
        betLabel.text = ""
        whoWins.text = ""
        potLabel.text = "Pot: 0"
        userTotalLabel.text = "Total: 100"
        computerTotalLabel.text = "Total: 100"
        resetTimer()
    }
    
    func playSound(forRes: String, src: String) {
        let url = Bundle.main.url(forResource: forRes, withExtension: src)
        player = try! AVAudioPlayer(contentsOf: url!)
        player.delegate = self
        player.play()
    }
    
    @objc func updateTimer() {
        if seconds > -1 {
            countdownLabel.text = String(seconds)
            countdownBar.progress = Float(seconds) / Float(totalSeconds)
            seconds -= 1
        } else {
            timer.invalidate()
            playSound(forRes: "bell", src: "mp3")
        }
    }
    
    @IBAction func switchClicked(_ sender: UISwitch) {
        if switchOn {
            switchOn = false
            coverLabel.isHidden = false
        } else {
            switchOn = true
            coverLabel.isHidden = true
        }
    }
    
    func audioPlayerDidFinishPlaying(_ player: AVAudioPlayer, successfully flag: Bool) {
        resetTimer()
    }
    
}

