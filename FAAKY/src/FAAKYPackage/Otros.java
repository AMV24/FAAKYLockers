package FAAKYPackage;

public class Otros {

    // Método para mostrar el menú principal
    public void menuPrincipal(){
        System.out.println("¡BIENVENIDOS A FAAKY LOCKERS CORPORATION!");
        System.out.println("========================================");
        System.out.println("           MENÚ PRINCIPAL");
        System.out.println("========================================");
        System.out.println("1. RENTAS");
        System.out.println("2. LOCKERS");
        System.out.println("3. REGISTROS Y MODIFICACIONES");
        System.out.println("4. CERRAR PROGRAMA");
        System.out.println("========================================");
        System.out.print("Elige una opción: ");
    }


    // Método para limpiar la pantalla
    public void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    } 
}
