package com.shobhit.movieapp.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by abcplusd on 04/12/17.
 */

public class IdName  extends RealmObject{
    public static String ID_KEY = "id";
    @PrimaryKey
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    private boolean isLiked;
}
