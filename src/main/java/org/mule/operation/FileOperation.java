package org.mule.operation;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import org.mule.conf.LoggerConfiguration;
import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.util.LogLevelType;

public class FileOperation {

	private static Logger logger=Logger.getLogger(DbOperations.class.getName());

	 @Parameter
	  private LogLevelType loggerLevel;
	 
	 @MediaType(value = ANY, strict = false)
	 public void fileMesage(@Expression(ExpressionSupport.REQUIRED) String message,@Config LoggerConfiguration conf) {
		 boolean append = true;
         try {
			FileHandler handler = new FileHandler(conf.getFileConfParams().getLogPath()+"/default.log", append);
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
