package TA22Ej2_AdrianGallego.TA22Ej2_AdrianGallego;

import Controllers.Controller;
import Models.Client;
import Views.ViewUsers;

public class App {
	public static void main(String[] args) {
		Client u = new Client();
		ViewUsers vu = new ViewUsers();
		Controller c = new Controller(u, vu);
		c.initView();
	}
}
