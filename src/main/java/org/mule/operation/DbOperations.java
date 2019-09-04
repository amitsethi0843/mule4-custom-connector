package org.mule.operation;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.mule.util.LogLevelType;
import org.mule.conf.LoggerConfiguration;
import org.mule.connection.DbConnection;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Parameter;


/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class DbOperations {

  private static Logger logger=Logger.getLogger(DbOperations.class.getName());

  @Parameter
  private LogLevelType loggerLevel;

  @MediaType(value = ANY, strict = false)
  public void dbMessage(@Expression(ExpressionSupport.REQUIRED) String message,@Expression(ExpressionSupport.REQUIRED) String referenceNumber, @Connection DbConnection dbConnection,@Config LoggerConfiguration configuration, boolean saveToDatabase){
    if(saveToDatabase) {
      java.sql.Connection con = dbConnection.getCon();
      try {
        String sql = "INSERT into "+configuration.getDbConfParams().getTableName()+"("+configuration.getDbConfParams().getReferenceNumberColumn()+","+configuration.getDbConfParams().getLogLevelColumn()+","+configuration.getDbConfParams().getMessageColumn()+","+configuration.getDbConfParams().getDataColumn()+") values (?,?,?,?)";
        System.out.println("executing sql query -----------------"+sql);
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, referenceNumber);
        preparedStatement.setString(2, this.loggerLevel.name());
        preparedStatement.setString(3, message);
        preparedStatement.setTimestamp(4,new Timestamp(new java.util.Date().getTime()));
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
        logger.severe(message);
        break;
      case WARNING:
        logger.warning(message);
        break;
      case INFO:
        logger.info(message);
    }
  }

  
  @MediaType(value = ANY, strict = false)
	 public void fileMesage(@Expression(ExpressionSupport.REQUIRED) String message,@Config LoggerConfiguration conf) {
		 boolean append = true;
      try {
			FileHandler handler = new FileHandler(conf.getFileConfParams().getLogPath()+"/appLog_%g.log", 10000,10);
			handler.setFormatter(new SimpleFormatter());
			logger.addHandler(handler);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		 switch (this.loggerLevel){
		 case ERROR:
			 logger.severe(message);
			 break;
	      case WARNING:
	        logger.warning(message);
	        break;
	      case INFO:
	        logger.info(message);
		 }
	 }
}
