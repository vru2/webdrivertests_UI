package headLessBooking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class JDBCPreparedStatement {
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:oracle:thin:@10.10.12.16:1521:cleardb";
	private static final String DB_USER = "review";
	private static final String DB_PASSWORD = "review123clear";

	
	
	  
	public  List<String> getAllReviewsId(String activityId) throws SQLException {
		Connection dbConnection = null;
		Statement statement = null;
		String selectSQL = "select id from USER_REVIEWS where REVIEWABLE_ID ="+activityId;
		List<String> COLLECTIONS = new ArrayList<String>();
		try {
			dbConnection = getDBConnection();
			statement = dbConnection.createStatement();
			ResultSet rs = statement.executeQuery(selectSQL);
			while (rs.next()) {
				String id = rs.getString("id");
				COLLECTIONS.add(id);
			}
		} catch (SQLException e) {
		System.out.println(e.getMessage());
		} finally {
			if (statement  != null) {
				statement .close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		return COLLECTIONS;
	}

	public  List<String> getAllReviewsVariantId(String activityId) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String selectSQL = "select REVIEWABLE_SUB_TYPE_ID from USER_REVIEWS where REVIEWABLE_ID ="+activityId+" AND ROWNUM=1";
        List<String> COLLECTIONS = new ArrayList<String>();
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectSQL);
            while (rs.next()) {
                String REVIEWABLE_SUB_TYPE_ID = rs.getString("REVIEWABLE_SUB_TYPE_ID");
                COLLECTIONS.add(REVIEWABLE_SUB_TYPE_ID);
            }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        } finally {
            if (statement  != null) {
                statement .close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return COLLECTIONS;
    }

	public  List<String> getAvgRating(String activityId) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String selectSQL = "select AVG(rating) from USER_REVIEWS where REVIEWABLE_ID ="+activityId;
        List<String> COLLECTIONS = new ArrayList<String>();
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectSQL);
          
            while (rs.next()) {
                String avgRating = rs.getString(1);
                COLLECTIONS.add(avgRating);
            }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        } finally {
            if (statement  != null) {
                statement .close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return COLLECTIONS;
    }

	public  List<String> getReviewCount(String activityId) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String selectSQL = "select count(id) from USER_REVIEWS where REVIEWABLE_ID ="+activityId+" AND COMMENTS is not null";
        List<String> COLLECTIONS = new ArrayList<String>();
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(selectSQL);
          
            while (rs.next()) {
                String commentsCount = rs.getString(1);
                COLLECTIONS.add(commentsCount);
            }
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        } finally {
            if (statement  != null) {
                statement .close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return COLLECTIONS;
    }

	
	public  void delete_USER_REVIEW_ATTRIBUTE_MAP(String activityId) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String selectSQL = "delete from USER_REVIEW_ATTRIBUTE_MAP WHERE USER_REVIEW_ID in(select id from USER_REVIEWS where REVIEWABLE_ID ="+activityId+")";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.executeQuery(selectSQL);
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        } finally {
            if (statement  != null) {
                statement .close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
     
    }
	
	public  void delete_USER_REVIEWS(String activityId) throws SQLException {
	    
	    delete_USER_REVIEW_ATTRIBUTE_MAP(activityId);
        Connection dbConnection = null;
        Statement statement = null;
        String selectSQL = "delete from USER_REVIEWS where REVIEWABLE_ID ="+activityId;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.executeQuery(selectSQL);
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        } finally {
            if (statement  != null) {
                statement .close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
	
	
	public  void delete_PRODUCT_REVIEWS(String activityId) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String selectSQL = "delete from PRODUCT_REVIEWS where REVIEWABLE_ID ="+activityId;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            statement.executeQuery(selectSQL);
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        } finally {
            if (statement  != null) {
                statement .close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
	
	
	
	public static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}

	public java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
	
	public String changeDatedFormat(String date) throws ParseException {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
	    Date originalDate = format.parse(date);
	    SimpleDateFormat formatTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = formatTo.format(originalDate);
	    return formattedDate;
	}
	
    public String unixTimestamp(long time) {
        Date date = new Date(time * 1000L);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        String formatted = format.format(date);
        return formatted;

    }
	    

}