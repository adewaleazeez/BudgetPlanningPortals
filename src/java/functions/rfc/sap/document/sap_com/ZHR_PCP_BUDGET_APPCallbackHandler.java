/**
 * ZHR_PCP_BUDGET_APPCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.6  Built on : Jul 30, 2017 (09:08:31 BST)
 */
package functions.rfc.sap.document.sap_com;


/**
 *  ZHR_PCP_BUDGET_APPCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class ZHR_PCP_BUDGET_APPCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public ZHR_PCP_BUDGET_APPCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public ZHR_PCP_BUDGET_APPCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for zHR_PCP_BUDGET_APP method
     * override this method for handling normal response from zHR_PCP_BUDGET_APP operation
     */
    public void receiveResultzHR_PCP_BUDGET_APP(
        functions.rfc.sap.document.sap_com.ZHR_PCP_BUDGET_APPStub.ZHR_PCP_BUDGET_APPResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from zHR_PCP_BUDGET_APP operation
     */
    public void receiveErrorzHR_PCP_BUDGET_APP(java.lang.Exception e) {
    }
}
