import java.util.Random;

public abstract class Hormiga extends Thread {
    //ATRIBUTOS
    protected String idHormiga;
    protected TipoHormiga tipo;
    protected Posicion posicion;
    protected boolean activo;
    protected Random random;
    protected int [][] direcciones;

    //CONSTRUCTOR
    public Hormiga(String idHormiga, TipoHormiga tipo, Posicion posicion) {
        this.idHormiga = idHormiga;
        this.tipo = tipo;
        this.posicion = posicion;
    }

    //METODOS

    public String getIdHormiga() {
        return idHormiga;
    }

    public TipoHormiga getTipo() {
        return tipo;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion nuevaPosicion) {
        this.posicion = nuevaPosicion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void detener(){
        activo = false;
    }
}
