package cursoSpringBoot.domain;
//Clase POJO
public class Customer {
    //atributos
    private int ID;
    private String name;
    private String username;
    private String password;

    //Constructor
    public Customer(int id, String name, String username, String password) {
        ID = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    //Getters y Setters
    public int getID() {
        return ID;
    }

    public void setID(int id) {
        ID = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
