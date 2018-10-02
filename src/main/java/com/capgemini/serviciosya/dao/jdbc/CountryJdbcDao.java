package com.capgemini.serviciosya.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.serviciosya.beans.domain.Country;
import com.capgemini.serviciosya.dao.DaoException;
import com.capgemini.serviciosya.dao.IJdbcDao;

public class CountryJdbcDao implements IJdbcDao<Country, Integer> {

	private static final Logger logger = Logger.getLogger(CountryJdbcDao.class);

	public CountryJdbcDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void create(Country c) {
		try {

			//Establish connection.
			logger.debug("Establishing connection...");
			Connection conn = CapgeminiDB.getConnection();

			String sql = "insert into country (id, name) values (?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getName());

			// Execute the query.
			logger.debug(String.format("Executing query [%s]", sql));
			int linesUpdated = ps.executeUpdate();

			// Read the result.
			if (linesUpdated == 0) {

				throw new DaoException("Failure inserting new country!");
			}

		} catch (SQLException e) {

			// Failure.
			logger.error("Failure inserting new country!");
			throw new DaoException("Failure inserting new country!", e);

		} catch (Exception e) {

			// Failure.
			logger.error("Failure inserting new country!");
			throw new DaoException(e.getMessage(), e);
		}

	}

	public Country read(Integer p_key) {
		Country c = new Country();
		try {
			// Get connection
			logger.debug("Reading database...");
			Connection conn = CapgeminiDB.getConnection();
			String query = "SELECT * FROM countries WHERE id =" + p_key;
			PreparedStatement ps = conn.prepareStatement(query);

			// Execute query
			logger.debug(String.format("Executing query [%s]", query));
			ResultSet rs = ps.executeQuery(query);
			//Converting data
			c = (Country) rs.getObject(p_key);
			//If "Name" field is empty
			if(c.getName().isEmpty()) {
				throw new DaoException("Failure reading country");
			}
		} catch (SQLException e) {
			// Failure.
			logger.error("Failure reading country");
			throw new DaoException("Failure reding country", e);
		} catch (Exception e) {

			// Failure.
			logger.error("Failure reading country");
			throw new DaoException(e.getMessage(), e);
		}
		return c;
	}

	public void update(Country object) {
		
		try {
			//Get connection
			logger.debug("Getting the connection...");
			Connection conn = CapgeminiDB.getConnection();
			String query = "UPDATE * FROM countries WHERE id=" + object.getId();
			logger.debug("Executing query: [%s]" + query);
			PreparedStatement ps = conn.prepareStatement(query);
			//Get results
			int linesUpdated = ps.executeUpdate();
			if(linesUpdated==0)
				throw new DaoException("Failure updating values");
			
		} catch (SQLException e) {
			logger.error("Failure updating values");
			throw new DaoException("Failure updating values", e);
		} catch(Exception e) {
			logger.error("Failure updating values");
			throw new DaoException(e.getMessage(), e);
		}

	}

	public void delete(Integer p_key) {

		try {
			//Get connection
			logger.debug("Getting the connection...");
			Connection conn = CapgeminiDB.getConnection();
			String query = "DELETE * FROM countries WHERE id=" + p_key;
			logger.debug("Executing query: [%s]" + query);
			PreparedStatement ps = conn.prepareStatement(query);
			//Get results
			int linesUpdated = ps.executeUpdate();
			if(linesUpdated==0)//verificar si devolerìa 1 o 0
				throw new DaoException("Failure deleting values");
			
		} catch (SQLException e) {
			logger.error("Failure deleting values");
			throw new DaoException("Failure deleting values", e);
		} catch(Exception e) {
			logger.error("Failure deleting values");
			throw new DaoException(e.getMessage(), e);
		}


	}

	public List<Country> readAll() {
		// Occupations list.
		List<Country> countries = new ArrayList<Country>();

		try {

			// Get connection.
			logger.debug("Getting new connection...");
			Connection conn = CapgeminiDB.getConnection();

			Statement statement = conn.createStatement();

			// Execute the query.
			String sql = "select * from country";
			logger.debug(String.format("Executing query [%s]", sql));
			ResultSet rs = statement.executeQuery(sql);

			// Read the result.

			while (rs.next()) {

				Country c = new Country();
				c.setId(rs.getInt("ID"));
				c.setName(rs.getString("NAME"));

				// Add new object to list.
				countries.add(c);
			}

		} catch (Exception e) {

			// Failure.
			logger.error("Failure searching all countries");
			throw new DaoException("Failure searching all countries", e);
		}

		// Return results.
		return countries;
	}

}
