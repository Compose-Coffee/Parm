package kr.co.data.source.remote

import kotlinx.coroutines.flow.Flow
import kr.co.data.model.data.calendar.DiaryData
import kr.co.data.model.data.calendar.HolidayData
import java.time.LocalDate

interface DiaryRemoteDataSource {
    suspend fun fetchList(
        crop: String,
        year: String,
        month: String
    ): Flow<List<DiaryData>>

    suspend fun fetchDetail(id: Long): DiaryData

    suspend fun searchList(
        crop: String,
        query: String,
        startDate: String,
        endDate: String
    ): Flow<List<DiaryData>>

    suspend fun update(
        id: Long,
        crop: String,
        date: String,
        holidayList: List<HolidayData>,
        weatherForecast: String,
        workLaborer: Int,
        workHours: Int,
        workArea: Int,
        workDescriptions: List<DiaryData.WorkDescriptionData>,
        imageUrls: List<String>,
        memo: String
    )

    suspend fun delete(id: Long)

    suspend fun create(
        crop: String,
        date: LocalDate,
        holidayList: List<HolidayData>,
        weatherForecast: String,
        imageUrls: List<String>,
        workLaborer: Int,
        workHours: Int,
        workArea: Int,
        workDescriptions: List<DiaryData.WorkDescriptionData>,
        memo: String
    )
}