package com.instafinancials.vendoralpha.deletellater.network.pojonew

import java.io.Serializable

data class RelatedParty (

	val companyName : String,
	val companyMcaStatus : String,
	val companyPaidUpCapital : Int
): Serializable