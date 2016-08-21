package f.lucia.tournamentmakerapp;

/**
 * Created by Nel on 2015-12-01.
 */
public class User {


    private String user;
    private String pass;
    private String userType;

    public User(){}
    public User(String username, String password,String uType ){

        user = username;
        pass = password;
        userType = uType;

    }

    public String getPassword(){
        return pass;
    }

    public void setPassword(String newPassword){
        pass=newPassword;
    }

    public String getUser() {
        return user;
    }

    public String getUserType() {
        return userType;
    }

}
