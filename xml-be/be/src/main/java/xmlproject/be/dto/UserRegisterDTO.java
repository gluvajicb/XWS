package xmlproject.be.dto;

public class UserRegisterDTO {

    public String username;
    public String name;
    public String surname;
    public String email;
    public String password;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(String username, String name, String surname, String email, String password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}
