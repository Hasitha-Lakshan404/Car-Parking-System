package model;

public class Management {
    private String name;
    private String username;
    private String password;
    private String telNo;

    public Management() {
    }

    public Management(String name, String username, String password, String telNo) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.telNo = telNo;
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

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    @Override
    public String toString() {
        return "Management{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", telNo='" + telNo + '\'' +
                '}';
    }
}
