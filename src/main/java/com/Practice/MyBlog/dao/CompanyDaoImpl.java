package com.Practice.MyBlog.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Practice.MyBlog.service.dto.CompanyServiceIO;

@Repository("CompanyDao")
public class CompanyDaoImpl implements CompanyDao {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);
	@Autowired
	private DataSource			ds; 
	
	/*
	 * TABLE 명 : COMPANY_INFO;
	 * COLUMNS : COMPANY_ID, COMPANY_NAME,COMPANY_TEL_NUMBER, LAST_CHANGE_TIMESTAMP
	 */
	
	static private  String insertQuery="INSERT INTO COMPANY_INFO (COMPANY_ID, COMPANY_NAME, COMPANY_TEL_NUMBER, LAST_CHANGE_TIMESTAMP) VALUES(?,?,?,?)";
	static private  String selectQuery="SELECT * FROM COMPANY_INFO WHERE COMPANY_ID='?'";
	static private  String selectAllQuery="SELECT * FROM COMPANY_INFO";
	static private  String updateQuery="UPDATE COMPANY_INFO (COMPANY_ID,COMPANY_NAME,COMPANY_TEL_NUMBER,LAST_CHANGE_TIMESTAMP)VALUES('','','','')\"";
	static private  String deleteQuery="DELETE FROM COMPANY_INFO WHERE COMPANY_ID='?'";
	static private  String selectForUpdateQuery= "SELECT COUNT(*) FROM COMPANY_INFO WHERE 1=1 FOR UPDATE ";
	
	
	
	
	
	
	/**
	 * 
	 */
	public CompanyServiceIO insert(CompanyServiceIO companyServiceIO) {
		// TODO Auto-generated method stub
		Connection con =null;
		try {
			con = ds.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setString(1, companyServiceIO.getCompanyId());
			preparedStatement.setString(2, companyServiceIO.getCompanyNm());
			preparedStatement.setString(3, companyServiceIO.getCompanyTelNbr());
			preparedStatement.setString(4, companyServiceIO.getLastChngTmstmp());
			preparedStatement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("SQL Eception : {}",e);
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Connection Close : {}",e);
			}
		}
		
		return null;
	}

	public CompanyServiceIO update(CompanyServiceIO companyServiceIO) {
		// TODO Auto-generated method stub
		Connection con =null;
		try {
			con = ds.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
			preparedStatement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("SQL Eception : {}",e);
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Connection Close : {}",e);
			}
		}
		return null;
	}

	public List<CompanyServiceIO> get(CompanyServiceIO companyServiceIO) {
		// TODO Auto-generated method stub
		Connection con =null;
		List<CompanyServiceIO> resultList = new ArrayList<CompanyServiceIO>();
		try {
			con = ds.getConnection();
			if(companyServiceIO.getCompanyNm() == null || companyServiceIO.getCompanyNm().isEmpty())
			{
				//전화번호로 
			}
			else if(companyServiceIO.getCompanyTelNbr() == null || companyServiceIO.getCompanyTelNbr().isEmpty())
			{
				
			}
			else
			{
				
			}
			PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
		 	ResultSet resultSet = preparedStatement.executeQuery();
			
		 	
	 		while(resultSet.next())
	 		{
	 			CompanyServiceIO out =new CompanyServiceIO();
	 			out.setCompanyId(resultSet.getString(1));
	 			out.setCompanyNm(resultSet.getString(2));
	 			out.setCompanyTelNbr(resultSet.getString(3));
	 			resultList.add(out);
	 		}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("SQL Eception : {}",e);
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Connection Close : {}",e);
			}
		}
		return resultList;
	}


	public CompanyServiceIO delete(CompanyServiceIO companyServiceIO) {
		// TODO Auto-generated method stub
		Connection con =null;
		try {
			con = ds.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(deleteQuery);
			preparedStatement.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("SQL Eception : {}",e);
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Connection Close : {}",e);
			}
		}
		return null;
	}
	public BigDecimal selectForUpdate() {
		// TODO Auto-generated method stub
		Connection con =null;
		BigDecimal nextIdx= BigDecimal.ZERO;
		try {
			con = ds.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(selectForUpdateQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next())
			{
				nextIdx = resultSet.getBigDecimal(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("SQL Eception : {}",e);
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("Connection Close : {}",e);
				}
			}
			return nextIdx;
	}

	public List<CompanyServiceIO> getAll() {
		// TODO Auto-generated method stub
				Connection con =null;
				List<CompanyServiceIO> resultList = new ArrayList<CompanyServiceIO>();
				try {
					con = ds.getConnection();
					PreparedStatement preparedStatement = con.prepareStatement(selectAllQuery);
				 	ResultSet resultSet = preparedStatement.executeQuery();
					
				 	
			 		while(resultSet.next())
			 		{
			 			CompanyServiceIO out =new CompanyServiceIO();
			 			out.setCompanyId(resultSet.getString(1));
			 			out.setCompanyNm(resultSet.getString(2));
			 			out.setCompanyTelNbr(resultSet.getString(3));
			 			resultList.add(out);
			 		}
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					logger.error("SQL Eception : {}",e);
				}
				finally
				{
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						logger.error("Connection Close : {}",e);
					}
				}
				return resultList;
	}	

}
