public class Main {
    public static void main(String[] args) {

        Amigo miAmigo = new Amigo("Ian", 123456789, "placeholder@gmail.com", new Fecha(9, 4, 1992));
        Amigo miAmigo2 = new Amigo("Iann", 123456789, "placeholder@gmail.com", new Fecha(9, 4, 1992));
        Amigo miAmigo3 = new Amigo("Iannn", 123456789, "placeholder@gmail.com", new Fecha(9, 4, 1992));

        miAmigo.Almacenar();
        miAmigo2.Almacenar();
        miAmigo3.Almacenar();
    }
}