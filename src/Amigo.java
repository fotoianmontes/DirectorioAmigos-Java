import java.io.*;

public class Amigo {
    private String nombre;
    private int telefono;
    private String eMail;
    private Fecha cumpleanios;
    private int uID;

    public Amigo(String nombre, int telefono, String eMail, Fecha cumpleanios){
        this.nombre = nombre;
        this.telefono = telefono;
        this.eMail = eMail;
        this.cumpleanios = cumpleanios;
        if (new File("coleccion.dat").exists()){
            uID = getHigherID();
        } else{
            uID = 1;
        }
    }

    private Integer getHigherID(){
        Integer miInt = null;
        try {
            FileReader fr = new FileReader("coleccion.dat");
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter("coleccion.dat", true);
            String line = "";
            while ((line = br.readLine()) != null){
                if (Integer.parseInt(line.substring(0,1)) >= id){
                    miInt = Integer.parseInt(line.substring(0,1)) + 1;
                }
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return miInt;
    }

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

    @Override
    public String toString() {
        return "Amigo{" +
                "nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                ", eMail='" + eMail + '\'' +
                ", cumpleanios=" + cumpleanios +
                ", uID=" + uID +
                '}';
    }

    public void Almacenar(){
        String usuarioFormateado = this.uID + " {nombre: '" + this.nombre + "', telefono: '" + this.telefono + "', eMail: '" + this.eMail + "', cumpleanios: '" + this.cumpleanios.toString() + "'}\n";
        File miColeccion = new File("coleccion.dat");
        if (miColeccion.exists()){
            try {
                FileWriter fw = new FileWriter(miColeccion, true);
                fw.write(usuarioFormateado);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileWriter fw = new FileWriter(miColeccion);
                fw.write(usuarioFormateado);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(usuarioFormateado);
        }
}
