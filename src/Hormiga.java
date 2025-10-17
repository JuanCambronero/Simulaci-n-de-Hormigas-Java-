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

    @Override
    public void run() {
        while (isActivo()) {
            try {
                // Mover aleatoriamente
                int direccion = random.nextInt(direcciones.length);
                int dx = direcciones[direccion][0];
                int dy = direcciones[direccion][1];
                Posicion nuevaPos = posicion.mover(dx, dy);

                // Verifica límites antes de moverse
                if (nuevaPos.dentroLimites(Mapa.ancho, Mapa.alto)) {
                    setPosicion(nuevaPos);
                }

                // Espera un poco antes del siguiente movimiento
                Thread.sleep(1000,2000);
            } catch (InterruptedException e) {
                detener();
            }
        }
    }

}
