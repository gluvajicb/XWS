package xmlproject.be.dto;

public class UserDTO {
	public String username;
    public String name;
    public String surname;
    public String email;
    public String id;
	public UserDTO(String username, String name, String surname, String email, String id) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.email = email;
	}
}
