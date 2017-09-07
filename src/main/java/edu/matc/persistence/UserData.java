package edu.matc.persistence;

import edu.matc.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import org.apache.log4j.*;

/**
 * Access users in the user table.
 *
 * @author pwaite
 */
public class UserData {

    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * This method will perform the search given the SQL to perform and Search
     * ojbect to place the results in.
     *
     * @param searchType The type of database search to perform
     * @param searchTerm The string to use in the search clause.
     *
     * @return userSearch The List of users found in the search results
     */
    public List<User> userSearch(String searchType, String searchTerm) {

        List<User> users = new ArrayList<User>();
        String sql = "SELECT * FROM users";

        if (searchType.equals("userid")) {
            sql = userIdSearch(sql,searchTerm);
        } else if (searchType.equals("first_name")) {
            sql = firstNameSearch(sql,searchTerm);
        } else if (searchType.equals("last_name")) {
            sql = lastNameSearch(sql,searchTerm);
        }

        Database database = Database.getInstance();
        Connection connection = null;
        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);
            while (results.next()) {
                User employee = createUserFromResults(results);
                users.add(employee);
            }
            database.disconnect();
        } catch (SQLException e) {
            logger.info("SearchUser.getAllUsers()...SQL Exception: " + e);
        } catch (Exception e) {
            logger.info("SearchUser.getAllUsers()...Exception: " + e);
        }

        return users;
    }


    /**
     * This method will format the sql with the user ID search criteria.
     *
     * @param sql The start of the SQL before search critieria is added.
     * @param searchTerm The string to use in the search clause.
     *
     * @return String The finalized SQL to be used in the search.
     */
    private String userIdSearch(String sql, String searchTerm) {
        return sql + " where id = " + searchTerm + "";
    }

    /**
     * This method will format the sql with the first name search criteria.
     *
     * @param sql The start of the SQL before search critieria is added.
     * @param searchTerm The string to use in the search clause.
     *
     * @return String The finalized SQL to be used in the search.
     */
    private String firstNameSearch(String sql, String searchTerm) {
        return sql + " where first_name like '" + searchTerm + "%'";
    }

    /**
     * This method will format the sql with the last name search criteria.
     *
     * @param sql The start of the SQL before search critieria is added.
     * @param searchTerm The string to use in the search clause.
     *
     * @return String The finalized SQL to be used in the search.
     */
    private String lastNameSearch(String sql, String searchTerm) {
        logger.info("The sql is:" + sql);
        return sql + " where last_name like '" + searchTerm + "%'";

    }

    /**
     * This method will create the User object with the SQL Search Results
     *
     * @param resultSet The resultSet from the SQL search
     * @return user The User objet created by processing the ResultSet
     */
    private User createUserFromResults(ResultSet results) throws SQLException {
        User user = new User();
        user.setLastName(results.getString("last_name"));
        user.setFirstName(results.getString("first_name"));
        user.setUserid(results.getString("id"));
        LocalDate birthDate = LocalDate.parse(results.getString("date_of_birth"));
        user.setBirthDate(birthDate);
        return user;
    }

}