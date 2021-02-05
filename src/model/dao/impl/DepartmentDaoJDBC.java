package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO DEPARTMENT (NAME) VALUES(?)",Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			int rowsAfected = st.executeUpdate();
			if(rowsAfected>0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultset(rs);
			}
			else {
				throw new DbException("Unexpected error! no rows affected");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE DEPARTMENT SET NAME = ? WHERE ID = ?");
			
			st.setString(1,obj.getName());
			st.setInt(2, obj.getId());
			st.executeUpdate();
			
			
			
		} catch (SQLException e) {
			throw new DbException(e.getLocalizedMessage());
			
		}
		finally {
			DB.closeStatement(st);
		}
	
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM DEPARTMENT WHERE ID = ? ");
			
			st.setInt(1, id);
			st.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("Select * FROM department WHERE ID=?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
			Department obj =  instantiateDepartment(rs);
			return obj;
			}
			return null;	
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeResultset(rs);
		}
	}
		
		
		
		
	

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT Id as DepartmentId, department.Name as DepName  FROM department order by name");
			
			rs = st.executeQuery();
			
			List<Department> list = new ArrayList<>();
		while(rs.next()) {
		
			list.add(SellerDaoJDBC.instantiateDepartment(rs));
			
			
					
		}
		
		return list;
	}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
			DB.closeResultset(rs);
		}
		
		

}
	protected static Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));// foi instanciado um departamento e setado os valores dele utilizado os valores que viream do banco de dados para o reslt set
		
		return dep;
	}
}