package org.mule.extension.internal;

import org.mule.extension.internal.connection.DbConnecionProvider;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(BasicOperations.class)
@ConnectionProviders(DbConnecionProvider.class)
public class BasicConfiguration {


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
