package Controllers;
import java.util.ArrayList;
import java.util.Map;
import Database.*;
import Datatypes.*;
import Controllers.*;
import SystemBehavioralPatterns.*;
import Database.*;
import Datatypes.*;
import Controllers.*;
import Encapsulatedbehaviors.*;

public class usercontroller {
	private static usercontroller singletonObj;

	private usercontroller() {
	}
	
	public static usercontroller getInstance() {
		if( singletonObj==null) {
			singletonObj=new usercontroller();
		}
		return singletonObj;
	}
	public boolean login(userDatabase db,user u) {
		boolean found = db.checkExistence(u);
		if(found==true) {
			String m=u.getUse_name();
			System.out.println("welcome back"+" "+m);
			return true;
		}
		else {
			System.out.println("password or username is invalid");
			return false;
		}
	}
	
	public boolean register(userDatabase db,user u) {
		Boolean x=db.checkExistence(u);
		if(x==true) {
			System.out.println("user is already register");
			return false;
			
		}
		else {
			db.addToDatabase(u);
			System.out.println("Registration completed");
			return true;
		}
		
	}
	
     public user getuser(userDatabase db,user u) {
    	 return db.getuser(u);
     }
     
     public void modifyUser(user c,userDatabase db) {
    	 int ind=db.getIndexOfObjectInFile(c);
    	 db.ModifyObjectInDatabase(ind, c);
     }
	
     public store checkStoreownerAccessToHisStores(String storename,user u) {
    	    ArrayList<store>s=u.getStoresUserowened();
    	    String n=u.getUse_name();
    	    for(store currs:s) {
    	    	if(currs.getStoreName().equals(storename)&&n.equals(currs.getStoreOwnername())) {
    	    		return currs;
    	    }
    	    }
    	    return null;
    	    
    	 
     }
     
 	
     public store checkcollaboratorAccessToHisStores(String storename,user u) {
    	    ArrayList<store>s=u.getStoresUserowened();
    	    String n=u.getUse_name();
    	    for(store currs:s) {
    	    	if(currs.getStoreName().equals(storename)) {
    	    		ArrayList<user>us=new ArrayList<user>();
    	    		for(user curr:us) {
    	    			if(n.equals(curr.getUse_name())) {
    	    				return currs;
    	    			}
    	    		}
    	    }
    	    }
    	    return null;
    	    
    	 
     }
     public boolean addcollaboratorsTOStore(String username,store s,userDatabase udb,storeDatabase sdb) {
		 user u=new user();
		 u.setUse_name(username);
		 if(udb.checkExistence(u)) {
			 s.addStoreCollaborators(u);
			 sdb.replaceObject(s);
			 return true;
		 }
		return false;
	 }
	 
	

}
