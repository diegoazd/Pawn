package com.paypal;


/*==================================================================
 PayPal Express Check out Call
 ===================================================================
*/


import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ExpressCheckout  extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2722761580200224133L;	
    
	public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {
		HttpSession session = request.getSession();
        PayPal pp = new PayPal();
        /*
        '------------------------------------
        ' The returnURL is the location where buyers return to when a
        ' payment has been succesfully authorized.
        '
        ' This is set to the value entered on the Integration Assistant
        '------------------------------------
        */
        
        String returnURL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/Return?page=review";
        if(pp.getUserActionFlag().equals("true"))
        	returnURL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/Return?page=return";
       
        /*
        '------------------------------------
        ' The cancelURL is the location buyers are sent to when they hit the
        ' cancel button during authorization of payment during the PayPal flow
        '
        ' This is set to the value entered on the Integration Assistant
        '------------------------------------
        */
        String cancelURL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/cancel.jsp";
      

        for (String key : request.getParameterMap().keySet()) {
			   session.setAttribute(key, request.getParameterMap().get(key)[0]);
			}

        //Redirect to check out page for check out mark
        if(!isSet(request.getParameter("Confirm")) && isSet(request.getParameter("checkout"))){

    		if(isSet(request.getParameter("checkout")) || isSet(session.getAttribute("checkout"))) {
    			session.setAttribute("checkout", request.getParameter("checkout"));
    		}
    			
	    	//Assign the Return and Cancel to the Session object for ExpressCheckout Mark
	    	//session.setAttribute("RETURN_URL", returnURL);
	    	//session.setAttribute("CANCEL_URL", cancelURL);
	    	session.setAttribute("EXPRESS_MARK", "ECMark");
	    	
	    	request.setAttribute("PAYMENTREQUEST_0_AMT", request.getParameter("PAYMENTREQUEST_0_AMT"));
	    	//redirect to check out page
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("checkout.jsp");
	    	if (dispatcher != null){
	    		dispatcher.forward(request, response);
	    	}
	    	
        }
        else{
        	Map<String, String> nvp;
        	        	
        	if(isSet(session.getAttribute("EXPRESS_MARK")) && session.getAttribute("EXPRESS_MARK").equals("ECMark")){
	        	if(isSet(request.getParameter("shipping_method"))) {
	        		BigDecimal new_shipping = new BigDecimal(request.getParameter("shipping_method")); //need to change this value, just for testing
	        		BigDecimal shippingamt = new BigDecimal(session.getAttribute("PAYMENTREQUEST_0_SHIPPINGAMT").toString());
	        		BigDecimal paymentAmount = new BigDecimal((String)session.getAttribute("PAYMENTREQUEST_0_AMT"));
	        		if(shippingamt.compareTo(new BigDecimal(0)) > 0){
	        			paymentAmount = paymentAmount.add(new_shipping).subtract(shippingamt) ;
	        		}
	        		session.setAttribute("PAYMENTREQUEST_0_AMT",paymentAmount.toString().replace(".00", ""));
	        		session.setAttribute("PAYMENTREQUEST_0_SHIPPINGAMT",new_shipping.toString());	
	        		session.setAttribute("shippingAmt",new_shipping.toString());
	        	}
	        	returnURL = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/Return?page=return";
	        	 nvp = pp.callMarkExpressCheckout(request, returnURL, cancelURL);        	
	        	
        	} else {
        	
        		 nvp = pp.callShortcutExpressCheckout (request, returnURL, cancelURL);
        	}
	        
	        
			String strAck = nvp.get("ACK").toString().toUpperCase();
	        if(strAck !=null && (strAck.equals("SUCCESS") || strAck.equals("SUCCESSWITHWARNING") ))
	        {
	            session.setAttribute("token", nvp.get("TOKEN").toString());
	            //' Redirect to paypal.com
	            pp.redirectURL(response, nvp.get("TOKEN").toString(),(isSet(session.getAttribute("EXPRESS_MARK")) && session.getAttribute("EXPRESS_MARK").equals("ECMark") || (pp.getUserActionFlag().equalsIgnoreCase("true"))) );
	        }
	        else
	        {
	            // Display a user friendly Error on the page using any of the following error information returned by PayPal
	            String ErrorCode = nvp.get("L_ERRORCODE0").toString();
	            String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
	            String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
	            String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
	            
	            String errorString = "SetExpressCheckout API call failed. "+
	           		"<br>Detailed Error Message: " + ErrorLongMsg +
			        "<br>Short Error Message: " + ErrorShortMsg +
			        "<br>Error Code: " + ErrorCode +
			        "<br>Error Severity Code: " + ErrorSeverityCode;
	            request.setAttribute("error", errorString);
	        	RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
	        	if (dispatcher != null){
	        		dispatcher.forward(request, response);
	        	}
	            
	        }
        }
}

public void doPost(HttpServletRequest request,
                   HttpServletResponse response)
    throws ServletException, IOException {
  doGet(request, response);
}
private boolean isSet(Object value){
	return (value !=null && value.toString().length()!=0);
}
}