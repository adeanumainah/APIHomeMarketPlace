package com.dean.apihomemarketplace.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseHome (
        @SerializedName("status")
        @Expose
        var status: Int? = null,

        @SerializedName("data")
        @Expose
        var data: List<DataItem>? = null
)

data class DataItem (
        @SerializedName("id")
        @Expose
        var id: Int? = null,

        @SerializedName("name")
        @Expose
        var name: String? = null,

        @SerializedName("address")
        @Expose
        var address: String? = null,

        @SerializedName("price")
        @Expose
        var price: Int? = null,

        @SerializedName("desc")
        @Expose
        var desc: String? = null,

        @SerializedName("type")
        @Expose
        var type: String? = null,

        @SerializedName("developer")
        @Expose
        var developer: String? = null,

        @SerializedName("property_facilities")
        @Expose
        var propertyFacilities: String? = null,

        @SerializedName("certificate")
        @Expose
        var certificate: String? = null,

        @SerializedName("furnished")
        @Expose
        var furnished: String? = null,

        @SerializedName("number_of_floors")
        @Expose
        var numberOfFloors: Int? = null,

        @SerializedName("surface_area")
        @Expose
        var surfaceArea: String? = null,

        @SerializedName("image")
        @Expose
        var image: String? = null,

        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null,

        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null,

        @SerializedName("deleted_at")
        @Expose
        var deletedAt: String? = null
)

