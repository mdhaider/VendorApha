package com.instafinancials.vendoralpha.deletellater.network.homeapi.instabasic.pojo;

import java.io.Serializable;

public class Director implements Serializable {
    private String DirectorDin;

    private Directorship Directorship;

    private String DirectorName;

    @Override
    public String toString() {
        return "Director{" +
                "DirectorDin='" + DirectorDin + '\'' +
                ", Directorship=" + Directorship +
                ", DirectorName='" + DirectorName + '\'' +
                '}';
    }
}
