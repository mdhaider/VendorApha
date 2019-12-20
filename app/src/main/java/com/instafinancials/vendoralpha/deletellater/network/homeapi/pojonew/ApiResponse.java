package com.instafinancials.vendoralpha.apicall.network.homeapi.pojonew;


public class ApiResponse implements Serializable {

    private GSTInformationAndCompliance gSTInformationAndCompliance;
    private CompanyMasterSummary companyMasterSummary;
    private DirectorSignatoryMasterBasic directorSignatoryMasterBasic;
    private PotentialRelatedPartyMasterBasic potentialRelatedPartyMasterBasic;

    public GSTInformationAndCompliance getGSTInformationAndCompliance() {
        return gSTInformationAndCompliance;
    }

    public void setGSTInformationAndCompliance(GSTInformationAndCompliance gSTInformationAndCompliance) {
        this.gSTInformationAndCompliance = gSTInformationAndCompliance;
    }

    public CompanyMasterSummary getCompanyMasterSummary() {
        return companyMasterSummary;
    }

    public void setCompanyMasterSummary(CompanyMasterSummary companyMasterSummary) {
        this.companyMasterSummary = companyMasterSummary;
    }

    public DirectorSignatoryMasterBasic getDirectorSignatoryMasterBasic() {
        return directorSignatoryMasterBasic;
    }

    public void setDirectorSignatoryMasterBasic(DirectorSignatoryMasterBasic directorSignatoryMasterBasic) {
        this.directorSignatoryMasterBasic = directorSignatoryMasterBasic;
    }

    public PotentialRelatedPartyMasterBasic getPotentialRelatedPartyMasterBasic() {
        return potentialRelatedPartyMasterBasic;
    }

    public void setPotentialRelatedPartyMasterBasic(PotentialRelatedPartyMasterBasic potentialRelatedPartyMasterBasic) {
        this.potentialRelatedPartyMasterBasic = potentialRelatedPartyMasterBasic;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("gSTInformationAndCompliance", gSTInformationAndCompliance).append("companyMasterSummary", companyMasterSummary).append("directorSignatoryMasterBasic", directorSignatoryMasterBasic).append("potentialRelatedPartyMasterBasic", potentialRelatedPartyMasterBasic).toString();
    }

}