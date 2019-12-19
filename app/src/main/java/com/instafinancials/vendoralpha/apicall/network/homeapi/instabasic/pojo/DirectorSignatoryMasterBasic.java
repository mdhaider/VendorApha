package com.instafinancials.vendoralpha.apicall.network.homeapi.instabasic.pojo;

import java.io.Serializable;

public class DirectorSignatoryMasterBasic implements Serializable {
    private DirectorCurrentMasterBasic DirectorCurrentMasterBasic;

    private DirectorAllDirectorshipMasterBasic DirectorAllDirectorshipMasterBasic;

    private String LastUpdatedDateTime;

    @Override
    public String toString() {
        return "DirectorSignatoryMasterBasic{" +
                "DirectorCurrentMasterBasic=" + DirectorCurrentMasterBasic +
                ", DirectorAllDirectorshipMasterBasic=" + DirectorAllDirectorshipMasterBasic +
                ", LastUpdatedDateTime='" + LastUpdatedDateTime + '\'' +
                '}';
    }
}
