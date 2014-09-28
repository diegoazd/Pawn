package com.pawn.utils;

import com.pawn.bean.ComplexResponse;

public class GenerateResponse {
	  	  
	  public static ComplexResponse generateResponse(int status, 
			  String message, Object single) {
		ComplexResponse cr= new ComplexResponse();
		cr.setCode(201);
        cr.setMessage(message);
        cr.setSingle(single);  
        return cr;
	  }
	  
}
