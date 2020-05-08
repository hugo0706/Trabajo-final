import java.util.ArrayList;
public class Ordenacion {
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
    
    
    public static ArrayList<Producto> BuscaOrdena(String palabrasClave, ArrayList<Producto> productos, String codigo){
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
}