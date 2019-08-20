package org.mule.extension.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import org.mule.extension.internal.connection.DbConnecionProvider;
import org.mule.extension.internal.connection.DbConnection;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;
import java.util.logging.Logger;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class BasicOperations {

  private static Logger logger=Logger.getLogger(BasicOperations.class.getName());


  @Parameter
  private LogLevelType loggerLevel;

  @Parameter
  private boolean saveToDatabase=false;

  /**
   * Example of an operation that uses the configuration and a connection instance to perform some action.
   */
//  @MediaType(value = ANY, strict = false)
//  public String retrieveInfo(@Config BasicConfiguration configuration, @Connection BasicConnection connection){
//    return "Using Configuration [" + configuration.getConfigId() + "] with Connection id [" + connection.getId() + "]";
//  }

  /**
   * Example of a simple operation that receives a string parameter and returns a new string message that will be set on the payload.
   */
//  @MediaType(value = ANY, strict = false)
//  public String sayHi(String person) {
//    return buildHelloMessage(person);
//  }

//  /**
//   * Private Methods are not exposed as operations
//   */
//  private String buildHelloMessage(String person) {
//    return "Hello " + person + "!!!";
//  }

  @MediaType(value = ANY, strict = false)
  public void logMesage(@Expression(ExpressionSupport.REQUIRED) String messasge, @Connection DbConnection dbConnection,@Config BasicConfiguration configuration){
    if(saveToDatabase) {
      java.sql.Connection con = dbConnection.getCon();
      try {
        String sql = "INSERT into custom_connector(corelationId,log_level,message) values (?,?,?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, configuration.isStoreCorelation() ? UUID.randomUUID().toString().replaceAll("-", "") : null);
        preparedStatement.setString(2, this.loggerLevel.name());
        preparedStatement.setString(3, messasge);
        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted <= 0) {
          throw new RuntimeException("row not inserted");
        }
      } catch (Exception ex) {
        logger.severe(ex.getMessage());
      }
    }
    switch (this.loggerLevel){
      case ERROR:
        logger.severe(messasge);
        break;
      case WARNING:
        logger.warning(messasge);
      case INFO:
        logger.info(messasge);
    }
  }

}
