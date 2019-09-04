package org.mule.conf;

import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

public class DbConfParams {
	@DisplayName("Table Name")
    @Parameter
    private String tableName;

    @DisplayName("Transaction reference Column")
    @Parameter
    @Optional
    private String referenceNumberColumn;

    @DisplayName("Message Column")
    @Parameter
    private String messageColumn;

    @DisplayName("Log Level Column")
    @Parameter
    private String logLevelColumn;

    @DisplayName("Date Column")
    @Parameter
    private String dataColumn;


    public String getTableName(){
        return this.tableName;
    }

    public String getReferenceNumberColumn(){
        return this.referenceNumberColumn;
    }

    public String getMessageColumn(){
        return this.messageColumn;
    }

    public String getLogLevelColumn(){
        return this.logLevelColumn;
    }

    public String getDataColumn() {
        return this.dataColumn;
    }
}
