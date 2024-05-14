//
//  ViewController.swift
//  ToDoApp
//
//  Created by Raymond Kim on 3/20/24.
//

import UIKit

class ViewController: UITableViewController, AddItemDelegate {
    
    var toDoList = ToDoList()
    @IBOutlet weak var datePicker: UIDatePicker!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationController?.navigationBar.prefersLargeTitles = true
        navigationItem.leftBarButtonItem = editButtonItem
        tableView.allowsMultipleSelectionDuringEditing = true
//        toDoList = loadData()
        tableView.reloadData()
        NotificationCenter.default.addObserver(self, selector: #selector(saveData), name: UIApplication.willResignActiveNotification, object: nil)
    }
    
    func loadData() -> ToDoList {
        if let listData = UserDefaults.standard.data(forKey: "todos") {
            do {
                let lists = try JSONDecoder().decode(ToDoList.self, from: listData)
                return lists
            } catch {
                print(error.localizedDescription)
            }
        }
        return ToDoList()
    }
    
    @objc func saveData() {
        do {
            let listData = try JSONEncoder().encode(toDoList)
            UserDefaults.standard.set(listData, forKey: "todos")
            print("saved")
        } catch {
            print(error.localizedDescription)
        }
    }
    
    func addItemDidCancel() {
        navigationController?.popViewController(animated: true)
    }
    
    func addItemDidEdit(item: ChecklistItem, category: ToDoList.Category) {
        for category in ToDoList.Category.allCases {
            let items = toDoList.getTodoList(category: category)
            if let rowIdx = items.firstIndex(of: item) {
                let indexPath = IndexPath(row: rowIdx, section: category.rawValue)
                if let cell = tableView.cellForRow(at: indexPath) {
                    setCheckmark(cell: cell, item: item)
                    setLabel(cell: cell, item: item)
                }
            }
        }
    }
    
    func addItemDidDone(item: ChecklistItem, category: ToDoList.Category) {
        let rowIdx = toDoList.getTodoList(category: category).count // get the list first
        toDoList.addToDo(item: item, category: category)
        let indexPath = IndexPath(row: rowIdx, section: category.rawValue)
        let indexPaths = [indexPath]
        tableView.insertRows(at: indexPaths, with: .automatic)
    }
    
    override func setEditing(_ editing: Bool, animated: Bool) {
        super.setEditing(editing, animated: true)
        tableView.setEditing(tableView.isEditing, animated: true)
    }
    
    override func shouldPerformSegue(withIdentifier identifier: String, sender: Any?) -> Bool {
        return !tableView.isEditing
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "toAddItemVC" {
            let destinationVC = segue.destination as! AddItemViewController
            destinationVC.delegate = self
        } else if segue.identifier == "toEditAddItemVC" {
            let destinationVC = segue.destination as! AddItemViewController
            if let cell = sender as? UITableViewCell,
               let indexPath = tableView.indexPath(for: cell),
               let category = categoryForSectionIndex(indexPath.section) {
                let item = toDoList.getTodoList(category: category)[indexPath.row]
                destinationVC.itemToEdit = item
                destinationVC.categoryToEdit = category
                destinationVC.delegate = self
            }
            
        }
    }
    
    // MARK: IBAction funcs

    @IBAction func deleteItems(_ sender: UIBarButtonItem) {
        if let selectedRows = tableView.indexPathsForSelectedRows {
            for indexPath in selectedRows {
                if let category = categoryForSectionIndex(indexPath.section) {
                    let toDos = toDoList.getTodoList(category: category)
                    let rowToDelete = indexPath.row > toDos.count-1 ? toDos.count-1 : indexPath.row
                    let item = toDos[rowToDelete]
                    toDoList.remove(item: item, category: category, index: rowToDelete)
                }
            }
            tableView.beginUpdates()
            tableView.deleteRows(at: selectedRows, with: .automatic)
            tableView.endUpdates()
        }
    }
    
    // MARK: tableView funcs
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
//        if let category = categoryForSectionIndex(indexPath.section) {
        if let _ = categoryForSectionIndex(indexPath.section) {
//            let item = toDoList.getTodoList(category: category)[indexPath.row]
            let indexPaths = [indexPath]
            tableView.deleteRows(at: indexPaths, with: .automatic)
        }
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if let category = categoryForSectionIndex(section) {
            let list = toDoList.getTodoList(category: category)
            return list.count
        } else { return 0 }
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ChecklistItem", for: indexPath)
        if let category = categoryForSectionIndex(indexPath.section) {
            let list = toDoList.getTodoList(category: category)
            let item = list[indexPath.row]
            setLabel(cell: cell, item: item)
            setCheckmark(cell: cell, item: item)
        }
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        if tableView.isEditing { return }
        if let _ = tableView.cellForRow(at: indexPath) { // _ replace with cell
//            if let category = categoryForSectionIndex(indexPath.section) {
//                let list = toDoList.getTodoList(category: category)
//                let item = list[indexPath.row]
//            }
            tableView.deselectRow(at: indexPath, animated: true)
        }
    }
    
    override func tableView(_ tableView: UITableView, moveRowAt sourceIndexPath: IndexPath, to destinationIndexPath: IndexPath) {
        if let srcCategory = categoryForSectionIndex(sourceIndexPath.section),
            let destCategory = categoryForSectionIndex(destinationIndexPath.section) {
            let item = toDoList.getTodoList(category: srcCategory)[sourceIndexPath.row]
            toDoList.move(item: item, sourceCategory: srcCategory, sourceIndex: sourceIndexPath.row, destinationCategory: destCategory, destinationIndex: destinationIndexPath.row)
            tableView.reloadData()
        }
    }
    
    override func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        var title: String? = nil
        if let category = categoryForSectionIndex(section) {
            switch category {
            case .daily: title = "Daily"
            case .weekly: title = "Weekly"
            case .monthly: title = "Monthly"
            case .yearly: title = "Yearly"
            }
        }
        return title
    }
    
    // MARK: Other funcs
    
    func setCheckmark(cell: UITableViewCell, item: ChecklistItem) {
        guard let tvCell = cell as? ChecklistTableViewCell else { return }
        if item.checked { tvCell.toDoCheck.text = "✔️" }
        else { tvCell.toDoCheck.text = "" }
    }
    
    func setLabel(cell: UITableViewCell, item: ChecklistItem) {
        guard let tvCell = cell as? ChecklistTableViewCell else { return }
        tvCell.toDoTextLabel.text = item.text
    }
    
    private func categoryForSectionIndex(_ index: Int) -> ToDoList.Category? {
        return ToDoList.Category(rawValue: index)
    }
    
    private func sectionIndexForCategory(category: ToDoList.Category ) -> Int {
        var idx = -1
        for cat in ToDoList.Category.allCases {
            if cat == category { idx = cat.rawValue }
        }
        return idx
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return ToDoList.Category.allCases.count
    }
    
}
