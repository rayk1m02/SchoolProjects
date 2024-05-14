//
//  ChecklistTableViewCell.swift
//  ToDoApp
//
//  Created by Raymond Kim on 3/27/24.
//

import UIKit

class ChecklistTableViewCell: UITableViewCell {
    
    @IBOutlet weak var toDoTextLabel: UILabel!
    @IBOutlet weak var toDoCheck: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        // Configure the view for the selected state
    }
    
}
