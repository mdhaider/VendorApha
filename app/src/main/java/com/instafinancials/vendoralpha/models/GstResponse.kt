package com.instafinancials.vendoralpha.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GstResponse:Serializable {

    @SerializedName("GSTInformationAndCompliance")
    val gSTInformationAndCompliance: GSTInformationAndCompliance? = null
    @SerializedName("CompanyMasterSummary")
    val companyMasterSummary: CompanyMasterSummary? = null
    @SerializedName("DirectorSignatoryMasterBasic")
    val directorSignatoryMasterBasic: DirectorSignatoryMasterBasic? = null
    @SerializedName("PotentialRelatedPartyMasterBasic")
    val potentialRelatedPartyMasterBasic: PotentialRelatedPartyMasterBasic? = null
}

class GSTInformationAndCompliance {
    @SerializedName("GSTRegistrationDetails")
    val gSTRegistrationDetails: GSTRegistrationDetails? = null
}

class CompanyMasterSummary {
    @SerializedName("LastUpdatedDateTime")
    val lastUpdatedDateTime: String? = null
    @SerializedName("CompanyName")
    val companyName: String? = null
    @SerializedName("CompanyMcaStatus")
    val companyMcaStatus: String? = null
    @SerializedName("CompanyMcaIndustry")
    val companyMcaIndustry: String? = null
    @SerializedName("CompanyCategory")
    val companyCategory: String? = null
    @SerializedName("CompanyLastBsDate")
    val companyLastBsDate: String? = null
    @SerializedName("CompanyPaidUpCapital")
    val companyPaidUpCapital: String? = null
    @SerializedName("CompanyRegCity")
    val companyRegCity: String? = null
    @SerializedName("CompanyRegState")
    val companyRegState: String? = null
    @SerializedName("CompanyWebSite")
    val companyWebSite: String? = null
    @SerializedName("CompanyRevenueRange")
    val companyRevenueRange: String? = null
    @SerializedName("CompanyCIN")
    val companyCIN: String? = null
}

class DirectorSignatoryMasterBasic {
    @SerializedName("DirectorCurrentMasterBasic")
    val directorCurrentMasterBasic: DirectorCurrentMasterBasic? = null
    @SerializedName("SignatoryCurrentMasterBasic")
    val signatoryCurrentMasterBasic: SignatoryCurrentMasterBasic? = null
}

class PotentialRelatedPartyMasterBasic {
    @SerializedName("RelatedParty")
    val relatedParty: List<RelatedParty>? = null
}

class DirectorCurrentMasterBasic {
    @SerializedName("Director")
    val director: List<Director>? = null
}

class SignatoryCurrentMasterBasic {
    @SerializedName("Signatory")
    val signatory: List<Signatory>? = null
}

class Signatory {
    @SerializedName("SignatoryName")
    val signatoryName: String? = null
    @SerializedName("SignatoryDesignation")
    val signatoryDesignation: String? = null
    @SerializedName("SignatoryDateOfAppnt")
    val signatoryDateOfAppnt: String? = null
}


class Director {
    @SerializedName("DirectorName")
    val directorName: String? = null
    @SerializedName("DirectorDesignation")
    val directorDesignation: String? = null
    @SerializedName("DirectorDateOfAppnt")
    val directorDateOfAppnt: String? = null
}

class RelatedParty {
    @SerializedName("CompanyName")
    val companyName: String? = null
    @SerializedName("CompanyMcaStatus")
    val companyMcaStatus: String? = null
    @SerializedName("CompanyPaidUpCapital")
    val companyPaidUpCapital: Long? = 0L
}

class GSTRegistrationDetails {
    @SerializedName("LastUpdatedDateTime")
    val lastUpdatedDateTime: String? = null
    @SerializedName("GSTIN")
    val gSTIN: String? = null
    @SerializedName("LegalNameOfBusiness")
    val legalNameOfBusiness: String? = null
    @SerializedName("RegisteredState")
    val registeredState: String? = null
    @SerializedName("TaxpayerType")
    val taxpayerType: String? = null
    @SerializedName("EligibleToCollect")
    val eligibleToCollect: String? = null
    @SerializedName("FilingStatus")
    val filingStatus: String? = null
    @SerializedName("GSTNStatus")
    val gSTNStatus: String? = null
    @SerializedName("Constitution")
    val constitution: String? = null
    @SerializedName("GSTComplianceLastest6Months")
    val gSTComplianceLastest6Months: GSTComplianceLastest6Months? = null
}

class GSTComplianceLastest6Months {
    @SerializedName("GSTComplianceRecord")
    val gSTComplianceRecord: List<GSTComplianceRecord>? = null
}

class GSTComplianceRecord {

    @SerializedName("ReturnType")
    val returnType: String? = null
    @SerializedName("TaxPeriod")
    val taxPeriod: String? = null
    @SerializedName("FinancialYear")
    val financialYear: String? = null
    @SerializedName("FilingDate")
    val filingDate: String? = null
    @SerializedName("FilingStatus")
    val filingStatus: String? = null
    @SerializedName("DueDateForTheMonth")
    val dueDateForTheMonth: String? = null
}



