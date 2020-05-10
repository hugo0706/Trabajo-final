import java.util.Scanner; 
import java.io.Serializable;
import java.util.ArrayList;
public class JavaPoP implements Serializable {

	/**
	 * Compara usuarios, comprueba si el usuario y contraseña escritos por cliente son correctos
	 * @param correo String introducido por usuario
	 * @param lista ArrayList de objetos cliente
	 * @param contraseña String introducido por usuario
	 * @return true/false
	 */
    
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
    /**
     * Obtiene el cliente al que pertenece dicho correo
     * @param correo String introducido por usuario
     * @param lista	ArrayList de objetos cliente
     * @return	Objeto CLiente
     */
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
        /**
         * Permite elegir entre iniciar sesion o registrarse.
         * En caso de elegir registro procede a el
         */
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
            	/**
            	 * Comienza proceso de registro
            	 */
                System.out.println("REGISTRO");
                DatosPrograma.crearCliente();
                correcto=true;
            }else {
            	correcto=false;
            	while(!correcto) {
                
                 try {
                    	System.out.println("Elija opcion 1 o 2");
                        decision=entrada.nextInt();
                        quemaLinea=entrada.nextLine();
                        correcto=true;
                    }catch(Exception e) {
                    	
                        quemaLinea = entrada.nextLine();
                        continue;
                    }
                    }
            }
        }
        //FIN DE FASE DE ELECCION REGISTRO/INICIO DE SESION 
    	//FASE DE INICIO DE SESION
    	Cliente elem;
        boolean logged = false;
        String correo = "";
        boolean admin=false;
        do{  
        	/**
        	 * Pide correo y contraseña y comprueba si coinciden con alguna cuenta
        	 * ya registrada. En caso de hacerlo inicia sesion, si no, pide los datos 
        	 * de nuevo.En caso de ser administrador se despliegan sus opciones, en caso
        	 * de ser cliente se despliegan las opciones de cliente.
        	 */
            System.out.println("INICIO DE SESION");
            System.out.print("Correo electrónico: ");
            correo = entrada.nextLine();
            System.out.print("Contraseña: ");
            String contraseña = entrada.nextLine();
            if(correo.equals("admin@javapop.com") || contraseña.equals("admin")){
            	admin=true;
            	while(admin) { 	
	            	Scanner menu = new Scanner(System.in);
	            	System.out.println("----MODO ADMINISTRADOR----");
	            	/**
	            	 * Opciones de modo administrador//////////////////////////////////////////////////////////////////////////
	            	 */
	            	System.out.println("1 Consulta de usuarios \n2 Consulta de productos \n3 Consulta ventas realizadas "
	 	                    + "\nCualquier otra tecla para salir");
	 	            String opcion = menu.nextLine();
	 	            if(opcion.equals("1")) {
	 	            	for(Cliente c:DatosPrograma.clientes) {
	 	            		System.out.println(c.getNombre());
	 	            		
	 	            	}
	 	            	/**
	 	            	 * Opciones de consulta de usuarios
	 	            	 */
	 	            	System.out.println("Introduce el nombre de un cliente para mostrar informacion o 1 para eliminar cliente o 0 para salir ");
	 	            	opcion=entrada.nextLine();
	 	            	/**
	 	            	 * Opcion de eliminar cliente con sus respectivos productos//////////////////////////////////////////////////////////////////////////
	 	            	 */
	 	            	if(opcion.equals("1")) {
	 	            		System.out.println("Introduce el nombre del cliente a eliminar (0 para salir)");
	 	            		opcion=entrada.nextLine();
	 	            		
	 	            		for(int contador=0;contador<DatosPrograma.clientes.size();contador++) {
	 	            			Cliente c=DatosPrograma.clientes.get(contador);
	 	            			if(c.getNombre().equals(opcion)) {
	 	            				for(int i=0;i<DatosPrograma.productos.size();i++) {
	 	            					Producto p=DatosPrograma.productos.get(i);
	 	            					if(p.getDniDueño().equals(c.dni)) {
	 	            						DatosPrograma.productos.remove(p);
	 	            						i=i-1;
	 	            					}
	 	            				}
	 	            				DatosPrograma.retirarCliente(c);
	 	            				contador=contador-1;
	 	            			}
	 	            			
	 	            			
	 	            		}
	 	            	}else if(opcion.equals("0")) {
	         			
	         			}else {
	         				for(Cliente c:DatosPrograma.clientes) {
	         					if(c.getNombre().equals(opcion)) {
	         						System.out.println(c);
	         					}else {
	         						
	         					}
	         				}
	         				
	         			}
	 	         /**
	 	          * Consulta de productos////////////////////////////////////////////////////////////////////////////////////////////////
	 	          */
 	            }else if(opcion.equals("2")) {
 	            	int i=0;
 	            	for(Producto p:DatosPrograma.productos) {
 	            		System.out.println(i+p.getTitulo());
 	            		i++;
 	            	}
 	            	System.out.println("Introduce el numero de un producto para mostrar informacion");
 	            	int numero=entrada.nextInt();
 	            	opcion=entrada.nextLine();
 	            	System.out.println(DatosPrograma.productos.get(numero));
 	            	System.out.println("Para eliminar este producto 1, para salir cualquier otra tecla");
 	            	opcion=entrada.nextLine();
 	            	if(opcion.equals("1")) {
 	            		Producto p=DatosPrograma.productos.get(numero);
 	          
 	            		DatosPrograma.productos.remove(numero);
 	
 	            		for(Cliente c: DatosPrograma.clientes) {
 	            			if(c.dni.equals(p.getDniDueño())) {
 	            				for(Producto pCliente: c.getProductosCliente()) {
 	            					if(pCliente.getTitulo().equals(p.getTitulo())) {
 	            		
 	            						c.retirarProducto(pCliente);
  	            					}
 	            				}
 	            				break;
 	            			}
 	            			break;
 	            		}
 	            	}else {
 	            		
 	            	}
 	            /**
 	             * Consulta de ventas//////////////////////////////////////////////////////////////////////////////////////////////////
 	             */
 	            }else if(opcion.equals("3")) {
 	            	for(Venta v:DatosPrograma.ventas) {
 	            		System.out.println(v);
 	            	
 	            		System.out.println("¿Quiere seleccionar las ventas por fecha? (1 si cualquier otra tecla no");
 	 	            	opcion=entrada.nextLine();
 	 	            	if(opcion.equals("1")) {
 	 	            		System.out.println("Introduce el mes (1-12)");
 	 	            		opcion=entrada.nextLine();
 	 	            		for(Venta venta:DatosPrograma.ventas) {
 	 	            			if(venta.getFechaVenta().getMonthValue()==Integer.parseInt(opcion)) {
 	 	            			
 	 	            				System.out.println(venta);
 	 	            			}
 	 	            		}
 	 	            	}else {
 	 	            	
 	 	            	}
 	 	            			
 	            	
 	            	}
 	            }else {
 	            	break;
 	            	
 	            	
 	            }
 	            
 	            
	           }
            }else {
            	logged = comparaUsuario(correo, DatosPrograma.clientes, contraseña);
            }
	            while (logged){
	                Scanner menu = new Scanner(System.in);
	                
	                elem = checkCliente(correo, DatosPrograma.clientes);
	                System.out.println("----OPCIONES DE USUARIO----");
	                /**
	                 * Opciones del usuario///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	                 */
	                System.out.println("1 para buscar y comprar productos\n2 para consultar tus productos\n3 para convertirse en cliente profesional"
	                    +"\n4 para comprobar notificaciones"+ "\nCualquier otra tecla para salir");
	                String opcion = menu.nextLine();
	                
	                
	                /**
	                 * Busqueda y compra////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	                 */
	                if (opcion.equals("1")){
	                	System.out.println("Introduce las palabras clave del producto a buscar\n(en caso de no querer, pulsa enter");
	                    String palabrasClave = menu.nextLine();
	                    elem.comprarProducto(palabrasClave);
	            
	                /**
	                 * Consulta de productos propios////////////////////////////////////////////////////////////////////////////////////////////////////////
	                 */
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
	                /**
	                 * Hacer profesional/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	                 */
	                else if(opcion.equals("3")){
	                    elem.hacerProfesional();
	                }
	                /**
	                 * Comprobacion de notificaciones//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	                 */
	                else if(opcion.equals("4")){
	                	 elem.comprobarCompra();
	                }
	                
	                
	                    
	                else {
	          
	                    break;
	                }
	            }
        
        
        } while (logged == false && admin==false);
        //Cuando se termina el programa actualizamos los ficheros
       
        DatosPrograma.actualizarProductos(DatosPrograma.productos);
       	DatosPrograma.actualizarClientes(DatosPrograma.clientes);
     	DatosPrograma.actualizarVentas(DatosPrograma.ventas);
        
    }
}
      
    
