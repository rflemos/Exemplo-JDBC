package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	//essa classe possui metodos staticos que implementam que instancia os DAOs 
	
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());// retorna a interface mas internamenente retorna uma implementação dela mesma 
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());// retorna a interface mas internamenente retorna uma implementação dela mesma 
	}
}
