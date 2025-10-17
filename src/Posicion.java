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
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean dentroLimites(int maxX, int maxY) {
        if ((x >= maxX || y >= maxY)  && (maxX > 0|| maxY > 0)){  //Comprobar si las coordenadas están en los límites del Mapa
            return true;
        }else{
            return false;
        }
    }

    public Posicion mover(int deltaX, int deltaY) {
        return new Posicion(x + deltaX, y + deltaY);//Crea la nueva posición a donde se mueve la hormiga sumando la posicion actual de ella con la del movimiento
    }
}
