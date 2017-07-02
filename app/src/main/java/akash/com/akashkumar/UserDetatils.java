package akash.com.akashkumar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Akash on 7/2/2017.
 */

public class UserDetatils {

 private HashMap<String,UserData> hashMap = new HashMap<>();

    public HashMap<String, UserData> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, UserData> hashMap) {
        this.hashMap = hashMap;
    }
}
