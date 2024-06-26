package kr.co.remote.model.request.account

import kotlinx.serialization.Serializable

@Serializable
internal data class UpdateAccountBookRequest(
    val id: Long,
    val transactionType: String,
    val amount: Long,
    val category: String,
    val title: String,
    val registerDateTime: String,
    val imageUrls: List<String>
)