package com.shobhit.movieapp.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by abcplusd on 04/12/17.
 */

public class IdNameList extends RealmObject {
    private RealmList<IdName> idNameRealmList = new RealmList<>();

    public RealmList<IdName> getIdNameRealmList() {
        return idNameRealmList;
    }

    public void setIdNameRealmList(RealmList<IdName> idNameRealmList) {
        this.idNameRealmList = idNameRealmList;
    }
}
