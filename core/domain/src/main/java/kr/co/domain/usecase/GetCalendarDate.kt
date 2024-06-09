package kr.co.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.flow.Flow
import kr.co.domain.entity.CropEntity

interface GetCalendarDate {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(crop: CropEntity, year: Int, month: Int): Flow<List<kr.co.domain.entity.CalendarDateEntity>>
}