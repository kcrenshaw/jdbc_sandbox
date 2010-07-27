package net.altapath.main;

import net.altapath.jdbc.ContactDao;

public class Main {
	
	public static void main(String[] args){
		ContactDao dao = new ContactDao();
		dao.createContact(null);
		System.out.println("Success!");
	}

}
