package kr.co.main.model.calendar

import kr.co.main.model.calendar.type.WorkDescriptionModelType
import java.time.LocalDate

internal data class DiaryModel(
    val id: Int,
    val crop: CropModel,
    val registerDate: LocalDate,
    val holidays: List<HolidayModel> = emptyList(),
    val weatherForecast: WeatherForecastModel,
    val workLaborer: Int = 0,
    val workHours: Int = 0,
    val workArea: Int = 0,
    val workDescriptions: List<WorkDescriptionModel> = emptyList(),
    val images: List<String> = emptyList(),
    val memo: String = ""
) {
    data class WorkDescriptionModel(
        val id: Int,
        val type: WorkDescriptionModelType,
        val description: String
    )
}

