//
//  ToDoList.swift
//  ToDoApp
//
//  Created by Raymond Kim on 3/20/24.
//

import Foundation

class ToDoList: Codable {
    
    enum Category: Int, CaseIterable {
        case daily
        case weekly
        case monthly
        case yearly
    }
    
//    for c in Category.allCases
    
    private var dailyToDos: [ChecklistItem] = []
    private var weeklyToDos: [ChecklistItem] = []
    private var monthlyToDos: [ChecklistItem] = []
    private var yearlyToDos: [ChecklistItem] = []
    
    init() {
        var item = ChecklistItem(text: "Drink water", checked: true)
        dailyToDos.append(item)
        item = ChecklistItem(text: "Laundry", checked: false)
        weeklyToDos.append(item)
        item = ChecklistItem(text: "Clean kitchen", checked: false)
        monthlyToDos.append(item)
        item = ChecklistItem(text: "Make Thanksgiving dinner", checked: false)
        yearlyToDos.append(item)
    }
    
    func addToDo(text: String, checked: Bool) -> ChecklistItem {
        let item = ChecklistItem(text: text, checked: checked)
        dailyToDos.append(item)
        return item
    }
    
    func move(item: ChecklistItem, sourceCategory: Category, sourceIndex: Int, destinationCategory: Category, destinationIndex: Int) {
        remove(item: item, category: sourceCategory, index: sourceIndex)
        addToDo(item: item, category: destinationCategory, index: destinationIndex)
    }
    
    func remove(item: ChecklistItem, category: Category, index: Int) {
//        for item in items { if let idx = toDos.firstIndex(of: item) { toDos.remove(at: idx) } }
        switch category {
        case .daily: dailyToDos.remove(at: index)
        case .weekly: weeklyToDos.remove(at: index)
        case .monthly: monthlyToDos.remove(at: index)
        case .yearly: yearlyToDos.remove(at: index)
        }
    }
    
    func getTodoList(category: Category) -> [ChecklistItem] {
        switch category {
        case .daily: return dailyToDos
        case .weekly: return weeklyToDos
        case .monthly: return monthlyToDos
        case .yearly: return yearlyToDos
        }
    }

    func addToDo(item: ChecklistItem, category: Category, index: Int = -1) {
        switch category {
        case .daily:
            if index < 0 { dailyToDos.append(item) }
            else { dailyToDos.insert(item, at: index) }
        case .weekly:
            if index < 0 { weeklyToDos.append(item) }
            else { weeklyToDos.insert(item, at: index) }
        case .monthly:
            if index < 0 { monthlyToDos.append(item) }
            else { monthlyToDos.insert(item, at: index) }
        case .yearly:
            if index < 0 { yearlyToDos.append(item) }
            else { yearlyToDos.insert(item, at: index) }
        }
    }
    
}
