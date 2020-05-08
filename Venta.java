import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
public class Venta implements Serializable {
		private LocalDate fechaPublicacion;    
	    private String categoria;     
	    private String estado;     //Nuevo, seminuevo, decente.....
	    private String descripcion;   
	    private String titulo;   //Resumen del producto con palabras clave
	    private double precio;       
	    public String cp; 
	    private LocalDateTime fechaVenta;
	    private String vendedor;
	    private String comprador;
	    private	String dniV;
	    private String dniC;
		public Venta(LocalDate fechaPublicacion, String categoria, String estado, String descripcion, String titulo,
				double precio, String cp, LocalDateTime fechaVenta, String vendedor, String comprador, String dniV,
				String dniC) {
			super();
			this.fechaPublicacion = fechaPublicacion;
			this.categoria = categoria;
			this.estado = estado;
			this.descripcion = descripcion;
			this.titulo = titulo;
			this.precio = precio;
			this.cp = cp;
			this.fechaVenta = fechaVenta;
			this.vendedor = vendedor;
			this.comprador = comprador;
			this.dniV = dniV;
			this.dniC = dniC;
		}
		public LocalDate getFechaPublicacion() {
			return fechaPublicacion;
		}
		public String getCategoria() {
			return categoria;
		}
		public String getEstado() {
			return estado;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public String getTitulo() {
			return titulo;
		}
		public double getPrecio() {
			return precio;
		}
		public String getCp() {
			return cp;
		}
		public LocalDateTime getFechaVenta() {
			return fechaVenta;
		}
		public String getVendedor() {
			return vendedor;
		}
		public String getComprador() {
			return comprador;
		}
		public String getDniV() {
			return dniV;
		}
		public String getDniC() {
			return dniC;
		}
		public void setFechaPublicacion(LocalDate fechaPublicacion) {
			this.fechaPublicacion = fechaPublicacion;
		}
		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		public void setPrecio(double precio) {
			this.precio = precio;
		}
		public void setCp(String cp) {
			this.cp = cp;
		}
		public void setFechaVenta(LocalDateTime fechaVenta) {
			this.fechaVenta = fechaVenta;
		}
		public void setVendedor(String vendedor) {
			this.vendedor = vendedor;
		}
		public void setComprador(String comprador) {
			this.comprador = comprador;
		}
		public void setDniV(String dniV) {
			this.dniV = dniV;
		}
		public void setDniC(String dniC) {
			this.dniC = dniC;
		}
	    
	    
}
