package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("=== TEST 1: department  findByAll ===");
		List<Department> list = departmentDao.findAll();
		
		for(Department obj : list) {
			System.out.println(obj);
		}
		System.out.println();
		
		System.out.println("=== TEST 2: department  findByid ===");
		Department dep = departmentDao.findById(5);
		System.out.print(dep);
		
		System.out.println();
		System.out.println("\n=== TEST 3: Department  insert  ===");
		Department depInsert = new Department(null,"Arts");
		departmentDao.insert(depInsert);
		System.out.println("Insert department ID " + depInsert.getId());
		
		System.out.println();
		
		System.out.println("\n=== TEST 4: Department  update  ===");
		Department depUpdate = departmentDao.findById(5);
		depUpdate.setName("Quality");
		departmentDao.update(depUpdate);
		
	System.out.println();
		
		System.out.println("\n=== TEST 5: Department  Delete  ===");
		
		departmentDao.deleteById(11);
		departmentDao.deleteById(12);
		departmentDao.deleteById(13);
		
	}

}
