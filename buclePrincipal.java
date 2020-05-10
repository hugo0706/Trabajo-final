import java.util.Scanner; 
import java.io.Serializable;
import java.util.ArrayList;
public class BuclePrincipal implements Serializable {

	
    
    public static boolean comparaUsuario(String correo, ArrayList<Cliente> lista, String contraseña){
        boolean dentro = false;
         for (Cliente k: lista){
            String correoComparado = k.getCorreo();
            dentro = correo.equals(correoComparado);
            if (dentro){
                if (!k.getClave().equals(contraseña)){
                    dentro = false;
                }
                
                
                break;
            }
        }
        if (dentro == false){
            System.out.println("Correo o contraseña incorrecta");
        }
        return dentro;
    }
    
    public static Cliente checkCliente(String correo, ArrayList<Cliente> lista){
        boolean coincide = false;
        Cliente elem = new Cliente();
        for (Cliente k: lista){
            String correoComparado = k.getCorreo();
            coincide = correo.equals(correoComparado);
            if (coincide){
                elem = k;
                return elem;
            }
            
        }
        return elem;
        
    }
/**
 * Este metodo Intenta cargar el array de clientes desde fichero, en caso de no existir crea uno vacio y lo retorna.
 * 
 */
    public static ArrayList<Cliente> cargarClientes(){ 
    	try {
    		return DatosPrograma.leerFicheroC(); 
    	}catch(Exception e) {
    		System.out.println("Error al cargar clientes");
    		return new ArrayList<Cliente>();
    		
    	}
    
    }
    /**
     * Este metodo Intenta cargar el array de productos desde fichero, en caso de no existir crea uno vacio y lo retorna.
     * 
     */
    public static ArrayList<Producto> cargarProductos(){ 
    	try {
    		return DatosPrograma.leerFicheroP(); 
    	}catch(Exception e) {
    		return new ArrayList<Producto>();
    	}
    
    }
    /**
     * Este metodo Intenta cargar el array de ventas	 desde fichero, en caso de no existir crea uno vacio y lo retorna.
     * 
     */
    public static ArrayList<Venta> cargarVentas(){ 
    	try {
    		return DatosPrograma.leerFicheroV(); 
    	}catch(Exception e) {
    		return new ArrayList<Venta>();
    	}
    
    }
    
    public static void main(String[] args) {
        //FASE DE CARGA DE INFORMACION DESDE FICHERO    	
    	DatosPrograma.productos= cargarProductos();
    	DatosPrograma.clientes = cargarClientes();
    	DatosPrograma.ventas = cargarVentas();
    	
    	//FIN DE FASE DE CARGA DE INFORMACION DESDE FICHERO
    	//FASE DE ELECCION REGISTRO/INICIO DE SESION
        Scanner entrada = new Scanner(System.in); 
        System.out.println("Pulsa enter para comenzar.");
        String quemaLinea = entrada.nextLine();
        System.out.println("¿Desea iniciar sesion(1) o registrarse(2)");
        int decision=0;
        boolean correcto=false;
        while(!correcto) {
            try {
                decision=entrada.nextInt();
                quemaLinea=entrada.nextLine();
                correcto=true;
            }catch(Exception e) {
                quemaLinea = entrada.nextLine();
                continue;
            }
            }
        correcto=false;
        while(!correcto) {
            if(decision==1) {
                
                correcto=true;
            }else if(decision==2) {
                System.out.println("REGISTRO");
                DatosPrograma.crearCliente();
                correcto=true;
            }else {
                System.out.println("Elija opcion 1 o 2");
            }
        }
        //FIN DE FASE DE ELECCION REGISTRO/INICIO DE SESION 
    	//FASE DE INICIO DE SESION
    	Cliente elem;
        boolean logged = false;
        String correo = "";
        
        do{  
        	
            System.out.println("INICIO DE SESION");
            System.out.print("Correo electrónico: ");
            correo = entrada.nextLine();
            System.out.print("Contraseña: ");
            String contraseña = entrada.nextLine();
            logged = comparaUsuario(correo, DatosPrograma.clientes, contraseña);
            
            while (logged){
                Scanner menu = new Scanner(System.in);
                
                elem = checkCliente(correo, DatosPrograma.clientes);
                System.out.println(elem.getProductosCliente());
                System.out.println(DatosPrograma.productos);
                System.out.println("1 para buscar y comprar productos\n2 para consultar tus productos\n3 para convertirse en cliente profesional"
                    +"\n4 para comprobar notificaciones"+ "\nCualquier otra tecla para salir");
                String opcion = menu.nextLine();
                
                
        
                if (opcion.equals("1")){
                	System.out.println("Introduce las palabras clave del producto a buscar\n(en caso de no querer, pulsa enter");
                    String palabrasClave = menu.nextLine();
                    elem.comprarProducto(palabrasClave);
            

                }
                else if(opcion.equals("2")){
                    
                	ArrayList<Producto> tusProductos = elem.getProductosCliente();
                
                    System.out.print("1  para dar de alta un nuevo producto"
                            + "\n 2 para dar de baja un producto\n 3 para hacer urgente un producto ");
                    String elegir = menu.nextLine();
                    
                    
                    switch (elegir){
                        case "1": 
                            elem.añadirProducto(elem.crearProducto());
                            break;
                        case "2":
                        	if(tusProductos==null) {
                            	System.out.println("No tiene productos todavia");
                            }else {
                            	int i=0; 
                            	System.out.println("Estos son sus productos: ");
                               	 for (Producto s:tusProductos){
                                         
                               		 System.out.println(i+" "+s.getTitulo());
                               		 i++;  
                               	}
                            }
                          
                            System.out.println("Elija mediante un número el producto a eliminar, la primera posicion se considera 0, la segunda 1...");
                            int posicionEliminar = menu.nextInt();
                            
                            if (posicionEliminar <= tusProductos.size() || 0<=posicionEliminar){
                            	System.out.println("Se ha eliminado "+tusProductos.get(posicionEliminar).getTitulo());
                            	elem.retirarProducto(tusProductos.get(posicionEliminar));
                                
                                }
                            else {
                            	 System.out.print("No se ha encontrado el producto, introduzca el número de nuevo");
                            }
                            break;
                        case "3":
                            elem.hacerUrgente();
                            
                    }
                    
                }
                
                else if(opcion.equals("3")){
                    elem.hacerProfesional();
                }
                
                else if(opcion.equals("4")){
                	 elem.comprobarCompra();
                }
                
                
                    
                else {
                    DatosPrograma.actualizarProductos(DatosPrograma.productos);
                   	DatosPrograma.actualizarClientes(DatosPrograma.clientes);
                 	DatosPrograma.actualizarVentas(DatosPrograma.ventas);
                    break;
                }
        
        }
        } while (logged == false);
        //Cuando se termina el programa actualizamos los ficheros
    
        
    }
}
      
    
