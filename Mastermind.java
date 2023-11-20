import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Mastermind {
    public static final int OPORTUNIDADES = 15;
    private static MastermindGUI mastermindGUI;

    public static void main(String[] args) {
        // Inicializar la GUI
        mastermindGUI = new MastermindGUI();

        Codigo codigo = new Codigo();
        Scanner sc = new Scanner(System.in);
        int intentos = 0;
        boolean ganado = false;

        System.out.println("Bienvenido al juego de Mastermind.");
        System.out.println("Debes adivinar el código secreto de " + Codigo.TAMANIO + " colores.");
        System.out.println("Los colores disponibles son: " + Arrays.toString(Codigo.COLORES));
        System.out.println("Los códigos no pueden tener colores repetidos, ni colores vacíos.(USA LAS INICIALES EN MAYUSCULAS SEPARAS POR ESPACIOS)");
        System.out.println("Tienes " + OPORTUNIDADES + " oportunidades para adivinar el código.");
        System.out.println("La retroalimentación se mostrará con pines de color negro y blanco.");
        System.out.println("Un pin negro significa que hay un color que coincide en posición y color.");
        System.out.println("Un pin blanco significa que hay un color que coincide solo en color.");
        System.out.println("Buena suerte!");

        while (!ganado && intentos < OPORTUNIDADES) {
            intentos++;
            System.out.println("Intento " + intentos + ":");
            System.out.print("Ingresa un código de colores: ");
            String entrada = sc.nextLine();

            boolean valido = codigo.ingresarCodigo(entrada);
            if (valido) {
                Retroalimentacion retro = codigo.obtenerRetroalimentacion();
                System.out.println("Retroalimentación: " + retro);
                mastermindGUI.updateBoard(codigo.getSecreto(), retro.toString());

                if (retro.toString().equals("NE NE NE NE NE NE")) {
                    ganado = true;
                    System.out.println("¡Felicidades! Has adivinado el código secreto.");
                }
            } else {
                System.out.println("El código ingresado no es válido. Inténtalo de nuevo.");
            }
        }

        if (!ganado) {
            System.out.println("Lo siento, no has podido adivinar el código secreto.");
            System.out.println("El código secreto era: " + codigo.getSecreto());
        }

        sc.close();
    }
}
