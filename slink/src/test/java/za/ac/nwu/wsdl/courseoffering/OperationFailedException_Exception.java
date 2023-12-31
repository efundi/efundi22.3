
package za.ac.nwu.wsdl.courseoffering;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.2
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "OperationFailedException", targetNamespace = "http://nwu.ac.za/wsdl/CourseOffering")
public class OperationFailedException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private OperationFailedException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public OperationFailedException_Exception(String message, OperationFailedException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public OperationFailedException_Exception(String message, OperationFailedException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: za.ac.nwu.wsdl.courseoffering.OperationFailedException
     */
    public OperationFailedException getFaultInfo() {
        return faultInfo;
    }

}
