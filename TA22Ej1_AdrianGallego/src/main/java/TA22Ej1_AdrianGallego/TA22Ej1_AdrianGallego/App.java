package TA22Ej1_AdrianGallego.TA22Ej1_AdrianGallego;

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
