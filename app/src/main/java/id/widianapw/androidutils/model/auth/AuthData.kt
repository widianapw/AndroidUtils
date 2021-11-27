package id.widianapw.androidutils.model.auth

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

/**
 * Created by Widiana Putra on 10/11/21
 * Copyright (c) PT. TIMEDOOR INDONESIA
 */
data class AuthResponse(val data: AuthItemResponse)

data class AuthItemResponse(
    val access_token: String? = null,

    val refreshToken: String? = null,
//
//    @field:SerializedName("admin_data")
//    val adminData: AdminData? = null
)

 class AdminData  {
    @field:SerializedName("address")
    var address: String? = null

    @field:SerializedName("gender")
    var gender: String? = null

    @field:SerializedName("phone")
    var phone: String? = null

    @field:SerializedName("phone_code")
    var phoneCode: String? = null

    @field:SerializedName("birth_date")
    var birthDate: String? = null

    @field:SerializedName("name")
    var name: String? = null


    @field:SerializedName("id")
    var id: Int? = null

    @field:SerializedName("company_id")
    var businessId: String? = null

    @field:SerializedName("company_type")
    var companyType: String? = null

    @field:SerializedName("email")
    var email: String? = null

    @field:SerializedName("store_id")
    var branchId: Int? = null

    @field:SerializedName("store_name")
    var branchName: String? = null

    @field:SerializedName("image_id")
    var imageId: Int? = null

    @field:SerializedName("permissions")
    var permissions: List<String>? = null

    @field:SerializedName("company")
    var company: CompanyData? = null

    @field:SerializedName("role")
    var role: String? = null
}

data class CompanyData (
    @field:SerializedName("id")
    var id: String? = null,

    @field:SerializedName("name")
    var name: String? = null,

    @field:SerializedName("type")
    var type: String? = null
)

data class AuthRequest(
    val credential: String,
    val password: String
)