package com.instafinancials.vendoralpha.deletellater.network.pojonew

import java.io.Serializable

data class ApiResponse (
	val gSTInformationAndCompliance : GSTInformationAndCompliance,
	val companyMasterSummary : CompanyMasterSummary,
	val directorSignatoryMasterBasic : DirectorSignatoryMasterBasic,
	val potentialRelatedPartyMasterBasic : PotentialRelatedPartyMasterBasic
):Serializable