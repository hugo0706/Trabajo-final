import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
public class Cliente implements Serializable {
	public String dni;
	public String nombre;
	public String correo;
	public String clave;
	public String cp;
	public String ciudad;
	public int credito;
	public static ArrayList<Producto> productosCliente; //Arraylist con los productos propios de cada cliente
	//Atributos de profesional
	private String descripcion;
	private int apertura;
	private int cierre;
	private String telefono;
	private String web;
	
	public  Producto crearProducto() {
		Scanner entrada=new Scanner(System.in);
		System.out.println("Introduce titulo");
		String titulo=entrada.nextLine();
		System.out.println("Introduce categoria");
		String categoria=entrada.nextLine();
		System.out.println("Introduce estado");
		String estado=entrada.nextLine();
		System.out.println("Introduce descripcion");
		String descripcion=entrada.nextLine();
		System.out.println("Introduce precio (double)");
		double precio=entrada.nextDouble();
		return new Producto(titulo,categoria,estado,descripcion,precio,this.cp);
	}
	public void añadirProducto(Producto producto) {//Si el producto no se encuentra en la lista personal del cliente, se añade.
		if(!productosCliente.contains(producto)) {
			producto.setCp(this.cp);
			productosCliente.add(producto);
			DatosPrograma.añadirProducto(producto);//Añade producto a la lista general de productos
			
		}else {System.out.println("Este producto ya se encuentra en su lista.");
	}
	}
	public static void retirarProducto(Producto producto) { //Si el producto se encuentra en la lista personal del cliente, se retira.
		if(productosCliente.contains(producto)) {
			productosCliente.remove(producto);
			DatosPrograma.retirarProducto(producto);//Retira producto de la lista general de productos
		}else {System.out.println("Este producto no se encuentra en su lista.");
		}
	}
	
	public void hacerUrgente() {
		Scanner entrada=new Scanner(System.in);
		for(Producto i:productosCliente) {
			System.out.println(i.getTitulo());
		}
		System.out.println("Indique el nombre del producto Urgente.");
		String nombre=entrada.nextLine();
		for(Producto i:productosCliente) {
			if(nombre.equals(i.getTitulo()) && !i.isUrgente()) {
				i.setUrgente(true);
				System.out.println("Ha convertido "+i.getTitulo()+" en urgente con tarjeta "+this.credito);
			}else {
				System.out.println("Ha introducido un nombre incorrecto o su producto ya era urgente.");
			}
		}
	}
	
	public void comprobarCompra() {
		Scanner entrada=new Scanner(System.in);
		for(Producto p: this.productosCliente) {
			if(p.isVenta()) {
				boolean terminado =false;
				while(!terminado) {	
					System.out.println("¿Quiere aceptar la venta del producto" +p.getTitulo()+"?"+"Si/No");
					String decision= entrada.nextLine();
				
					if(decision.toUpperCase().equals("SI")){
						System.out.println("Ha vendido el producto");
						Venta v =new Venta(p.getfechaPublicacion(),p.getCategoria(),p.getEstado(),p.getDescripcion(),p.getTitulo(),
								p.getPrecio(),p.getCp(),LocalDateTime.now(),this.getNombre(),p.getComprador().get(0),this.getDni(),p.getComprador().get(1));
						DatosPrograma.ventas.add(v);
						retirarProducto(p);
						terminado=true;
					}else if(decision.toUpperCase().equals("NO")) { //Si no acepta la compra, el producto deja de estar pendiente de vender
						System.out.println("No acepta la compra");
						p.setVenta(false);
						terminado=true;
					}
					else{
						System.out.println("Introduce SI/NO");
					}
		}
		}
	}
	}
	public static ArrayList<Producto> ordenaCoPostal(String codigo, ArrayList<Producto> productos){
        char caracter0 = codigo.charAt(0);
        char caracter1 = codigo.charAt(1);
        char caracter2 = codigo.charAt(2);
        char comparable0; 
        char comparable1; 
        char comparable2; 
        ArrayList<Producto> muyProximo = new ArrayList<Producto>();
        ArrayList<Producto> proximo = new ArrayList<Producto>();
        ArrayList<Producto> indiferente = new ArrayList<Producto>();
        ArrayList<Producto> listaFinal = new ArrayList<Producto>();
        
        for (Producto variable : productos){
            comparable0 = (variable.getCp()).charAt(0);
            comparable1 = (variable.getCp()).charAt(1);
            comparable2 = (variable.getCp()).charAt(2);
            if (comparable0 == caracter0 && comparable1 == caracter1 && comparable2 == caracter2){
                muyProximo.add(variable);
            }
            else if (comparable0 == caracter0 && comparable1 == caracter1){
                proximo.add(variable);
                
            }
            else{
                indiferente.add(variable);
            }
            
            
        }
        for (Producto enLista:proximo){
            muyProximo.add(enLista);
        }
        for (Producto enLista:indiferente){
            muyProximo.add(enLista);
        }
            
    return muyProximo;   
    }
    
    
    public static ArrayList<Producto> buscaOrdena(String palabrasClave, ArrayList<Producto> productos, String codigo){
        ArrayList<Producto> urgentes = new ArrayList<Producto>();
        ArrayList<Producto> iguales = new ArrayList<Producto>();
        ArrayList<Producto> parecidos = new ArrayList<Producto>();
        ArrayList<Producto> restantes = new ArrayList<Producto>();
        
        for (Producto p: productos){
            if (p.isUrgente()){
                urgentes.add(p);
            }
            else {
                if (palabrasClave.equals(p.getTitulo())){
                iguales.add(p);
                }
                else if(p.getTitulo().contains(palabrasClave)){
                parecidos.add(p);
                }
                else{
                restantes.add(p);
                }
            }
            
        }
        urgentes = ordenaCoPostal(codigo, urgentes);
        iguales = ordenaCoPostal(codigo, iguales);
        parecidos = ordenaCoPostal(codigo, parecidos);
        restantes = ordenaCoPostal(codigo, restantes);
        
        for (Producto p:iguales){
            urgentes.add(p);
            
        }
        for (Producto p:parecidos){
            urgentes.add(p);
            
        }
    
        for (Producto p:restantes){
            urgentes.add(p);
            
        }
        
        return urgentes;        
    }
	public void comprarProducto(String palabrasClave) {
		Scanner entrada=new Scanner(System.in);
		ArrayList<Producto> productosDisponibles = buscaOrdena(palabrasClave,DatosPrograma.productos,this.cp);
		for(Producto p:productosDisponibles) {
			System.out.println(p.getTitulo()+ "precio "+p.getPrecio()+"Estado"+p.getEstado());
		}
		System.out.println("Escribe el nombre del producto que quieres comprar: ");
		String nombre=entrada.nextLine();
		for(Producto p:productosDisponibles) {
			if(nombre.equals(p.getTitulo())) {
				System.out.println("Ha solicitado comprar: "+p.getTitulo()+"por valor "+p.getPrecio()+" con tarjeta "+this.getCredito());
				p.setVenta(true); //Pone el marcador del producto en vendido para ser aceptado por el vendedor mas tarde
				p.setComprador(this.nombre, this.dni);
			}else {
				System.out.println("El nombre que ha introducido es incorrecto");
			}
		}
	}
	
	
	
	public void hacerProfesional() {
		Scanner entrada=new Scanner(System.in);
		System.out.println("Introduce descripcion: ");
		String descripcion=entrada.nextLine();
		System.out.println("Introduce apertura (int 0-24): ");
		int  apertura=entrada.nextInt();
		System.out.println("Introduce cierre (int 0-24): ");
		int cierre=entrada.nextInt();
		System.out.println("Introduce telefono: ");
		String telefono=entrada.nextLine();
		System.out.println("Introduce web: ");
		String web=entrada.nextLine();
		this.setApertura(apertura);
		this.setCierre(cierre);
		this.setDescripcion(descripcion);
		this.setTelefono(telefono);
		this.setWeb(web);
		System.out.println("Ahora es profesional. Transaccion de 30€. Tarjeta:"+this.credito);
	}
	
	
	
	
	
	
	
	
	public Cliente() {
		super();
	}

	public Cliente(String dni, String nombre, String correo, String clave, String cp, String ciudad, int credito) {
		//atributos vacios de cliente profesional
		//atributos de cliente normal
		this.dni = dni;
		this.nombre = nombre;
		this.correo = correo;
		this.clave = clave;
		this.cp = cp;
		this.ciudad = ciudad;
		this.credito = credito;
		this.productosCliente =new ArrayList<>(); //Crea Arraylist vacio cada vez que se crea un cliente 
	}
	
	//constructor de cliente profesional inicial
	public Cliente(String dni, String nombre, String correo, String clave, String cp, String ciudad, int credito, 
			String descripcion, int apertura, int cierre, String telefono, String web) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.correo = correo;
		this.clave = clave;
		this.cp = cp;
		this.ciudad = ciudad;
		this.credito = credito;
		this.descripcion = descripcion;
		this.apertura = apertura;
		this.cierre = cierre;
		this.telefono = telefono;
		this.web = web;
		this.productosCliente =new ArrayList<>(); //Crea Arraylist vacio cada vez que se crea un cliente 
	}
	
	public ArrayList<Producto> getProductosCliente(){ //Retorna arraylist con productos de un cliente
		return productosCliente;
	}
	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public String getClave() {
		return clave;
	}

	public String getCp() {
		return cp;
	}

	public int getCredito() {
		return credito;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;	
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public void setCredito(int credito) {
		this.credito = credito;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", correo=" + correo + ", clave=" + clave + ", cp=" + cp
				+ ", ciudad=" + ciudad + ", credito=" + credito + "Productos"+productosCliente+"]";
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public int getApertura() {
		return apertura;
	}
	public int getCierre() {
		return cierre;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getWeb() {
		return web;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void setApertura(int apertura) {
		this.apertura = apertura;
	}
	public void setCierre(int cierre) {
		this.cierre = cierre;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	
	
}
