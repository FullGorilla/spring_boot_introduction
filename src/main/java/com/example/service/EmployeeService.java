package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeService {
    // Repositoryをフィールドに用意する
    private final EmployeeRepository employeeRepository;

    // コンストラクタでRepositoryをインジェクションします
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    // ビジネスロジック用のメソッドを以降に定義します
 // 全件取得処理
    // メソッド名はRepositoryの処理や最終的な返り値がわかるような名前にしましょう
    public List<Employee> findAllEmployee() {
        // employeesテーブルのデータをemployeeオブジェクトにつめて全件取得する
        return this.employeeRepository.findAll();
    }
    
    public Employee findEmployee(Integer employeeId) {
        // データの1件取得
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(employeeId);
        // OptionalからEntityクラスの取得を試みる
        Employee employee = optionalEmployee.get();
        return employee;
    }
    
 // 絞り込み検索
    public List<Employee> findByName(String name) {
        return this.employeeRepository.findByName(name);
    }
}