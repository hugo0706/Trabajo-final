import java.io.*;

import java.util.*;
public  class DatosPrograma {
	public static ArrayList<Cliente>clientes=new ArrayList<Cliente>();
	public static ArrayList<Producto>productos=new ArrayList<Producto>();
	public static ArrayList<Venta>ventas=new ArrayList<Venta>();
	//Contiene funciones para crear/eliminar cliente,y añadir/eliminar producto

	public static void crearCliente() {
		Scanner entrada=new Scanner(System.in);
		String dni="";
		String nombre="";
		String correo="";
		String clave="";
		String cp="";
		String ciudad="";
		int credito=0;
		System.out.println("Introduzca su DNI");
		try {
			dni=Integer.toString(entrada.nextInt());
			nombre=entrada.nextLine();//se hace para quemar una linea, si no daria error porque nextInt dejaria una linea de retraso
		}catch(Exception e) {
			System.out.println("Error");
		}
		System.out.println("Introduzca su nombre");
		try {
			
			nombre=entrada.nextLine();
			
		}catch(Exception e) {
			System.out.println("Error");
			}
		System.out.println("Introduzca su correo");
		try {
			correo=entrada.nextLine();
			}catch(Exception e) {
				System.out.println("Error");
			}

		System.out.println("Introduzca su clave");
		try {
			clave=entrada.nextLine();
			}catch(Exception e) {
				System.out.println("Error");
			}
		System.out.println("Introduzca su codigo postal");
		try {
			cp=entrada.nextLine();
			}catch(Exception e) {
				System.out.println("Error");
			}
		System.out.println("Introduzca su ciudad");
		try {
			ciudad=entrada.nextLine();
			}catch(Exception e) {
				System.out.println("Error");
			}
		System.out.println("Introduzca su tarjeta de credito");
		try {
			credito= entrada.nextInt();
			}catch(Exception e) {
				System.out.println("Error");
			}
		Cliente cliente=new Cliente(dni,nombre,correo,clave,cp,ciudad,credito);//Crea nuevo cliente con los datos
		clientes.add(cliente);//Añade a la lista de clientes
			
	}
	public static void retirarProducto(Producto producto) { //Si el producto se encuentra en la lista general, se retira.
		if(productos.contains(producto)) {
			productos.remove(producto);
		}else {System.out.println("Este producto no se encuentra en su lista.");
		}
	}
	public static void añadirProducto(Producto producto) {//Si el producto no se encuentra en la lista general, se añade.
		if(!productos.contains(producto)) {
			productos.add(producto);
			System.out.println("Se ha añadido");
		}else {System.out.println("Este producto ya se encuentra en su lista.");
	}
	}
	public static void retirarCliente(Cliente cliente) { //Si el cliente se encuentra en la lista general, se retira.
		if(clientes.contains(cliente)) {
			clientes.remove(cliente);
		}else {System.out.println("Este cliente no se encuentra en su lista.");
		}
	
}
	public static void añadirCliente(Cliente cliente) { //Si el cliente se encuentra en la lista general, se retira.
		if(!clientes.contains(cliente)) {
			clientes.add(cliente);
		}else {System.out.println("Este cliente se encuentra en su lista.");
		}
	
}
	

	public static void actualizarClientes(ArrayList<Cliente> clientes) {
		try {
			ObjectOutputStream ficheroClientes=new ObjectOutputStream(new FileOutputStream("clientes.txt"));
			ficheroClientes.writeObject(clientes);
			ficheroClientes.close();
		}catch(Exception e){
			System.out.println("Error al transferir a fichero clientes");
			
		}
	}
	public static void actualizarProductos(ArrayList<Producto>productos) {
		try {
			ObjectOutputStream ficheroProductos=new ObjectOutputStream(new FileOutputStream("productos.txt"));
			ficheroProductos.writeObject(productos);
			ficheroProductos.close();
		}catch(Exception e){
			System.out.println("Error al transferir a fichero productos");
			
		}
	}
	public static void actualizarVentas(ArrayList<Venta>ventas) {
		try {
			ObjectOutputStream ficheroVentas=new ObjectOutputStream(new FileOutputStream("ventas.txt"));
			ficheroVentas.writeObject(ventas);
			ficheroVentas.close();
		}catch(Exception e){
			System.out.println("Error al transferir a fichero ventas");
			
		}
	}
	public static ArrayList<Cliente> leerFicheroC() {
		ArrayList<Cliente> clientesGuardados=new ArrayList<Cliente>();
		try {
		ObjectInputStream ficheroClientes=new ObjectInputStream(new FileInputStream("clientes.txt"));
		clientesGuardados=(ArrayList<Cliente>) ficheroClientes.readObject();
		ficheroClientes.close();
		}catch(Exception e) {
			System.out.println("Errror al leer fichero clientes");
		}
		return clientesGuardados;
	}
	public static ArrayList<Producto> leerFicheroP() {
		ArrayList<Producto> productosGuardados=new ArrayList<Producto>();
		try {
		ObjectInputStream ficheroProductos=new ObjectInputStream(new FileInputStream("productos.txt"));
		productosGuardados=(ArrayList<Producto>) ficheroProductos.readObject();
		ficheroProductos.close();
		}catch(Exception e) {
			System.out.println("Errror al leer fichero Productos");
		}
		return productosGuardados;
	}
	public static ArrayList<Venta> leerFicheroV() {
		ArrayList<Venta> ventasGuardadas=new ArrayList<Venta>();
		try {
		ObjectInputStream ficheroVentas=new ObjectInputStream(new FileInputStream("ventas.txt"));
		ventasGuardadas=(ArrayList<Venta>) ficheroVentas.readObject();
		ficheroVentas.close();
		}catch(Exception e) {
			System.out.println("Errror al leer fichero ventas");
		}
		return ventasGuardadas;
	}
}