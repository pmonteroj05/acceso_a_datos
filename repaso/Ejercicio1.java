import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1 {

    static Scanner numeros = new Scanner(System.in);

    static long tCE = 0;
    static long tCL = 0;

    static boolean hecho = false;
    static boolean contado = false;

    // Crea un fichero texto_sin_buffer.txt
    // Escribe en él varias frases de ejemplo (10.000 veces la palabra "Java")
    public static void crearEscribir(String a) {
        long tinicioCE = System.nanoTime();
        try {
            File fichero = new File(a);
            FileOutputStream fo = new FileOutputStream(fichero);

            for (int i = 0; i < 10000; i++) {
                fo.write("Java\n".getBytes());
            }

            System.out.println("Fichero creado y escrito correctamente.");

            fo.close();

            long tfinCE = System.nanoTime();

            tCE = tfinCE - tinicioCE;

            hecho = true;

        } catch (IOException e) {
            System.err.println("Error");
        }
    }

    // Cierra el fichero y luego léelo de nuevo para contar cuántas líneas tiene.
    public static void contarLineas(String a) {
        int contadorLineas = 0;

        long tinicioCL = System.nanoTime();

        try {
            File fichero = new File(a);

            if(!fichero.exists()){
                crearEscribir(a);
            }

            FileInputStream fi = new FileInputStream(fichero);
            int caracter;
            while ((caracter = fi.read()) != -1) {
                if ((char) caracter == '\n') {
                    contadorLineas++;
                }
            }

            System.out.println("El fichero tiene " + contadorLineas + " líneas.");

            fi.close();

            long tfinCL = System.nanoTime();

            tCL = tfinCL - tinicioCL;

            contado = true;

        } catch (IOException e) {
            System.err.println("Error");
        }
    }

    public static void ejercicio2(String[] args) {
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