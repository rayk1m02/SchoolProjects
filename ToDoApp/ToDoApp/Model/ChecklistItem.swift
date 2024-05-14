//
//  ChecklistItem.swift
//  ToDoApp
//
//  Created by Raymond Kim on 3/20/24.
//

import Foundation

class ChecklistItem: NSObject, Codable {
    var text: String
    var checked: Bool
    
    init(text: String, checked: Bool) {
        self.text = text
        self.checked = checked
    }
    
    convenience init(text: String) {
        self.init(text: text, checked: false)
    }
    
    func toggleChecked() {
        self.checked = !self.checked
    }
}
