import java.io.PrintWriter;
import java.util.Random;

public class GenerateInfoFiles {

    private static final String[] NOMBRES = {"Juliana", "Alberto", "Juan", "Ana", "Luis"};
    private static final String[] APELLIDOS = {"Romero", "Padilla", "Chacon", "Gomez", "Perez"};
    private static final String[] TIPOS_DOC = {"CC", "CE"};
    private static final String[] PRODUCTOS = {"ConcentradoPerro", "ConcentradoGato", "ArenaGato", "Juguete", "Collar", "Correa", "Shampoo", "Cama"};

    private static final double[] PRECIOS = {12000, 11000, 50000, 25000, 20000, 35000, 30000, 100000};

    private static final Random rand = new Random();

    public static void main(String[] args) {
        try{
            System.out.println("Generando archivos...");

            createProductsFile(PRODUCTOS.length);
            createSalesMaInfoFile(5);

            System.out.println("Archivos generados correctamente");
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void createProductsFile(int productsCount) throws Exception {
        try (PrintWriter writer = new PrintWriter("productos.csv", "UTF-8")){

            for (int i = 0; i < productsCount; i++){
                writer.println((i + 1) + ";" + PRODUCTOS[i] + ";" + PRECIOS[i]);
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            throw e;
        }
    }

    public static void createSalesMaInfoFile(int salesmanCount) throws  Exception {
        try (PrintWriter writer = new PrintWriter("vendedores.csv", "UTF-8")) {

            for (int i =0; i < salesmanCount; i++){

                long id = 1000 + i;
                String nombre = NOMBRES[rand.nextInt(NOMBRES.length)];
                String apellido = APELLIDOS[rand.nextInt(APELLIDOS.length)];
                String tipoDoc = TIPOS_DOC[rand.nextInt(TIPOS_DOC.length)];

                writer.println(tipoDoc + ";" + id + ";" + nombre + ";" + apellido + ";");

                createSalesMenFile(rand.nextInt(5) + 1, nombre, id);
            }
        } catch (Exception e) {
           System.out.println("Error" + e.getMessage());
           throw  e;
        }
    }

    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws Exception {

        try (PrintWriter writer = new PrintWriter("vendedor_" + id + ".csv", "UTF-8")) {

            writer.println("CC;" + id);

            for (int i = 0; i < randomSalesCount; i++) {

                int productoId = rand.nextInt(PRODUCTOS.length) + 1;
                int cantidad = rand.nextInt(10) + 1;

                writer.println(productoId + ";" + cantidad + ";");
            }

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            throw e;
        }

    }

}
