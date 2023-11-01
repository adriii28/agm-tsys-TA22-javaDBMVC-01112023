package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import Models.Client;
import SQLConnector.SQLConnect;
import Views.ViewUsers;

public class Controller implements ActionListener {

	private Client client;
	private ViewUsers view;
	private ArrayList<Client> listaClientes;
	private int id;

	public Controller(Client user, ViewUsers view) {
		this.client = user;
		this.view = view;
		this.view.btnAdd.addActionListener(this);
		this.view.btnDelUser.addActionListener(this);
		this.view.btnUpdateUser.addActionListener(this);
	}

	public void initView() {
		view.frame.setTitle("Clientes");
		view.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.frame.getContentPane().setLayout(null);
		view.frame.setVisible(true);
		view.frame.setResizable(false);
		view.frame.setLocationRelativeTo(null);

		showClients();

	}

	public void showClients() {
		listaClientes = SQLConnect.getValues();
		String clientes = "";

		for (Client client : listaClientes) {
			clientes += client.toString() + "\n";
		}
		view.taClientes.setText(clientes);
	}

	public void clearTf() {
		view.tfNombre.setText("");
		view.tfApellido.setText("");
		view.tfDireccion.setText("");
		view.tfDNI.setText("");
		view.tfFecha.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (view.btnAdd.equals(e.getSource())) {
			if (view.btnAdd.getText().equals("Añadir")) {
				buttonAdd();
			} else {
				buttonModify();
			}
		} else if (view.btnDelUser.equals(e.getSource())) {
			buttonDel();
		} else if (view.btnUpdateUser.equals(e.getSource())) {
			buttonUpdate();
		}
	}

	private void buttonModify() {
		if (view.tfNombre.getText().isEmpty() || view.tfApellido.getText().isEmpty()
				|| view.tfDireccion.getText().isEmpty() || view.tfDNI.getText().isEmpty()
				|| view.tfFecha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Rellena todos los campos");
		} else {
			if (view.tfDNI.getText().length() <= 11) {
				client.setId(id);
				client.setNombre(view.tfNombre.getText());
				client.setApellidos(view.tfApellido.getText());
				client.setDireccion(view.tfDireccion.getText());
				client.setDni(Integer.valueOf(view.tfDNI.getText()));
				client.setFecha(view.tfFecha.getText());

				SQLConnect.updateData(client);
				view.btnAdd.setText("Añadir");
				view.panel.setBorder(new TitledBorder(null, "A\u00F1adir usuario", TitledBorder.LEADING,
						TitledBorder.TOP, null, null));
				view.btnDelUser.setEnabled(true);
				view.btnUpdateUser.setEnabled(true);
				clearTf();
				showClients();
				JOptionPane.showMessageDialog(null, "Operacion realizada correctamente");

			}
		}

	}

	private void buttonUpdate() {
		if (listaClientes.size() == 0) {
			JOptionPane.showMessageDialog(null, "No hay clientes para modificar", "Alerta", JOptionPane.ERROR_MESSAGE);
		} else {
			String idStr = JOptionPane.showInputDialog(null, "Introduce el ID de el cliente que deseas modificar",
					"Modificar Cliente", JOptionPane.INFORMATION_MESSAGE);
			try {
				id = Integer.valueOf(idStr);
				boolean exist = false;
				for (Client client : listaClientes) {
					if (client.getId() == id) {
						exist = true;
						editSetTF(id);
					}
				}
				if (!exist) {
					JOptionPane.showMessageDialog(null, "Ningun cliente contiene el ID -> " + id, "Atencion!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					view.btnDelUser.setEnabled(false);
					view.btnUpdateUser.setEnabled(false);
				}
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}

	private void editSetTF(int id) {
		Client c = SQLConnect.getClient(id);
		if (c != null) {
			view.btnAdd.setText("Modificar");
			view.panel.setBorder(
					new TitledBorder(null, "Modificar usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			view.tfNombre.setText(c.getNombre());
			view.tfApellido.setText(c.getApellidos());
			view.tfDireccion.setText(c.getDireccion());
			view.tfDNI.setText(String.valueOf(c.getDni()));
			view.tfFecha.setText(c.getFecha());

		}
	}

	private void buttonDel() {
		if (listaClientes.size() == 0) {
			JOptionPane.showMessageDialog(null, "No hay clientes para poder eliminar", "Alerta",
					JOptionPane.ERROR_MESSAGE);
		} else {
			
			String idStr = JOptionPane.showInputDialog("Introduce el ID de el cliente que deseas eliminar");
			try {
				int id = Integer.valueOf(idStr);
				boolean exist = false;
				for (Client client : listaClientes) {
					if (client.getId() == id) {
						exist = true;
						int resp = JOptionPane.showConfirmDialog(null, "Esta seguro?", "Alerta!",
								JOptionPane.YES_NO_OPTION);
						if (resp == 0) {
							SQLConnect.deleteData(id);
							showClients();
							JOptionPane.showMessageDialog(null, "Operacion realizada correctamente");
						} else {
							JOptionPane.showMessageDialog(null, "Operacion cancelada");
						}
					}
				}

				if (!exist) {
					JOptionPane.showMessageDialog(null, "Ningun cliente contiene el ID -> " + id, "Atencion!",
							JOptionPane.ERROR_MESSAGE);
				}
				
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
	}

	private void buttonAdd() {
		if (view.tfNombre.getText().isEmpty() || view.tfApellido.getText().isEmpty()
				|| view.tfDireccion.getText().isEmpty() || view.tfDNI.getText().isEmpty()
				|| view.tfFecha.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "Rellena todos los campos");

		} else {
			if (view.tfDNI.getText().length() <= 11) {
				client.setNombre(view.tfNombre.getText());
				client.setApellidos(view.tfApellido.getText());
				client.setDireccion(view.tfDireccion.getText());
				client.setDni(Integer.valueOf(view.tfDNI.getText()));
				client.setFecha(view.tfFecha.getText());

				SQLConnect.insertData(client);
				clearTf();
				showClients();
				JOptionPane.showMessageDialog(null, "Operacion realizada correctamente");

			}
		}

	}
}
