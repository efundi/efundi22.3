
package za.ac.nwu.wsdl.courseoffering;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 3.0.2
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "PermissionDeniedException", targetNamespace = "http://nwu.ac.za/wsdl/CourseOffering")
public class PermissionDeniedException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private PermissionDeniedException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public PermissionDeniedException_Exception(String message, PermissionDeniedException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public PermissionDeniedException_Exception(String message, PermissionDeniedException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: za.ac.nwu.wsdl.courseoffering.PermissionDeniedException
     */
    public PermissionDeniedException getFaultInfo() {
        return faultInfo;
    }

}
