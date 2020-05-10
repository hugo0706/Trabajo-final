import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * 
 * Esta Clase pretende construir un cliente y recoge los metodos usados por este.
 * 
 * @author Javier Carrizosa Bermejo
 * @author Hugo García Calvo
 *
 */
public class Cliente implements Serializable {
	public String dni;
	public String nombre;
	public String correo;
	public String clave;
	public String cp;
	public String ciudad;
	public int credito;
	private ArrayList<Producto> productosCliente;
	/**
	 * Atributos de cliente profesional
	 */
	private String descripcion;
	private int apertura;
	private int cierre;
	private String telefono;
	private String web;
	
	/**
	 * 
	 * Este método crea una instancia de la clase Producto
	 * 
	 * @return Retorna un objeto de la clase Producto
	 */
	public  Producto crearProducto() {
		String estadosPosibles[]= {"NUEVO","COMO NUEVO","BUENO","ACEPTABLE","REGULAR"};
		String categoriasPosibles[]= {"Moda y accesorios","TV, audio y foto","Móviles y telefonía","Informática y electrónica","Consolas y videojuegos","Deporte y ocio"};
		Scanner entrada=new Scanner(System.in);
		String titulo="";
		String categoria="";
		String estado="";
		String descripcion="";
		double precio=0;
		boolean correcto=false;
		while(!correcto) {
			System.out.println("Introduce titulo");
			try {
				titulo=entrada.nextLine();
			}catch(Exception e) {
				continue;
			}
			System.out.println("Selecciona categoria por su numero: ");
			try {
				int e=0;
				for(String i:categoriasPosibles) {
					e++;
					System.out.println(e+" "+i);
				}
				String seleccion=entrada.nextLine();
				switch(seleccion) {
					case "1":
						categoria=categoriasPosibles[0];
						break;
					case "2":
						categoria=categoriasPosibles[1];
						break;
					case "3":
						categoria=categoriasPosibles[2];
						break;
					case "4":
						categoria=categoriasPosibles[3];
						break;
					case "5":
						categoria=categoriasPosibles[4];
						break;
					case "6":
						categoria=categoriasPosibles[5];
						break;
				}
			}catch(Exception e) {
				continue;
			}
			boolean estadoAceptado=false;
			while(!estadoAceptado) {
			System.out.println("Introduce estado(Nuevo/Como nuevo/Bueno/Aceptable/Regular");
			try {
				estado=entrada.nextLine();
				for(String e:estadosPosibles) {
					if(estado.toUpperCase().equals(e)) {
						estadoAceptado=true;
					}
				}
			}catch(Exception e) {
				continue;
			}
			}
			System.out.println("Introduce descripcion");
			try {
				descripcion=entrada.nextLine();
			}catch(Exception e) {
				continue;
			}
			System.out.println("Introduce precio (double)");
			try {
				precio=entrada.nextDouble();
				correcto=true;
			}catch(Exception e) {
				continue;
			}
		}
		return new Producto(titulo,categoria,estado,descripcion,precio,this.cp,this.dni);
	}
	
	/**
	 * Este método añade un objeto de clase Producto a un array general con todos
	 * los productos de la aplicacion, y a un array con los productos de un cliente
	 * específico.
	 * 
	 * @param producto objeto de clase Producto
	 */
	public void añadirProducto(Producto producto) {
		/**
		 * Si el producto no se encuentra en la lista personal del cliente, se añade.
		 */
		if(this.productosCliente==null) {
			producto.setCp(this.cp);
			this.productosCliente.add(producto);
			DatosPrograma.añadirProducto(producto);
		}else {
			if(!this.productosCliente.contains(producto)) {
				producto.setCp(this.cp);
				this.productosCliente.add(producto);
				/**
				 * Añade producto a la lista general de productos
				 */
				DatosPrograma.añadirProducto(producto);
				
			}else {System.out.println("Este producto ya se encuentra en su lista.");
		}
		}
	}
	
	/**
	 * Este metodo retira un objeto de clase Producto del array general de productos
	 * y del array personal de productos del cliente
	 * 
	 * @param producto objeto de clase Producto
	 */
	public  void retirarProducto(Producto producto) { 
		/**
		 * Si el producto se encuentra en la lista personal del cliente, se retira.
		 */
		if(productosCliente.contains(producto)) {
			this.productosCliente.remove(producto);
			/**
			 * Retira producto de la lista general de productos
			 */
			DatosPrograma.retirarProducto(producto);
		}else {System.out.println("Este producto no se encuentra en su lista.");
		}
	}
	
	/**
	 * Este metodo muestra todos los productos de un cliente y, una vez seleccionado
	 * uno, cambia su estado a urgente, mostrando la transaccion realizada y la 
	 * tarjeta de credito usada.
	 */
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
				i.estableceFechaUrgente();
			}else {
				System.out.println("Ha introducido un nombre incorrecto o su producto ya era urgente.");
			}
		}
	}
	
	/**
	 * Este metodo comprueba si alguien ha solicitado la compra de uno de los 
	 * productos propios del usuario con la sesion abierta, en caso de haber 
	 * solicitudes, muestra la notificacion y se decide si aceptar o no la compra.
	 * Si esta es aceptada se guardan los datos de la venta en un array con las 
	 * ventas totales.
	 * Si esta no es aceptada el producto deja de estar en estado de compra.
	 */
	public void comprobarCompra() {
		Scanner entrada=new Scanner(System.in);
		System.out.println("------------NUEVAS NOTIFICACIONES------------");

		for(int i=0;i<this.productosCliente.size();i++) {
			Producto p=this.productosCliente.get(i);
			if(p.isVenta()) {
				boolean terminado =false;
				
				while(!terminado) {	
					
					System.out.println("¿Quiere aceptar la venta del producto " +p.getTitulo()+"?"+" Si/No");
					String decision= entrada.nextLine();
				
					if(decision.toUpperCase().equals("SI")){
						i=i-1;
						System.out.println("Ha vendido el producto");
						Venta v =new Venta(p.getfechaPublicacion(),p.getCategoria(),p.getEstado(),p.getDescripcion(),p.getTitulo(),
								p.getPrecio(),p.getCp(),LocalDateTime.now(),this.getNombre(),p.getComprador().get(0),this.getDni(),p.getComprador().get(1));
						System.out.println(v);
						DatosPrograma.ventas.add(v);
						for(Producto pCliente:this.productosCliente) {
							if(p.getTitulo().equals(pCliente.getTitulo())) {
								this.productosCliente.remove(pCliente);		
								
								break;
							}
						}
						
						for(Producto pGeneral: DatosPrograma.productos) {
							if(pGeneral.getTitulo().equals(p.getTitulo())) {
								if(pGeneral.getDniDueño().equals(this.dni)) {
									DatosPrograma.productos.remove(pGeneral);
									
									break;
								}
								
							}
						}
						
						terminado=true;
					/**
					 * Si no acepta la compra, el producto deja de estar pendiente de vender
					 */
					}else if(decision.toUpperCase().equals("NO")) { 
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
		System.out.println("----------NO HAY NOTIFICACIONES----------");
	}
	/**
     * 
     * Método que ordena una lista de productos comparando producto a producto con el código postal del cliente
     * @param codigo String del codigo postal, debe contener como minimos 3 caracteres
     * @param productos Lista de productos a ordenar
     * @return lista de productos inicial ordenada mediante la semejanza del código postal
     */
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
    
	/**
     * Divide la lista en listas más pequeñas según la semejanza entre las palabras claves y el título de cada producto
     * @param palabrasClave String introducido por el usuario
     * @param productos Lista de productos
     * @param codigo codigo postal que corresponderá con el codigo postal del usuario utilizando la aplicación
     * @return retorna una lista ordenada tanto por código postal como por semejanza con las palabras claves
     */
	
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
    
    /**
     * Este metodo hace uso del metodo buscaOrdena para acceder a un array con 
     * los productos que aparecen en la busqueda, y permite realizar la solicitud
     * de compra de cualquiera de estos.
     * 
     * @param palabrasClave String con palabras clave sobre producto a buscar,
     * puede ser un string vacio en caso de no usar palabras clave
     */
    public void comprarProducto(String palabrasClave) {
		Scanner entrada = new Scanner(System.in);
		ArrayList<Producto> productosDisponibles = new ArrayList<Producto>();
                String categoria = "";
                String categoriasPosibles[]= {"Moda y accesorios","TV, audio y foto","Móviles y telefonía","Informática y electrónica","Consolas y videojuegos","Deporte y ocio"};
                System.out.println("Selecciona categoria por su numero: ");
                int e=0;
                for(String i:categoriasPosibles) {
                    e++;
                    System.out.println(e+" "+i);
                }
                String seleccion=entrada.nextLine();
                switch(seleccion) {
                    case "1":
                        categoria=categoriasPosibles[0];
                        break;
                    case "2":
                        categoria=categoriasPosibles[1];
                        break;
                    case "3":
                        categoria=categoriasPosibles[2];
                        break;
                    case "4":
                        categoria=categoriasPosibles[3];
                        break;
                    case "5":
                        categoria=categoriasPosibles[4];
                        break;
                    case "6":
                        categoria=categoriasPosibles[5];
                        break;
                }
                
                
                
                for (Producto purga:DatosPrograma.productos){
                    if (categoria.equals(purga.getCategoria())){
                        if (!purga.getDniDueño().equals(this.dni)){
                            productosDisponibles.add(purga);
                        }
                    }
                    
                }        
                productosDisponibles = buscaOrdena(palabrasClave,productosDisponibles,this.cp);
                        
                        
		if(productosDisponibles.isEmpty()) {
			System.out.println("No hay ningun producto disponible.");
		}else {
			for(Producto p:productosDisponibles) {
				System.out.println(p.getTitulo()+ " precio "+p.getPrecio()+" Estado "+p.getEstado());
			}
			System.out.println("Escribe el nombre del producto que quieres comprar: ");
			String nombre=entrada.nextLine();
			for(Producto p:productosDisponibles) {
				if(nombre.equals(p.getTitulo())) {
					System.out.println("Ha solicitado comprar: "+p.getTitulo()+"por valor "+p.getPrecio()+" con tarjeta "+this.getCredito());
					/**
					 * Pone el marcador del producto en vendido para ser aceptado por el vendedor mas tarde
					 */
					for(Producto producto:DatosPrograma.productos) {
						if(producto.getTitulo().equals(nombre))//Si el nombre del producto coincide
							if(p.getDniDueño().equals(producto.getDniDueño())) {//si los dnis de los dueños coinciden
								producto.setVenta(true); 
								producto.setComprador(this.nombre, this.dni);
								String dniDueño=producto.getDniDueño();
								for(Cliente elem:DatosPrograma.clientes) {
									if(elem.dni.equals(dniDueño)) {
										for(Producto pDueño:elem.getProductosCliente()) {
											if(pDueño.getTitulo().equals(nombre)) {
												pDueño.setVenta(true);
												pDueño.setComprador(this.nombre, this.dni);
											}
										}
									}
								}
								}
							}
				}else {
					System.out.println("El nombre que ha introducido es incorrecto");
				}
			}
		}
	}
	
	/**
	 * Este metodo permite añadir los atributos de profesional a un cliente
	 * mostrando los datos de la transaccion.
	 */
	
	public void hacerProfesional() {
		Scanner entrada=new Scanner(System.in);
		boolean correcto=false;
		while(!correcto) {
			System.out.println("Introduce descripcion: ");
			try {
				descripcion=entrada.nextLine();
			}catch(Exception e) {
				continue;
			}
			System.out.println("Introduce apertura (int 0-24): ");
			try {
				apertura=entrada.nextInt();
			}catch(Exception e) {
				continue;
			}
			System.out.println("Introduce cierre (int 0-24): ");
			try {
				cierre=entrada.nextInt();
				telefono=entrada.nextLine();
			}catch(Exception e) {
				continue;
			}
			System.out.println("Introduce telefono: ");
			try {
				telefono=entrada.nextLine();
			}catch(Exception e) {
				continue;
			}
			System.out.println("Introduce web: ");
			try {
				web=entrada.nextLine();
				correcto=true;
			}catch(Exception e) {
				continue;
			}
		}
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
	/**
	 * Este constructor permite instanciar objetos de la clase Cliente.
	 */
	public Cliente(String dni, String nombre, String correo, String clave, String cp, String ciudad, int credito) {

		this.dni = dni;
		this.nombre = nombre;
		this.correo = correo;
		this.clave = clave;
		this.cp = cp;
		this.ciudad = ciudad;
		this.credito = credito;
		this.productosCliente =new ArrayList<Producto>(); //Crea Arraylist vacio cada vez que se crea un cliente 
	}
	
	
	public ArrayList<Producto> getProductosCliente(){ 
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
	
	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", correo=" + correo + ", clave=" + clave + ", cp=" + cp
				+ ", ciudad=" + ciudad + ", credito=" + credito + "Productos"+productosCliente+"]";
	}
	
	
}
