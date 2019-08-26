package org.mule.extension.internal.connection;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

import java.sql.SQLException;

public class DbConnecionProvider implements ConnectionProvider<DbConnection> {


    @DisplayName("Database Provider")
    @Parameter
    private ProviderType databaseType;


    @DisplayName("Database Name")
    @Parameter
    private String databaseName;

    @DisplayName("Username")
    @Parameter
    private String userName;

    @DisplayName("Password")
    @Parameter
    private String password;


    @Override
    public DbConnection connect() throws ConnectionException {

        DbConnection dbConnection=new DbConnection();
        dbConnection.setType(this.databaseType);
        dbConnection.setDbName(this.databaseName);
        dbConnection.setUserName(this.userName);
        dbConnection.setPassword(this.password);
        try {
            dbConnection.connect();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return dbConnection;
    }

    @Override
    public void disconnect(DbConnection connection) {

    }

    @Override
    public ConnectionValidationResult validate(DbConnection connection) {
        try{
            if(connection.isValid()){
                return ConnectionValidationResult.success();
            }
            else{
                return ConnectionValidationResult.failure("Couldn't connect to database",new RuntimeException("Connection couldn't not be established due to invalid credentials"));
            }
        }catch (Exception ex){
            return ConnectionValidationResult.failure("Couldn't connect to database",ex);
        }
    }
}
