package Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class ViewUsers {

	public JFrame frame;
	public JTextField tfNombre,tfApellido,tfDireccion,tfDNI,tfFecha;
	public JTextArea taClientes;
	private JLabel lblApellidos;
	private JLabel lblDireccion;
	private JLabel lblDni;
	private JLabel lblFecha;
	public JButton btnAdd, btnDelUser, btnUpdateUser;
	public JPanel panel_1,panel;
	public JTable table;

	public ViewUsers() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 380);
		frame.getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(null, "A\u00F1adir usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(30, 45, 231, 215);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(29, 24, 60, 14);
		panel.add(lblNewLabel);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(100, 24, 110, 20);
		panel.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfApellido = new JTextField();
		tfApellido.setColumns(10);
		tfApellido.setBounds(100, 52, 110, 20);
		panel.add(tfApellido);
		
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(29, 52, 60, 14);
		panel.add(lblApellidos);
		
		tfDireccion = new JTextField();
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(100, 80, 110, 20);
		panel.add(tfDireccion);
		
		lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(29, 80, 60, 14);
		panel.add(lblDireccion);
		
		tfDNI = new JTextField();
		tfDNI.setColumns(10);
		tfDNI.setBounds(100, 111, 110, 20);
		panel.add(tfDNI);
		
		lblDni = new JLabel("DNI");
		lblDni.setBounds(29, 111, 46, 14);
		panel.add(lblDni);
		
		tfFecha = new JTextField();
		tfFecha.setColumns(10);
		tfFecha.setBounds(100, 139, 110, 20);
		panel.add(tfFecha);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(29, 139, 60, 14);
		panel.add(lblFecha);
		
		btnAdd = new JButton("Añadir");
		btnAdd.setBounds(65, 170, 89, 23);
		panel.add(btnAdd);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Tabla clientes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(271, 45, 685, 215);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		taClientes = new JTextArea();
		taClientes.setEditable(false);
		taClientes.setBounds(10, 24, 665, 180);
		panel_1.add(taClientes);
		
		btnDelUser = new JButton("Eliminar usuario");
		btnDelUser.setBounds(340, 287, 150, 30);
		frame.getContentPane().add(btnDelUser);
		
		btnUpdateUser = new JButton("Modificar usuario");
		btnUpdateUser.setBounds(500, 287, 150, 30);
		frame.getContentPane().add(btnUpdateUser);
		
	}
}
