package com.instafinancials.vendoralpha.deletellater.network.homeapi.instabasic.pojo;

import java.io.Serializable;

public class Directorship implements Serializable
{
    private String CompanyCin;

    private String CompanyName;

    private String CompanyMcaIndustry;

    private String CompanyMcaStatus;

    private String CompanyDateOfInc;

    private String CompanyPaidUpCapital;

    public String getCompanyCin ()
    {
        return CompanyCin;
    }

    public void setCompanyCin (String CompanyCin)
    {
        this.CompanyCin = CompanyCin;
    }

    public String getCompanyName ()
    {
        return CompanyName;
    }

    public void setCompanyName (String CompanyName)
    {
        this.CompanyName = CompanyName;
    }

    public String getCompanyMcaIndustry ()
    {
        return CompanyMcaIndustry;
    }

    public void setCompanyMcaIndustry (String CompanyMcaIndustry)
    {
        this.CompanyMcaIndustry = CompanyMcaIndustry;
    }

    public String getCompanyMcaStatus ()
    {
        return CompanyMcaStatus;
    }

    public void setCompanyMcaStatus (String CompanyMcaStatus)
    {
        this.CompanyMcaStatus = CompanyMcaStatus;
    }

    public String getCompanyDateOfInc ()
    {
        return CompanyDateOfInc;
    }

    public void setCompanyDateOfInc (String CompanyDateOfInc)
    {
        this.CompanyDateOfInc = CompanyDateOfInc;
    }

    public String getCompanyPaidUpCapital ()
    {
        return CompanyPaidUpCapital;
    }

    public void setCompanyPaidUpCapital (String CompanyPaidUpCapital)
    {
        this.CompanyPaidUpCapital = CompanyPaidUpCapital;
    }

    @Override
    public String toString() {
        return "Directorship{" +
                "CompanyCin='" + CompanyCin + '\'' +
                ", CompanyName='" + CompanyName + '\'' +
                ", CompanyMcaIndustry='" + CompanyMcaIndustry + '\'' +
                ", CompanyMcaStatus='" + CompanyMcaStatus + '\'' +
                ", CompanyDateOfInc='" + CompanyDateOfInc + '\'' +
                ", CompanyPaidUpCapital='" + CompanyPaidUpCapital + '\'' +
                '}';
    }
}
