package org.mule.conf;

import org.mule.connection.DbConnecionProvider;
import org.mule.operation.DbOperations;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.ExclusiveOptionals;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */

//@ExclusiveOptionals(isOneRequired = true)
@Operations(DbOperations.class)
@ConnectionProviders(DbConnecionProvider.class)
public class LoggerConfiguration {

@Parameter
@Optional
private FileConfParams fileConfParams;

//@ParameterGroup(name = "db")
@Parameter
@Optional
private DbConfParams dbConfParams;

public FileConfParams getFileConfParams() {
	return this.fileConfParams;
}

public DbConfParams getDbConfParams() {
	return this.dbConfParams;
}   
}
