import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Amigo {
    private String nombre;
    private int telefono;
    private String eMail;
    private Fecha cumpleanios;
    private int uID;
    private boolean estado;
    private static ArrayList<Amigo> coleccion = new ArrayList<>();
    private static int id = 1;

    public Amigo(String nombre, int telefono, String eMail, Fecha cumpleanios, boolean estado){
        this.nombre = nombre;
        this.telefono = telefono;
        this.eMail = eMail;
        this.cumpleanios = cumpleanios;
        this.estado = estado;
        this.uID = id;
        id ++;
    }

    private static void cargarDatos(){
        File file = new File("coleccion.dat");
        if(file.exists()){
            try {
                FileReader fr = new FileReader("coleccion.dat");
                BufferedReader br = new BufferedReader(fr);
                String linea = "";
                while((linea = br.readLine()) != null){
                    id = Integer.parseInt(linea.substring(0,1));
                    String[] datos = linea.split("'");
                    String[] fecha = datos[7].split("/");
                    coleccion.add(new Amigo(datos[1], Integer.parseInt(datos[3]), datos[5], new Fecha(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2])), Boolean.parseBoolean(datos[9])));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
    public static void Menu(){
        cargarDatos();
        Scanner sc = new Scanner(System.in);
        boolean salida = true;
        while (salida){
            Integer opcion = null;
            do{
                System.out.println("Por favor, elige una opción:" +
                        "\n1- Crear una entrada Amigo" +
                        "\n2- Mostrar entradas" +
                        "\n3- Modificar una entrada ya creada" +
                        "\n4- Guardar cambios y salir" +
                        "\n5- Salir sin guardar");
                switch(opcion = Integer.parseInt(sc.nextLine())){
                    case 1:
                        System.out.println("Introduce el nombre");
                        String nombre = sc.nextLine();
                        System.out.println("Introduce el teléfono");
                    /*  nextInt a veces da problemas con el buffer, para no tener
                    que hacer un nextLine vacío prefiero el parseInt().  */
                        int telefono = Integer.parseInt(sc.nextLine());
                        System.out.println("Introduce el E-mail");
                        String email = sc.nextLine();
                        System.out.println("Introduce el cumpleaños (dd/mm/aaaa)");
                        String[] cumpleanios = sc.nextLine().split("/");
                        coleccion.add(new Amigo(nombre, telefono, email, new Fecha(Integer.parseInt(cumpleanios[0]), Integer.parseInt(cumpleanios[1]), Integer.parseInt(cumpleanios[2])), false));
                        break;
                    case 2:
                        Iterator<Amigo> miIterator = coleccion.iterator();
                        while (miIterator.hasNext()){
                            System.out.println(miIterator.next().toString());
                        }
                        break;
                    case 3:
                        System.out.println("Introduce el número ID que quieres modificar");
                        int idNumero = Integer.parseInt(sc.nextLine());
                        for (int i = 0; i < coleccion.size(); i++) {
                            if (coleccion.get(i).getuID() == idNumero){
                                System.out.println("Modifica el valor que quieras, deja vacío los que no quieras modificar");
                                String dato = "";
                                System.out.println("Introduce el nuevo nombre");
                                if ((dato = sc.nextLine()).length() >= 1){
                                    coleccion.get(i).setNombre(dato);
                                    coleccion.get(i).setEstado(false);
                                }

                                System.out.println("Introduce el nuevo teléfono");
                                if ((dato = sc.nextLine()).length() >= 1){
                                    coleccion.get(i).setTelefono(Integer.parseInt(dato));
                                    coleccion.get(i).setEstado(false);
                                }

                                System.out.println("Introduce el nuevo E-Mail");
                                if ((dato = sc.nextLine()).length() >= 1){
                                    coleccion.get(i).seteMail(dato);
                                    coleccion.get(i).setEstado(false);
                                }

                                System.out.println("Introduce el nuevo cumpleaños");
                                if (!(dato = sc.nextLine()).equals("")){
                                    String[] cumple = dato.split("/");
                                    coleccion.get(i).setCumpleanios(new Fecha(Integer.parseInt(cumple[0]), Integer.parseInt(cumple[1]), Integer.parseInt(cumple[2])));
                                    coleccion.get(i).setEstado(false);
                                }
                                break;
                            }
                        }
                        break;
                    case 4:
                        File file = new File("coleccion.dat");



                        try {
                            FileWriter fwr = new FileWriter(file);
                            fwr.write("");
                            fwr.close();
                            FileWriter fw = new FileWriter(file, true);
                            Iterator<Amigo> miIterator2 = coleccion.iterator();
                            while (miIterator2.hasNext()){
                                Amigo miAmigo = (Amigo) miIterator2.next();
                                fw.write(miAmigo.getuID()+ "{'" + miAmigo.getNombre() + "', '" + miAmigo.getTelefono() + "', '" + miAmigo.geteMail() + "', '" + miAmigo.getCumpleanios() + "', 'true'}\n");
                            }
                            fw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        salida = false;
                        System.out.println("Cambios guardados");
                        break;
                    case 5:
                        salida = false;
                        break;
                    default:
                        System.out.println("Opción no admitida, vuelve a intentarlo");
                        opcion = null;
                }
            } while(opcion == null);
        }
    }

    @Override
    public String toString() {

        String colorEstado = null;
        if (estado){
            colorEstado = "🟢";
        } else if(!estado){
            colorEstado = "🔴";
        }

        return uID+ " {" +
                "nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                ", eMail='" + eMail + '\'' +
                ", cumpleanios=" + cumpleanios + '}' + colorEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Fecha getCumpleanios() {
        return cumpleanios;
    }

    public void setCumpleanios(Fecha cumpleanios) {
        this.cumpleanios = cumpleanios;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Amigo.id = id;
    }
}