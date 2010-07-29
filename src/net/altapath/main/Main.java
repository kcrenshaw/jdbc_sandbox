package net.altapath.main;

import net.altapath.jdbc.ContactsDbSetup;

public class Main {
	
	public static void main(String[] args){
		ContactsDbSetup setup = new ContactsDbSetup();
		setup.CreateContactsDb();
		System.out.println("Success!");
	}

}
