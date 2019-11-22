
package com.xncoding.webservice.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CommonService", targetNamespace = "http://model.webservice.xncoding.com/", wsdlLocation = "http://localhost:8092/services/CommonService?wsdl")
public class CommonService_Service
    extends Service
{

    private final static URL COMMONSERVICE_WSDL_LOCATION;
    private final static WebServiceException COMMONSERVICE_EXCEPTION;
    private final static QName COMMONSERVICE_QNAME = new QName("http://model.webservice.xncoding.com/", "CommonService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8092/services/CommonService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        COMMONSERVICE_WSDL_LOCATION = url;
        COMMONSERVICE_EXCEPTION = e;
    }

    public CommonService_Service() {
        super(__getWsdlLocation(), COMMONSERVICE_QNAME);
    }

    public CommonService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), COMMONSERVICE_QNAME, features);
    }

    public CommonService_Service(URL wsdlLocation) {
        super(wsdlLocation, COMMONSERVICE_QNAME);
    }

    public CommonService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, COMMONSERVICE_QNAME, features);
    }

    public CommonService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CommonService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CommonService
     */
    @WebEndpoint(name = "CommonServiceImplPort")
    public CommonService getCommonServiceImplPort() {
        return super.getPort(new QName("http://model.webservice.xncoding.com/", "CommonServiceImplPort"), CommonService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CommonService
     */
    @WebEndpoint(name = "CommonServiceImplPort")
    public CommonService getCommonServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://model.webservice.xncoding.com/", "CommonServiceImplPort"), CommonService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (COMMONSERVICE_EXCEPTION!= null) {
            throw COMMONSERVICE_EXCEPTION;
        }
        return COMMONSERVICE_WSDL_LOCATION;
    }

}
