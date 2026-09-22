import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*Repite el ejercicio anterior, pero usa BufferedOutputStream y BufferedInputStream.
Compara el tiempo con el del ejercicio --> el tiempo es mucho menor utilizando Buffer */

public class Ejercicio4 {
    public static void crearYEscribir(String f){
        try (BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(f))){

            for(int i = 0; i < 1000000; i++)
                bo.write((i + "\n").getBytes());

            bo.close();
        } catch (IOException e) {
            System.err.println("Error al crear o escribir el fichero");
        }
    }

    public static void leerYSumar(String f){
        long suma = 0;
        try (BufferedInputStream bi = new BufferedInputStream(new FileInputStream(f))){
        
            int c;
            long numeroActual = 0;

            while((c = bi.read()) != -1){
                char ch = (char) c;
                if (ch >= '0' && ch <= '9') {
                    numeroActual = numeroActual * 10 + (ch - '0');
                } else {
                    suma += numeroActual;
                    numeroActual = 0;
                }
            }
            System.out.println("Suma total de los número del fichero = " + suma);
            bi.close();
            
        } catch (IOException e) {
            System.err.println("Error, no se ha podido leer el fichero");
        }
    }


    public static void main(String [] args){
        double tinicio = System.nanoTime();
        crearYEscribir("datos_con_buffer.bin");
        leerYSumar("datos_con_buffer.bin");
        double tfin = System.nanoTime();

        System.out.println("Tiempo total en nanosegundos = " + (tfin - tinicio));
    }
}
