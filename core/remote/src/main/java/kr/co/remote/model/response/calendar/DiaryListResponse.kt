package kr.co.remote.model.response.calendar

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class DiaryListResponse(
    @SerialName("response")
    val diaryList: List<DiaryResponse>
) {
    @Serializable
    data class DiaryResponse(
        val id: Int,
        val date: String,
        val holidayList: List<HolidayListResponse>,
        val weatherForecast: String,
        val workLaborer: Int,
        val workHours: Int,
        val workArea: Int,
        val workDescriptions: List<WorkDescriptionResponse>,
        val images: List<String>,
        val memo: String
    ) {
        @Serializable
        data class WorkDescriptionResponse(
            val type: String,
            val description: String
        )
    }
}