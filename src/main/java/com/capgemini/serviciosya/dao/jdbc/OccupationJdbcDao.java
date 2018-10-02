package com.capgemini.serviciosya.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.capgemini.serviciosya.beans.domain.Occupation;
import com.capgemini.serviciosya.dao.DaoException;
import com.capgemini.serviciosya.dao.IJdbcDao;



	public class OccupationJdbcDao implements IJdbcDao <Occupation, Integer>{


	    private static final Logger logger = Logger.getLogger (OccupationJdbcDao.class);


	    public OccupationJdbcDao () {

	        super ();
	    }


	    public List<Occupation> readAll () {


	        // Occupations list.
	        List<Occupation> occupations = new ArrayList<Occupation> ();

	        try {

	            // Get connection.
	            logger.debug ("Getting new connection...");
	            Connection conn = CapgeminiDB.getConnection ();

	            Statement statement = conn.createStatement ();


	            // Execute the query.
	            String sql = "select * from occupation";
	            logger.debug (String.format ("Executing query [%s]", sql));
	            ResultSet rs = statement.executeQuery (sql);


	            // Read the result.

	            while (rs.next ()) {

	                Occupation o = new Occupation ();
	                o.setId (rs.getInt ("ID"));
	                o.setName (rs.getString ("NAME"));
	                o.setDescription (rs.getString ("DESCRIPTION"));

	                // Add new object to list.
	                occupations.add (o);
	            }

	        } catch (Exception e) {

	            // Failure.
	            logger.error ("Failure searching all occupations");
	            throw new DaoException ("Failure searching all occupations", e);
	        }

	        // Return results.
	        return occupations;
	    }

	    public void create (Occupation occupation) {

	        try {

	            // Get connection.
	            logger.debug ("Getting new connection...");
	            Connection conn = CapgeminiDB.getConnection ();

	            String sql = "insert into occupation (name, description) values (?, ?)";
	            PreparedStatement ps = conn.prepareStatement (sql);
	            ps.setString (1, occupation.getName ());
	            ps.setString (2, occupation.getDescription ());


	            // Execute the query.
	            logger.debug (String.format ("Executing query [%s]", sql));
	            int c = ps.executeUpdate ();


	            // Read the result.
	            if (c == 0) {

	                throw new DaoException ("Failure inserting new occupations!");
	            }


	        } catch (SQLException e) {

	            // Failure.
	            logger.error ("Failure inserting new occupation!");
	            throw new DaoException ("Failure inserting new occupation!", e);

	        } catch (Exception e) {

	            // Failure.
	            logger.error ("Failure inserting new occupation!");
	            throw new DaoException (e.getMessage (), e);
	        }
	    }
	

	public void update(Occupation occupation) {
		
	}

	public Occupation read(Integer p_key) {
		// TODO Auto-generated method stub
		return null;
	}


	public void delete(Integer p_key) {
		// TODO Auto-generated method stub
		
	}
}
