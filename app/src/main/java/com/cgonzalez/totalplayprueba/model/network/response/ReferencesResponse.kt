package com.cgonzalez.totalplayprueba.model.network.response


data class ResponseData(
    val status: Int = 0,
    val arrayReferences: List<ArrayReference> = emptyList()
)

data class Image(
    val url3X3: String? = null,
    val url4X4: String? = null,
    val url5X5: String? = null,
    val url6X6: String? = null,
    val url7X7: String? = null
)

data class ArrayReference(
    val images: List<Image> = emptyList(),
    val bank: String? = null,
    val reference: String? = null,
    val aliasbank: String? = null
)
