import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2 {

    //Repite el ejercicio anterior, pero usa BufferedWriter y BufferedReader.

    /*Compara el tiempo con el del ejercicio 1. ¿Cuál fue más eficiente? ¿Por qué?
    Con Buffer el el tiempo total el mucho menor que en el ejercicio 1 en el que no se utiliza.
    Sin utilizar Buffer el programa debe hacer muchas más llamadas al sistema mientras que con él
    tan solo tiene que hacer una.*/

    static Scanner numeros = new Scanner(System.in);

    static long tCE = 0;
    static long tCL = 0;

    static boolean hecho = false;
    static boolean contado = false;

    public static void crearEscribir(String a) {
        long tinicioCE = System.nanoTime();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(a)))) {

            for (int i = 0; i < 10000; i++) {
                bw.write("Java\n");
            }

            System.out.println("Fichero creado y escrito correctamente.");

            bw.close();

            long tfinCE = System.nanoTime();

            tCE = tfinCE - tinicioCE;

            hecho = true;

        } catch (IOException e) {
            System.err.println("Error");
        }
    }

    public static void contarLineas(String a) {
        int contadorLineas = 0;

        long tinicioCL = System.nanoTime();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(a)))){

            int caracter;
            while ((caracter = br.read()) != -1) {
                if ((char) caracter == '\n') {
                    contadorLineas++;
                }
            }

            System.out.println("El fichero tiene " + contadorLineas + " líneas.");

            br.close();

            long tfinCL = System.nanoTime();

            tCL = tfinCL - tinicioCL;

            contado = true;

        } catch (IOException e) {
            System.err.println("Error, el fichero no existe");
            crearEscribir(a);
            contarLineas(a);
        }
    }


    public static void main(String [] args){

        String nombreFichero = "texto_sin_buffer.txt";

        String menu = "- - - MENÚ - - -\n" +
                "1. Crear y escribir fichero\n" +
                "2. Contar líneas\n" +
                "3. Tiempo de ejecución para escribir y crear\n" +
                "4. Tiempo de ejecución para contar líneas\n" +
                "5. Tiempo total de ejecución\n" +
                "6. Salir\n" +
                "Introduce una opción: ";

        int opc;
        do {
            System.out.print(menu);

            opc = numeros.nextInt();

            switch (opc) {
                case 1:
                    crearEscribir(nombreFichero);
                    break;
                case 2:
                    contarLineas(nombreFichero);
                    break;
                case 3:
                    if(hecho){
                        System.out.println(tCE);
                    }else{
                        System.out.println("El fichero no ha sido creado aún");
                    }
                    
                    break;
                case 4:
                    if(contado){
                        System.out.println(tCL);
                    }else{
                        System.out.println("El fichero no ha sido contado aún");
                    }
                    break;
                case 5:
                    if(contado && hecho){
                        System.out.println(tCE + tCL);
                    }else{
                        System.out.println("El fichero aún no ha sido creado o contado");
                    }
                    break;
                case 6:
                    break;
            
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while (opc != 6);
    }
    
}
