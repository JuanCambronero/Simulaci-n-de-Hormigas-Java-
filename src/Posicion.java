public class Posicion {
    //Clase inmutable que representa una posición (x, y) en el mapa
    //Permite crear nuevas posiciones simulando movimientos y verificar si están dentro de los límites

    //ATRIBUTOS
    private final int x;    //Posición horizontal
    private final int y;    //Posición Vertical

    //CONSTRUCTOR
    public Posicion(int x, int y) { //Asignar los valores de la posición
        this.x = x;
        this.y = y;
    }

    //METODOS
    //Este metodo captura el valor del eje horizontal
    public int getX() {
        return x;
    }
    //Este metodo captura el valor del eje vertical
    public int getY() {
        return y;
    }
    //Este metodo contiene la logica que válida que la posición está dentro de límites - O(1)
    public boolean dentroLimites(int maxX, int maxY) {
        return (x >= 0 && x < maxX) && (y >= 0 && y < maxY);
    }
    //Este metodo simula el movimiento de la hormiga -O(1)
    public Posicion mover(int deltaX, int deltaY) {
        return new Posicion(x + deltaX, y + deltaY);//Crea la nueva posición a donde se mueve la hormiga sumando la posicion actual de ella con la del movimiento
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
