import java.io.*;
import java.util.*;
/**
 * Esta clase pretende albergar los metodos que tienen relacion con la
 * transferencia y lectura de datos, arrays generales del programa y creacion
 * de clientes.
 * 
 * @author Javier Carrizosa Bermejo
 * @author Hugo García Calvo
 *
 */
public  class DatosPrograma implements Serializable {
	public static ArrayList<Cliente>clientes=new ArrayList<Cliente>();
	public static ArrayList<Producto>productos=new ArrayList<Producto>();
	public static ArrayList<Venta>ventas=new ArrayList<Venta>();
	
	/**
	 * Este metodo permite crear un nuevo cliente, añadiendolo al array de clientes
	 */

	public static void crearCliente() {
		Scanner entrada=new Scanner(System.in);
		String dni="";
		String nombre="";
		String correo="";
		String clave="";
		String cp="";
		String ciudad="";
		int credito=0;
		boolean correcto=false;
                boolean cifrasDNI = false;
		while(!correcto) {
			System.out.println("Introduzca su DNI");
                        while (!cifrasDNI){ //Sistema de seguridad para que no se meta un dni incorrecto
			try {   
                                System.out.println("Introduzca la parte numerica (8 números)");
				dni=Integer.toString(entrada.nextInt());
                                int conversion = Integer.parseInt(dni);
                                if (dni.length() == 8){
                                    System.out.println("Introduce letra");
                                	String letraDNI = entrada.nextLine();
                                    dni += letraDNI;
                                    cifrasDNI = true;
                                    
                                }
				nombre=entrada.nextLine();//se hace para quemar una linea, si no daria error porque nextInt dejaria una linea de retraso
			}catch(Exception e) {
				System.out.println("Error");
				nombre=entrada.nextLine();
				continue;
			}
                        }
			System.out.println("Introduzca su nombre");
			try {
				
				nombre=entrada.nextLine();
				
			}catch(Exception e) {
				
				System.out.println("Error");
				continue;
			}
			System.out.println("Introduzca su correo");
			try {
				correo=entrada.nextLine();
				}catch(Exception e) {
					System.out.println("Error");
					continue;
				}
	
			System.out.println("Introduzca su clave");
			try {
				clave=entrada.nextLine();
				}catch(Exception e) {
					System.out.println("Error");
					continue;
				}
			
			try {
			    correcto=false;
				while(!correcto) {
					System.out.println("Introduzca su codigo postal");
					cp=entrada.nextLine();
					if(cp.length()<3) {
						System.out.println("Introduce al menos 3 numeros");
					}else {
						correcto=true;
					}
				}
				}
				catch(Exception e) {
					System.out.println("Error");
					continue;
				}
			System.out.println("Introduzca su ciudad");
			try {
				ciudad=entrada.nextLine();
				}catch(Exception e) {
					System.out.println("Error");
					continue;
				}
			System.out.println("Introduzca su tarjeta de credito");
			try {
				credito= entrada.nextInt();
				correcto=true;
				}catch(Exception e) {
					System.out.println("Error");
					continue;
				}
		}
		
		Cliente cliente=new Cliente(dni,nombre,correo,clave,cp,ciudad,credito);//Crea nuevo cliente con los datos
		clientes.add(cliente);//Añade a la lista de clientes
		System.out.println("Cuenta "+nombre+" creada correctamente.");
	}		
	
	
	/**
	 * Este metodo retira un producto del arraylist general de productos
	 * @param producto objeto de clase Producto
	 */
	public static void retirarProducto(Producto producto) { 
		if(productos.contains(producto)) {
			productos.remove(producto);
		}else {System.out.println("Este producto no se encuentra en su lista.");
		}
	}
	
	/**
	 * Este metodo permite añadir un producto al arraylist general de productos
	 * @param producto objeto de clase Producto
	 */
	public static void añadirProducto(Producto producto) {
		if(!productos.contains(producto)) {
			productos.add(producto);
			System.out.println("Se ha añadido");
		}else {System.out.println("Este producto ya se encuentra en su lista.");
	}
	}
	/**
	 * Este metodo permite retirar un cliente al arraylist general de clientes
	 * @param producto objeto de clase Cliente
	 */
	public static void retirarCliente(Cliente cliente) {
		if(clientes.contains(cliente)) {
			clientes.remove(cliente);
		}else {System.out.println("Este cliente no se encuentra en su lista.");
		}
	}
	/**
	 * Este metodo permite añadir un cliente al arraylist general de clientes
	 * @param producto objeto de clase Cliente
	 */
	public static void añadirCliente(Cliente cliente) { 
		if(!clientes.contains(cliente)) {
			clientes.add(cliente);
		}else {System.out.println("Este cliente se encuentra en su lista.");
		}
}
	
	/**
	 * Este método permite actualizar los datos del fichero de clientes con 
	 * el arraylist general de los clientes.
	 * 
	 */
	public static void actualizarClientes(ArrayList<Cliente> clientes) {
		try {
			FileOutputStream fos=new FileOutputStream("clientes.dat");
			ObjectOutputStream ficheroClientes=new ObjectOutputStream(fos);
			ficheroClientes.writeObject(clientes);
			ficheroClientes.close();
			fos.close();
		}catch(Exception e){
			System.out.println("Error al transferir a fichero clientes"+e.toString());
			
			
		}
	}
	/**
	 * Este método permite actualizar los datos del fichero de productos con 
	 * el arraylist general de los productos.
	 * 
	 */
	public static void actualizarProductos(ArrayList<Producto>productos) {
		try {
			ObjectOutputStream ficheroProductos=new ObjectOutputStream(new FileOutputStream("productos.txt"));
	
				ficheroProductos.writeObject(productos);
			
			ficheroProductos.close();
		}catch(Exception e){
			System.out.println("Error al transferir a fichero productos");
			
		}
	
		
	}
	/**
	 * Este método permite actualizar los datos del fichero de ventas con 
	 * el arraylist general de las ventas.
	 * 
	 */
	public static void actualizarVentas(ArrayList<Venta>ventas) {
		try {
			ObjectOutputStream ficheroVentas=new ObjectOutputStream(new FileOutputStream("ventas.txt"));
			ficheroVentas.writeObject(ventas);
		
			ficheroVentas.close();
		}catch(Exception e){
			System.out.println("Error al transferir a fichero ventas");
			
		}
	}
	/**
	 * Este método permite leer los datos del fichero de clientes y los retorna 
	 * en forma de arrayList.
	 * 
	 */
	public static ArrayList<Cliente> leerFicheroC() {
		ArrayList<Cliente> clientesGuardados=new ArrayList<Cliente>();
		try {
		FileInputStream fis=new FileInputStream("clientes.dat");
		ObjectInputStream ficheroClientes=new ObjectInputStream(fis);
		clientesGuardados=(ArrayList<Cliente>) ficheroClientes.readObject();
		
		ficheroClientes.close();
		fis.close();
	
		}catch(Exception e) {
			System.out.println("Errror al leer fichero clientes");
		}
		return clientesGuardados;
	}
	/**
	 * Este método permite leer los datos del fichero de productos y los retorna 
	 * en forma de arrayList.
	 * 
	 */
	public static ArrayList<Producto> leerFicheroP() {
		ArrayList<Producto> productosGuardados=new ArrayList<Producto>();
		try {
		FileInputStream fis=new FileInputStream("productos.txt");
		ObjectInputStream ficheroProductos=new ObjectInputStream(fis);
		productosGuardados=(ArrayList<Producto>) ficheroProductos.readObject();
		ficheroProductos.close();
		fis.close();
		}catch(Exception e) {
			System.out.println("Errror al leer fichero Productos");
		}
		return productosGuardados;
	}
	/**
	 * Este método permite leer los datos del fichero de ventas y los retorna 
	 * en forma de arrayList.
	 * 
	 */
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
