package com.instafinancials.vendoralpha.deletellater.network.homeapi.instabasic.pojo;

import java.io.Serializable;
import java.util.Arrays;

public class DirectorCurrentMasterBasic implements Serializable {
    private Director[] Director;

    @Override
    public String toString() {
        return "DirectorCurrentMasterBasic{" +
                "Director=" + Arrays.toString(Director) +
                '}';
    }
}
