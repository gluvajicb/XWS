package xmlproject.be.dto;

public class UserLoginDTO {

    public String username;
    public String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
