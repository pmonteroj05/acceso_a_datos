import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio3{ 

    //Crea un fichero datos_sin_buffer.bin. Escribe 1 millón de enteros (del 0 al 999.999).
    public static void crearYEscribir(String f){
        try {
            File fichero = new File(f);
            FileWriter fw = new FileWriter(fichero);

            for(int i = 0; i < 1000000; i++){
                fw.write(i + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.err.println("Error al crear o escribir el fichero");
        }
    }

    //Lée el fichero sumando todos los enteros.
    public static void leerYSumar(String f){
        long suma = 0;
        try {
            FileReader fr = new FileReader(new File(f));
            int c;
            long numeroActual = 0;
            while((c = fr.read()) != -1){
                char ch = (char) c;
                if (ch >= '0' && ch <= '9') {  //se comprueba que el carácter es un número
                    numeroActual = numeroActual * 10 + (ch - '0'); //mediante esta formula, se pueden tener en cuenta 
                                                                   // los numeros de más de una cifra completos ya que 
                                                                   // se va recorriendo caracter a caracter y si no, 
                                                                   // no se realizaría bien la suma
                } else {
                    suma += numeroActual;
                    numeroActual = 0;
                }
            }
            System.out.println("Suma total de los número del fichero = " + suma);
            fr.close();
            
        } catch (IOException e) {
            System.err.println("Error, no se ha podido leer el fichero");
        }
    }


    public static void main(String [] args){
        //Mide el tiempo total de escritura y lectura
        double tinicio = System.nanoTime();
        crearYEscribir("datos_sin_buffer.bin");
        leerYSumar("datos_sin_buffer.bin");
        double tfin = System.nanoTime();

        System.out.println("Tiempo total en nanosegundos = " + (tfin - tinicio));
    }
}