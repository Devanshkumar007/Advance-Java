package com.lpu.algo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
//@Scope(value="singleton") // by default the scope is singleton meaning spring will create one object of everything (DEFAULT)
//@Scope(value="prototype") // it means create new object every time 
//@Scope(value="request") // for every request spring will create new object for every request
//@Scope(value="session") //for every user session create new object
//@Scope(value="application") //one object for whole application (nearly same as singleton)/
@Scope(value="")
public class MyService {

	
	
	//CONSTRUCTOR INJECTION 
	//@Autowired
	public MyService(PasswordHasher hasher1 ,PasswordHasher hasher2) {
		myNextPasswordHasher = hasher1;
		mySimplePasswordHasher = hasher2;
	}
	
	public MyService() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	
	
	// SETTER INJECTION 
	public PasswordHasher getMyNextPasswordHasher() {
		return myNextPasswordHasher;
	}

	//@Autowired
	public void setMyNextPasswordHasher(PasswordHasher myNextPasswordHasher) {
		this.myNextPasswordHasher = myNextPasswordHasher;
	}

	public PasswordHasher getMySimplePasswordHasher() {
		return mySimplePasswordHasher;
	}

	//@Autowired
	public void setMySimplePasswordHasher(PasswordHasher mySimplePasswordHasher) {
		this.mySimplePasswordHasher = mySimplePasswordHasher;
	}



//Property INJECTION 
	@Autowired
	PasswordHasher myNextPasswordHasher;
	
	@Autowired
	PasswordHasher mySimplePasswordHasher;


	
	public void show() {
		System.out.println("Hashers : "+myNextPasswordHasher +" and "+mySimplePasswordHasher);
		//in this both of them are assigned the object of mynextpasswordhasher bcz that is the primary bean for passwordHAsher ;
		
	}
}
