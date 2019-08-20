package org.mule.extension.internal;

import org.mule.extension.internal.connection.DbConnecionProvider;
import org.mule.extension.internal.connection.DbConnection;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(BasicOperations.class)
@ConnectionProviders(DbConnecionProvider.class)
public class BasicConfiguration {

    @Parameter
    private boolean storeCorelation=true;

    public boolean isStoreCorelation() {
        return storeCorelation;
    }
}
