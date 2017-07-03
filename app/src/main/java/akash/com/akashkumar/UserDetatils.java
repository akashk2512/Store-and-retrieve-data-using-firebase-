package akash.com.akashkumar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Akash on 7/2/2017.
 */

public class UserDetatils {

 private List<UserData> userList = new ArrayList<>();

    public List<UserData> getUserList() {
        return userList;
    }

    public void setUserList(List<UserData> userList) {
        this.userList = userList;
    }
}
