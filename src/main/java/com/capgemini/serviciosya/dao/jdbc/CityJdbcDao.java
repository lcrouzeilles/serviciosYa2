package com.capgemini.serviciosya.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.serviciosya.beans.domain.City;
import com.capgemini.serviciosya.dao.DaoException;
import com.capgemini.serviciosya.dao.IJdbcDao;

public class CityJdbcDao implements IJdbcDao<City, Integer> {
	private static final Logger logger = Logger.getLogger(CityJdbcDao.class);

	public void create(City c) {
		try {
			// Establish connection
			logger.debug("Establishing connection...");
			Connection conn = CapgeminiDB.getConnection();
			// Set the query
			String query = "INSERT INTO city (id, name) values " + "(" + c.getId_city() + ", " + c.getName() + ")";
			PreparedStatement ps = conn.prepareStatement(query);

			/*
			 * String query = "INSERT INTO cities (id, name) values (?, ?)";
			 * PreparedStatement ps = conn.prepareStatement(query); ps.setInt(1,
			 * c.getId_city()); ps.setString(2, c.getName());
			 */

			logger.debug("Executing query: [%s]" + query);
			// Execute query
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated == 0)
				throw new DaoException("Failing to create the new city");

		} catch (SQLException e) {
			logger.error("Failing to create the new city");
			throw new DaoException("Failing to create the new city", e);
		} catch (Exception e) {
			logger.error("Failing to create the new city");
			throw new DaoException(e.getMessage(), e);
		}

	}

	public City read(Integer p_key) {
		City c = new City();

		try {
			// Establish connection
			logger.debug("Establishing connection...");
			Connection conn = CapgeminiDB.getConnection();
			// Structuring and executing query
			String sql = "SELECT * FROM city WHERE id =" + p_key;
			logger.debug("Executing query: [%s]" + sql);

			// por que tiene que ser Statement?

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			c = (City) rs.getObject(p_key);

			if (c.getName().isEmpty()) {
				logger.error("Failing to convert data to Object type");
				throw new DaoException("Failing to convert data to Object type");
			}

		} catch (SQLException e) {
			logger.error("Failing to convert data to Object type");
			throw new DaoException("Failing to convert data to Object type", e);
		} catch (Exception e) {
			logger.error("Failing to convert data to Object type");
			throw new DaoException(e.getMessage(), e);
		}
		return c;
	}

	public void update(City object) {
		try {
			// Establish connection
			logger.debug("Establishing connection...");
			Connection conn = CapgeminiDB.getConnection();
			String sql = "UPDATE * FROM city WHERE id =" + object.getId_city();
			logger.debug("Structuring and executing query: [%s]" + sql);
			PreparedStatement ps = conn.prepareStatement(sql);
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated == 0) {
				logger.error("Failing to update data");

				// si funciona el update pero no se modifica ningun dato, que arroja? 1

				throw new DaoException("Failing to update data");
			}

		} catch (SQLException e) {
			logger.error("Failing to update data");
			throw new DaoException("Failing to update data", e);
		} catch (Exception e) {
			logger.error("Failing to update data");
			throw new DaoException(e.getMessage(), e);
		}

	}

	public void delete(Integer p_key) {
		try {
			// Establish connection
			Connection conn = CapgeminiDB.getConnection();
			// Structuring and executing query
			String sql = "DELETE * FROM city WHERE id =" + p_key;
			PreparedStatement ps = conn.prepareStatement(sql);
			logger.debug("Structuring and executing query: [%s]" + sql);
			int rowsUpdated = ps.executeUpdate();

			if (rowsUpdated == 0) {
				logger.error("Failing to delete data");
				throw new DaoException("Failing to delete data");
			}
		} catch (SQLException e) {
			logger.error("Failing to delete data");
			throw new DaoException("Failing to delete data", e);
		} catch (Exception e) {
			logger.error("Failing to delete data");
			throw new DaoException(e.getMessage(), e);
		}
	}

	public List<City> readAll() {
		// Cities list.
		List<City> cities = new ArrayList<City>();

		try {

			// Get connection.
			logger.debug("Getting new connection...");
			Connection conn = CapgeminiDB.getConnection();

			Statement statement = conn.createStatement();

			// Execute the query.
			String sql = "SELECT * FROM city";
			logger.debug(String.format("Executing query [%s]", sql));
			ResultSet rs = statement.executeQuery(sql);

			// Read the result.

			while (rs.next()) {

				City c = new City();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));

				// Add new object to list.
				cities.add(c);
			}

		} catch (Exception e) {

			// Failure.
			logger.error("Failure searching all cities");
			throw new DaoException("Failure searching all cities", e);
		}

		// Return results.
		return cities;
	}

}
