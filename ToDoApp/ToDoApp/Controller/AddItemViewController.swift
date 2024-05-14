//
//  AddItemViewController.swift
//  ToDoApp
//
//  Created by Raymond Kim on 3/22/24.
//

import UIKit

protocol AddItemDelegate {
    func addItemDidCancel()
    func addItemDidDone(item: ChecklistItem, category: ToDoList.Category)
    func addItemDidEdit(item: ChecklistItem, category: ToDoList.Category)
}

class AddItemViewController: UITableViewController, UITextFieldDelegate {
    
    @IBOutlet weak var itemTF: UITextField!
    weak var itemToEdit: ChecklistItem? // item we get from ViewController
    var categoryToEdit: ToDoList.Category? // category of item we got
    var newCategory = ToDoList.Category.daily
    var delegate: AddItemDelegate?
    var toDoList = ToDoList()
    @IBOutlet weak var completeBtn: UIButton!
    
    override func viewDidLoad() {
        itemTF.delegate = self
        super.viewDidLoad()
        navigationItem.largeTitleDisplayMode = .never
        // Uncomment the following line to preserve selection between presentations
         self.clearsSelectionOnViewWillAppear = false
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
        if let item = itemToEdit {
            self.title = "Edit Item"
            itemTF.text = item.text
            newCategory = categoryToEdit!
            if item.checked { completeBtn.setTitle("Uncomplete", for: .normal) }
            else { completeBtn.setTitle("Complete", for: .normal) }
        } else {
            completeBtn.isHidden = true
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        removeCheckmark(remove: false)
    }
    
    @IBAction func completeBtnPressed(_ sender: UIButton) {
        if completeBtn.title(for: .normal) == "Complete" {
            itemToEdit!.checked = true
        } else { // completeBtn.title(for: .normal) == "Uncomplete"
            itemToEdit!.checked = false
        }
        delegate?.addItemDidEdit(item: itemToEdit!, category: newCategory)
        navigationController?.popViewController(animated: true)
    }
    
    private func removeCheckmark(remove: Bool) {
        guard let category = categoryToEdit else { return }
        let rowIdx = category.rawValue + 2
        let indexPath = IndexPath(row: rowIdx, section: 0)
        if let cell = self.tableView.cellForRow(at: indexPath) {
            if remove { cell.imageView?.image = UIImage(systemName: "circle") }
            else {
                cell.imageView?.image = UIImage(systemName: "checkmark.circle")
            }
        }
    }
    
    @IBAction func cancel(_ sender: UIBarButtonItem) {
        delegate?.addItemDidCancel()
    }
    
    
    /*
     attempted to move the item into its new category before calling delegate function.
     was not sure how to retrive the src idx and dest idx for the move() func so tried to do it manually.
     commented out code is that attempt.
     */
    @IBAction func done(_ sender: UIBarButtonItem) {
        if let item = itemToEdit, let text = itemTF.text {
            item.text = text
//            var oldList = toDoList.getTodoList(category: categoryToEdit!)
//            var newList = toDoList.getTodoList(category: newCategory)
//            let idx = oldList.firstIndex(of: item)
//            oldList.remove(at: idx!)
//            newList.append(item)
            //for item in items { if let idx = toDos.firstIndex(of: item) { toDos.remove(at: idx) } }
            delegate?.addItemDidEdit(item: item, category: newCategory)
        } else {
            if itemTF.text != "" { // so it doesn't add a blank line
                if let itemText = itemTF.text {
                    let item = ChecklistItem(text: itemText)
                    delegate?.addItemDidDone(item: item, category: newCategory)
                }
            }
        }
        navigationController?.popViewController(animated: true)
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        itemTF.endEditing(true)
        return true
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        removeCheckmark(remove: true)
        if let cell = tableView.cellForRow(at: indexPath) {
            switch cell.tag {
            case 100: newCategory = ToDoList.Category.daily
            case 101: newCategory = ToDoList.Category.weekly
            case 102: newCategory = ToDoList.Category.monthly
            case 103: newCategory = ToDoList.Category.yearly
            default: newCategory = ToDoList.Category.yearly
            }
        }
    }
    
    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return ToDoList.Category.allCases.count + 2
    }

}
