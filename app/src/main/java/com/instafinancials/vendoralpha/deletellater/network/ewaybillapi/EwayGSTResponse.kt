package com.instafinancials.vendoralpha.deletellater.network.ewaybillapi

import java.io.Serializable

data class EwayGSTResponse (
    val gstNumber : String,

    val address : String,

    val related : Array<String>,

    val pinCode : String,

    val name : String,

    val billState : String,

    val cancelled : String,

    val state : String,

    val fromLocation : String
) : Serializable