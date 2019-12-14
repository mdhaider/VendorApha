package com.instafinancials.vendoralpha.apicall.network.homeapi.instabasic.pojo;

import java.io.Serializable;

public class InstaBasicCINResponse implements Serializable
    {
        private InstaBasic InstaBasic;

        public InstaBasic getInstaBasic ()
        {
            return InstaBasic;
        }

        public void setInstaBasic (InstaBasic InstaBasic)
        {
            this.InstaBasic = InstaBasic;
        }

        @Override
        public String toString()
        {
            return "InstaBasic = "+InstaBasic+"";
        }
    }

