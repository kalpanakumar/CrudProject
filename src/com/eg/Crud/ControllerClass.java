package com.eg.Crud;

import java.util.ArrayList;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.eg.Crud.PMF;

@Controller
public class ControllerClass {
	@RequestMapping("/welcome")
	public ModelAndView mehod() {
		ModelAndView model = new ModelAndView("Display");
		PersistenceManager pm = PMF.get().getPersistenceManager();
	/*	Query q = pm.newQuery(Data.class);
		List<Data> l1 = (List<Data>) q.execute();
		 
		for(int i = 0; i < l1.size(); i++) {
            System.out.println(l1.get(i).getName());
        }
		List<String> details = new ArrayList<>();

		for(int i = 0; i < l1.size(); i++) {
			details.add(l1.get(i).getName());
        }
		model.addObject("customerList", details);
	*/	
		return model;
	}

	@RequestMapping("/AddDetails")
	public ModelAndView addValue(@RequestParam("name") String Name, @RequestParam("emailaddress") String EmailAddress) {
		Data obj = new Data();
		obj.setEmailaddress(EmailAddress);
		obj.setName(Name);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ModelAndView model = new ModelAndView("Display");
		model.addObject("OBJ", obj);
		model.addObject("msg", "The data has been saved");
		pm.makePersistent(obj);
		return model;
	}

	@RequestMapping("/FetchDetails")
	public ModelAndView fetchValue(@RequestParam("name") String Name) {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		ModelAndView model = new ModelAndView("Display");

		Query q = pm.newQuery(Data.class, "name== '" + Name + "'");
		List<Data> l1 = (List<Data>) q.execute(Name);

		if (!l1.isEmpty()) {

			for (Data obj : l1) {
				model.addObject("msg1",
						"The name is " + obj.getName() + " The email address is " + obj.getEmailaddress());
			}

		}
		return model;

	}

	@RequestMapping("/DeleteDetails")
	public ModelAndView DeleteValue(@RequestParam("name") String Name) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ModelAndView model = new ModelAndView("Display");

		Query q = pm.newQuery(Data.class, "name== '" + Name + "'");
		List<Data> l1 = (List<Data>) q.execute(Name);

		if (!l1.isEmpty()) {

			for (Data obj : l1) {
				pm.deletePersistent(obj);
				model.addObject("msg2", "It has been deleted");
			}
		}
		return model;
	}

	@RequestMapping("/ListDetails")
	
	public ModelAndView ListValue() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ModelAndView model = new ModelAndView("Display");

		Query q = pm.newQuery(Data.class);
		List<Data> l1 = (List<Data>) q.execute();
		 
		for(int i = 0; i < l1.size(); i++) {
            System.out.println(l1.get(i).getName());
        }
		List<String> details = new ArrayList<>();

		for(int i = 0; i < l1.size(); i++) {
			details.add(l1.get(i).getName());
        }
		model.addObject("cus", l1);
		model.addObject("customerList", details);
		return model;
	} 

	@RequestMapping("/Update")
	public ModelAndView method2(@RequestParam("name") String Name, @RequestParam("Newname") String NewName,
			@RequestParam("Newemailaddress") String NewEmail) {
		ModelAndView model = new ModelAndView("Display");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Data.class, "name== '" + Name + "'");
		List<Data> l1 = (List<Data>) q.execute(Name);

		if (!l1.isEmpty()) {

			for (Data obj : l1) {
				obj.setEmailaddress(NewEmail);
				obj.setName(NewName);
				pm.makePersistent(obj);
				model.addObject("msg3", "update sucess");
			}
		}
		return model;
	}
}
