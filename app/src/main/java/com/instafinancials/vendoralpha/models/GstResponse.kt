package com.instafinancials.vendoralpha.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class GstResponse : Serializable {
    @SerializedName("Response")
    val response: Response? = null
    @SerializedName("GSTInformationAndCompliance")
    val gSTInformationAndCompliance: GSTInformationAndCompliance? = null
    @SerializedName("CompanyMasterSummary")
    val companyMasterSummary: CompanyMasterSummary? = null
    @SerializedName("DirectorSignatoryMasterBasic")
    val directorSignatoryMasterBasic: DirectorSignatoryMasterBasic? = null
    @SerializedName("PotentialRelatedPartyMasterBasic")
    val potentialRelatedPartyMasterBasic: PotentialRelatedPartyMasterBasic? = null
}

class Response : Serializable {
    @SerializedName("Status")
    val status: String? = null
    @SerializedName("Message")
    val message: String? = null
}

class GSTInformationAndCompliance : Serializable {
    @SerializedName("MetaInfo")
    val metaInfo: MetaInfo? = null
    @SerializedName("GSTRegistrationDetails")
    val gSTRegistrationDetails: GSTRegistrationDetails? = null
}

class MetaInfo : Serializable {
    @SerializedName("Input")
    val input: String? = null
    @SerializedName("OrderedDateTime")
    val orderedDateTime: String? = null
    @SerializedName("DeliveredDateTime")
    val deliveredDateTime: String? = null
}

class CompanyMasterSummary : Serializable {
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

class DirectorSignatoryMasterBasic : Serializable {
    @SerializedName("DirectorCurrentMasterBasic")
    val directorCurrentMasterBasic: DirectorCurrentMasterBasic? = null
    @SerializedName("SignatoryCurrentMasterBasic")
    val signatoryCurrentMasterBasic: SignatoryCurrentMasterBasic? = null
}

class PotentialRelatedPartyMasterBasic : Serializable {
    @SerializedName("RelatedParty")
    val relatedParty: List<RelatedParty>? = null
}

class DirectorCurrentMasterBasic : Serializable {
    @SerializedName("Director")
    val director: List<Director>? = null
}

class SignatoryCurrentMasterBasic : Serializable {
    @SerializedName("Signatory")
    val signatory: List<Signatory>? = null
}

class Signatory : Serializable {
    @SerializedName("SignatoryName")
    val signatoryName: String? = null
    @SerializedName("SignatoryDesignation")
    val signatoryDesignation: String? = null
    @SerializedName("SignatoryDateOfAppnt")
    val signatoryDateOfAppnt: String? = null
}

class Director : Serializable {
    @SerializedName("DirectorName")
    val directorName: String? = null
    @SerializedName("DirectorDesignation")
    val directorDesignation: String? = null
    @SerializedName("DirectorDateOfAppnt")
    val directorDateOfAppnt: String? = null
}

class RelatedParty : Serializable {
    @SerializedName("CompanyName")
    val companyName: String? = null
    @SerializedName("CompanyMcaStatus")
    val companyMcaStatus: String? = null
    @SerializedName("CompanyPaidUpCapital")
    val companyPaidUpCapital: Long? = 0L
}

class GSTRegistrationDetails : Serializable {
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

class GSTComplianceLastest6Months : Serializable {
    @SerializedName("GSTComplianceRecord")
    val gSTComplianceRecord: List<GSTComplianceRecord>? = null
}

class GSTComplianceRecord : Serializable {
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



