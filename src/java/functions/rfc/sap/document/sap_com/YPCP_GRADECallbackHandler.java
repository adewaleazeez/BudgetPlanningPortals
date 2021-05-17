/**
 * YPCP_GRADECallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.6  Built on : Jul 30, 2017 (09:08:31 BST)
 */
package functions.rfc.sap.document.sap_com;


/**
 *  YPCP_GRADECallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class YPCP_GRADECallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public YPCP_GRADECallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public YPCP_GRADECallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for yPCP_GRADE method
     * override this method for handling normal response from yPCP_GRADE operation
     */
    public void receiveResultyPCP_GRADE(
        functions.rfc.sap.document.sap_com.YPCP_GRADEStub.YPCP_GRADEResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from yPCP_GRADE operation
     */
    public void receiveErroryPCP_GRADE(java.lang.Exception e) {
    }
}
