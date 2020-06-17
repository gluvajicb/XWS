package xmlproject.be.security;

import java.util.List;

public class UserTokenState {

    private String accessToken;
    private Long expiresIn;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private List<String> roles;

    public UserTokenState() {
    }

    public UserTokenState(String token, long expiresIn) {
        this.accessToken = token;
        this.expiresIn = expiresIn;
    }

    public UserTokenState(String accessToken, Long expiresIn, String type, String id, String username, String email, List<String> roles) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.type = type;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
