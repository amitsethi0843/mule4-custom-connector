package org.mule.extension.internal.connection;

import java.sql.*;

public class DbConnection {

    private ProviderType type;

    private String dbName;

    private String userName;

    private String password;

    private String tableName;

    private Connection con;



    public boolean connect() throws SQLException,ClassNotFoundException {

        switch (type){
            case MYSQL:
                Class.forName("com.mysql.jdbc.Driver");
                con= DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/"+this.dbName,this.userName,this.password);
                break;
            default:
                Class.forName("com.mysql.jdbc.Driver");
                con= DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/sonoo","root","root");
        }
        return con.isValid(1000);
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
        if(con!= null && con.isValid(1000)) {
            System.out.println("--------------- con is valid");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * \n" +
                    "FROM information_schema.tables\n" +
                    "WHERE table_schema = '" + this.dbName + "' \n" +
                    "    AND table_name = '" + this.tableName + "' LIMIT 1");
            System.out.println("--------------- rs  "+rs);

            if (rs.first()) {
                return true;
            }
            return false;
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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Connection getCon() {
        return con;
    }
}
