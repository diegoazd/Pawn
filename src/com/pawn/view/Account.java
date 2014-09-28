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

@Path("/anypath")
public class Account {

	@Path("/testpost")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComplexResponse testPost(TestBean report) {
		ComplexResponse cr = new ComplexResponse();
		cr.setCode(200);
		cr.setMessage("mensaje");
		return cr;
	}

	@Path("/test/{hi}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ComplexResponse testGet(@PathParam("hi")String salute) {
		ComplexResponse cr = new ComplexResponse();
		cr.setCode(200);
		cr.setMessage(salute);
		return cr;
	}
}
