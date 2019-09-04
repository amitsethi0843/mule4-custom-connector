package org.mule.connection;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.connection.ConnectionValidationResult;

public class FileConnectionProvider implements ConnectionProvider<String> {

	@Override
	public String connect() throws ConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void disconnect(String connection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ConnectionValidationResult validate(String connection) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
