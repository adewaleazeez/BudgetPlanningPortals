/**
 * YBUDGET_ACTUALS_REVENUECallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.6  Built on : Jul 30, 2017 (09:08:31 BST)
 */
package mc_style.functions.soap.sap.document.sap_com;


/**
 *  YBUDGET_ACTUALS_REVENUECallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class YBUDGET_ACTUALS_REVENUECallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public YBUDGET_ACTUALS_REVENUECallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public YBUDGET_ACTUALS_REVENUECallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for ybudgetActualsRevenue method
     * override this method for handling normal response from ybudgetActualsRevenue operation
     */
    public void receiveResultybudgetActualsRevenue(
        mc_style.functions.soap.sap.document.sap_com.YBUDGET_ACTUALS_REVENUEStub.YbudgetActualsRevenueResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from ybudgetActualsRevenue operation
     */
    public void receiveErrorybudgetActualsRevenue(java.lang.Exception e) {
    }
}
