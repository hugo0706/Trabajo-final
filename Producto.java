import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;  
/**
 * 
 * Esta Clase pretende construir un producto y recoge sus métodos asociados
 * 
 * @author Javier Carrizosa Bermejo
 * @author Hugo García Calvo
 *
 */
public class Producto implements Serializable {
    private LocalDate fechaPublicacion;    
    private String categoria;     
    private String estado;     //Nuevo, seminuevo, decente.....
    private String descripcion;   
    private String titulo;   //Resumen del producto con palabras clave
    private double precio;       
    private boolean venta;    //Booleano que indica si el producto ha sido vendido o no
    private boolean urgente;	//Booleano que indica si es urgente
    public String cp;
    private ArrayList<String> comprador; //guarda nombre y dni de su comprador
    
	
    
    public Producto(String titulo, String categoria, String estado, String descripcion, double precio,String cp) {
    	this.cp=cp;
    	this.categoria = categoria;
        this.estado = estado;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.precio = precio;
        this.venta = false;     //Siempre se inicia como falso
        this.urgente=false;  //Se inicia como falso
        this.fechaPublicacion = LocalDate.now();        //Fecha de publicacion = momento en el que se crea el producto
                
    }
    
    
    
    public boolean isVenta() {
        return venta;
    }
    
    public void setVenta(boolean valor){
        this.venta = valor;
                
    }

    

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    
    public LocalDate getfechaPublicacion(){
        return fechaPublicacion;
    }



	@Override
	public String toString() {
		return "Producto [fechaPublicacion=" + fechaPublicacion + ", categoria=" + categoria + ", estado=" + estado
				+ ", descripcion=" + descripcion + ", titulo=" + titulo + ", precio=" + precio + ", venta=" + venta
				+ "]";
	}


	public boolean isUrgente() {
		return urgente;
	}


	public void setUrgente(boolean urgente) {
		this.urgente = urgente;
	}



	public String getCp() {
		return cp;
	}



	public void setCp(String cp) {
		this.cp = cp;
	}

	/**
         * Funcion que guarda el nombre y el dni del comprador que pretenda comprar este producto
         * @param nombre Generalmente se utilizará el nombre del comprador
         * @param dni Dni del comprador
         */
	public void setComprador(String nombre,String dni) {
		this.comprador.add(nombre);
		this.comprador.add(dni);
	}
	public ArrayList<String> getComprador(){
		return this.comprador;
	}
   

    
}
