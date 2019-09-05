package org.mule.connection;

import java.sql.*;

public class DbConnection {

    private ProviderType type;

    private String dbName;

    private String userName;

    private String url;
    
    private String password;

    private Connection con;



    public boolean connect() throws SQLException,ClassNotFoundException {
    	if(!logToFiles()) {
	        switch (type){
	            case MYSQL:
	                Class.forName("com.mysql.jdbc.Driver");
	                con= DriverManager.getConnection(
	                        "jdbc:mysql://"+this.url+"/"+this.dbName,this.userName,this.password);
	                break;
	            case ORACLEDB:
	                Class.forName("oracle.jdbc.driver.OracleDriver");
	                con= DriverManager.getConnection(
	                        "jdbc:oracle:thin:@"+this.url,this.userName,this.password);
	                break;
	            default:
	                Class.forName("com.mysql.jdbc.Driver");
	                con= DriverManager.getConnection(
	                        "jdbc:mysql://localhost:3306/sonoo","root","root");
	        }
	        return con.isValid(1000);
    	}
    	else
    	{
    		return true;
    	}
    	
    }

    public void disconnect(){
        try {
            this.con.close();
        }
        catch (SQLException ex){
            System.out.println("error message: "+ex.getMessage());
        }
    }

    public boolean isValid()throws SQLException{
        if(logToFiles() || (con!= null && con.isValid(1000))) {
//            System.out.println("--------------- con is valid");
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * \n" +
//                    "FROM information_schema.tables\n" +
//                    "WHERE table_schema = '" + this.dbName + "' \n" +
//                    "    AND table_name = '" + this.tableName + "' LIMIT 1");
//            System.out.println("--------------- rs  "+rs);
//
//            if (rs.first()) {
//                return true;
//            }
            return true;
        }
        return false;
    }

    public ProviderType getType() {
        return type;
    }

    public void setType(ProviderType type) {
        this.type = type;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setURL(String url) {
    	this.url=url;
    }

    public Connection getCon() {
        return con;
    }
    
    public boolean logToFiles() {
    	if(this.url == null && this.dbName==null && this.userName==null && this.password == null) {
    		return true;
    	}
    	return false;
    }
}
