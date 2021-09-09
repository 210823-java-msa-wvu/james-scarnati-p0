package dev.scarnati.model;

public class Accounts {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String pos;

    public Accounts(){

    }

    public Accounts(String pos) {
        this.pos = pos;
    }
    public Accounts(String username, String password, String email, String pos){
        this.username = username;
        this.email = email;
        this.pos = pos;
    }
    public Accounts(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Accounts(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Accounts(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public Accounts(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "Account: " + id + " " + username + " " + email + " " + password;
    }
}
