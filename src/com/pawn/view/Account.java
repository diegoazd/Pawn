package com.pawn.view;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.pawn.bean.ComplexResponse;
import com.pawn.bean.TestBean;
import com.pawn.dao.UserDAO;
import com.pawn.model.User;
import com.pawn.utils.DBConnector;
import com.pawn.utils.MySQLConfig;
import com.pawn.utils.MySQLJDBC;

@Path("/account")
public class Account {
	
    @Path("/testpost")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public ComplexResponse testPost(TestBean report){
	    ComplexResponse cr= new ComplexResponse();
        cr.setCode(200);
        cr.setMessage("mensaje");
        return cr;
    }
    	@Path("/test")
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
	    public ComplexResponse testGet(){	 
	    ComplexResponse cr= new ComplexResponse();
	        cr.setCode(200);
	        cr.setMessage("mensaje");
	        return cr;
	    }
    	
     	@Path("/create")
	    @POST
	    @Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
     	public ComplexResponse create(User user){
     	  UserDAO userDAO = new UserDAO(new MySQLJDBC(new MySQLConfig()));
     	  String apiToken = userDAO.registerUser(user.getEmail(), user.getPassword());
     	  ComplexResponse cr= new ComplexResponse();
	        cr.setCode(201);
	        cr.setMessage("mensaje");
	        cr.setSingle(apiToken);
	        return cr;
     	}
	 
	 
}
