package com.rest.AOPBackendApp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

@Path("/AOP")
public class CrudAOP {
	public CrudAOP(){
		
	}
	private List<AOPBean> ArrListAOPBean;

	// This method is called if JSON is request - Modified this comment yoohoooo
	@POST
	@Path("/persistAOPOrder")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String persistAOPOrder(List<AOPBean> ArrListAOPBean) {
		populatePricingDictionary();


		for (AOPBean aopBean : ArrListAOPBean) {
			AOPEntity aopEntity = new AOPEntity(aopBean.getUserName(),
					aopBean.getUserPhone(), aopBean.getItemName(),
					aopBean.getItemQuantity());
			ObjectifyService.ofy().save().entity(aopEntity).now();
		}
		return "Success Persisting AOP Order";
	}

	private void populatePricingDictionary() {
		AOPPricingEntity aopPricingEntity = new AOPPricingEntity("Sesame Oil",
				"120");
		ObjectifyService.ofy().save().entity(aopPricingEntity).now();
		aopPricingEntity = new AOPPricingEntity("GroundNut Oil",
				"180");
		ObjectifyService.ofy().save().entity(aopPricingEntity).now();
	}

	@POST
	@Path("/queryAOPOrders")
	@Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_JSON)
	public List<AOPBean> getAOPOrders() {
		System.out.println("Entered getAOPOrders");
		List<AOPBean> aopOrderBeanList = new ArrayList<AOPBean>();
		Objectify objectify = ObjectifyService.ofy();
		// Create a OQuery for Course class without setting any filter
		// Query<AOPEntity> q = objectify.load().type(AOPEntity.class);
		Query<AOPEntity> q = objectify.load().type(AOPEntity.class)
				.filter("userName =", "peter");
		System.out.println("Count=----------------------------------------->"
				+ q.count());
		for (AOPEntity aopEntity : q) {
			AOPBean aopOrderBean = new AOPBean();
			aopOrderBean.setUserName(aopEntity.userName);
			aopOrderBean.setUserPhone(aopEntity.userPhone);
			aopOrderBean.setItemName(aopEntity.itemName);
			aopOrderBean.setOrderDate(aopEntity.orderDate);
			aopOrderBean.setItemQuantity(aopEntity.itemQuantity);
			aopOrderBeanList.add(aopOrderBean);
		}
		return aopOrderBeanList;
	}
}
