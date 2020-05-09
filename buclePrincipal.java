import java.util.Scanner; 
import java.util.ArrayList;
public class BuclePrincipal {

    
    
    public static Cliente comparaCorreos(String correo, ArrayList<Cliente> lista){
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
    
    
    public static boolean enLista(String correo, ArrayList<Cliente> lista){
        boolean dentro = false;
         for (Cliente k: lista){
            String correoComparado = k.getCorreo();
            dentro = correo.equals(correoComparado);
            if (dentro){
                System.out.println("Correo aceptado");
                break;
            }
        }
        if (dentro == false){
            System.out.println("Correo o contraseña incorrecta");
        }
        return dentro;
    }

    public static ArrayList<Cliente> cargarClientes(){ //Intenta cargar el array de clientes desde fichero, en caso de no existir crea uno vacio
    	try {
    		return DatosPrograma.leerFicheroC(); 
    	}catch(Exception e) {
    		return new ArrayList<Cliente>();
    	}
    
    }
    public static ArrayList<Producto> cargarProductos(){ //Intenta cargar el array de productos desde fichero, en caso de no existir crea uno vacio
    	try {
    		return DatosPrograma.leerFicheroP(); 
    	}catch(Exception e) {
    		return new ArrayList<Producto>();
    	}
    
    }
    public static ArrayList<Venta> cargarVentas(){ //Intenta cargar el array de ventas desde fichero, en caso de no existir crea uno vacio
    	try {
    		return DatosPrograma.leerFicheroV(); 
    	}catch(Exception e) {
    		return new ArrayList<Venta>();
    	}
    
    }
    
    public static void main(String[] args) {
        //FASE DE CARGA DE INFORMACION DESDE FICHERO
    	DatosPrograma.productos= cargarProductos();
    	DatosPrograma.clientes=cargarClientes();
    	DatosPrograma.ventas=cargarVentas();
    	//FIN DE FASE DE CARGA DE INFORMACION DESDE FICHERO
    	DatosPrograma.crearCliente();
    	
    	//FASE DE INICIO DE SESION
    	Cliente elem;
        boolean logged = false;
        String correo = "";
        
        do{    
            
            Scanner entrada = new Scanner(System.in); 
            System.out.print("Correo electrónico: ");
            correo = entrada.nextLine();
            logged = enLista(correo, DatosPrograma.clientes);
            
            while (logged){
                System.out.println("1 para buscar un producto\n2 para vender un nuevo producto:"
                    + "\n3 para dar de baja un producto\n4 para convertirse en cliente profesional"
                    + "\5 para salir");
            
                Scanner menu = new Scanner(System.in);
                String opcion = menu.nextLine();
                elem = comparaCorreos(correo, DatosPrograma.clientes);
        
                if (opcion.equals("1")){
            

                break;
                }
                else if(opcion.equals("2")){
                    break;
                }

                else if(opcion.equals("3")){
                    break;
                }

                else if(opcion.equals("4")){
                    break;
                }

                else {
                	
                    break;
                }
                }
        } while (logged == false);
        //Cuando se termina el programa actualizamos los ficheros
        DatosPrograma.actualizarProductos(DatosPrograma.productos);
       	DatosPrograma.actualizarClientes(DatosPrograma.clientes);
     	DatosPrograma.actualizarVentas(DatosPrograma.ventas);
        
        
    }       
    
}