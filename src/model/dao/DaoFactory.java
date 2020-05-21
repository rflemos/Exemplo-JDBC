package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	//essa classe possui metodos staticos que implementam que instancia os DAOs 
	
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();// retorna a interface mas internamenente retorna uma implementação dela mesma 
	}

}
