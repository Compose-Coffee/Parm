package kr.co.remote.model.response.calendar

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class HolidayListResponse(
    val holidayList: List<HolidayResponse>
){
    @Serializable
    data class HolidayResponse(
        @SerialName("localDate")
        val date: String,
        @SerialName("dateName")
        val name:String,
        @SerialName("dateKind")
        val type:String,
        val isHoliday: String
    )
}