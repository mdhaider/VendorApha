package com.instafinancials.vendoralpha.deletellater.network.pojonew

import java.io.Serializable

data class GSTRegistrationDetails (

	val lastUpdatedDateTime : String,
	val gSTIN : String,
	val legalNameOfBusiness : String,
	val registeredState : String,
	val taxpayerType : String,
	val gSTNStatus : String,
	val constitution : String,
	val gSTComplianceLastest6Months : GSTComplianceLastest6Months
): Serializable