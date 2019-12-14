package com.instafinancials.vendoralpha.apicall.network.homeapi.instabasic.pojo;

import java.io.Serializable;
import java.util.Arrays;

public class DirectorAllDirectorshipMasterBasic implements Serializable {
    private Director[] Director;

    @Override
    public String toString() {
        return "DirectorAllDirectorshipMasterBasic{" +
                "Director=" + Arrays.toString(Director) +
                '}';
    }
}
