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
        this.activo = true;
        this.random = new Random();
        this.direcciones = new int[][]{
                {-1, 0}, {1, 0}, {0, 1}, {0, -1}
        };
    }


    //METODOS
    //Este metodo recoge el id de la hormiga
    public String getIdHormiga() {
        return idHormiga;
    }
    //Este metodo captura de que tipo es la hormiga
    public TipoHormiga getTipo() {
        return tipo;
    }
    //Este metodo captura la posición actual de la hormiga
    public Posicion getPosicion() {
        return posicion;
    }
    //Este metodo actualiza la posición de la hormiga
    public void setPosicion(Posicion nuevaPosicion) {
        this.posicion = nuevaPosicion;
    }
    //Este metodo comprueba si el hilo esta activo
    public boolean isActivo() {
        return activo;
    }
    //Este metodo detiene la ejecución del hilo
    public void detener(){
        activo = false;
    }
    private boolean esHormiguero(Posicion pos, Posicion hormiguero) {
        return pos.getX() == hormiguero.getX() && pos.getY() == hormiguero.getY();
    }
    //Metodo independiente del hilo
    @Override
    public void run() {
        while (isActivo()) {
            try {
                // Mover aleatoriamente
                int direccion = random.nextInt(direcciones.length);
                int dx = direcciones[direccion][0];
                int dy = direcciones[direccion][1];
                Posicion nuevaPos = posicion.mover(dx, dy);

                Posicion hormiguero = new Posicion(Mapa.ancho/2, Mapa.alto/2);
                // Verifica límites antes de moverse
                if (nuevaPos.dentroLimites(Mapa.ancho, Mapa.alto) && !esHormiguero(nuevaPos, hormiguero)) {
                    setPosicion(nuevaPos);
                }

                // Espera un poco antes del siguiente movimiento
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                detener();
            }
        }
    }

}
