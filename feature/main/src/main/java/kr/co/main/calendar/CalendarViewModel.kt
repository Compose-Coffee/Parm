package kr.co.main.calendar

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kr.co.common.util.combine
import kr.co.domain.entity.DiaryEntity
import kr.co.domain.entity.FarmWorkEntity
import kr.co.domain.entity.HolidayEntity
import kr.co.domain.entity.ScheduleEntity
import kr.co.ui.base.BaseViewModel
import java.time.LocalDate
import javax.inject.Inject

internal data class CalendarScreenState(
    val userCrops: List<DreamCrop> = emptyList(),
    val selectedCrop: DreamCrop? = null,

    val year: Int = LocalDate.now().year,
    val month: Int = LocalDate.now().monthValue,

    val farmWorks: List<FarmWorkEntity> = emptyList(),
    val holidays: List<HolidayEntity> = emptyList(),
    val schedules: List<ScheduleEntity> = emptyList(),
    val diaries: List<DiaryEntity> = emptyList()
): BaseViewModel.State

internal interface CalendarScreenInput {
    fun onSelectCrop(crop: DreamCrop)
    fun onSelectMonth(month: Int)
}

@HiltViewModel
internal class CalendarViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<CalendarScreenState>(savedStateHandle), CalendarScreenInput {
    private val TAG = this@CalendarViewModel::class.java.simpleName

    private val _userCrops = MutableStateFlow<List<DreamCrop>>(emptyList())
    private val _selectedCrop = MutableStateFlow<DreamCrop?>(null)

    private val _year = MutableStateFlow(LocalDate.now().year)
    private val _month = MutableStateFlow(LocalDate.now().monthValue)

    private val _farmWorks = MutableStateFlow<List<FarmWorkEntity>>(emptyList())
    private val _holidays = MutableStateFlow<List<HolidayEntity>>(emptyList())
    private val _schedules = MutableStateFlow<List<ScheduleEntity>>(emptyList())
    private val _diaries = MutableStateFlow<List<DiaryEntity>>(emptyList())

    private val _state = MutableStateFlow(CalendarScreenState())

    val input = this@CalendarViewModel

    init {
        combine(
            _userCrops,
            _selectedCrop,
            _year,
            _month,
            _farmWorks,
            _holidays,
            _schedules,
            _diaries
        ) { userCrops, selectedCrop,
            year, month,
            farmWorks, holidays, schedules, diaries ->
            _state.update {
                CalendarScreenState(
                    userCrops = userCrops,
                    selectedCrop = selectedCrop,
                    year = year,
                    month = month,
                    farmWorks = farmWorks,
                    holidays = holidays,
                    schedules = schedules,
                    diaries = diaries
                )
            }
        }.launchIn(viewModelScopeEH)
    }

    override fun onSelectCrop(crop: DreamCrop) {
        _selectedCrop.update { crop }
    }

    override fun onSelectMonth(month: Int) {
        // TODO 캘린더 년도 & 월 변경
    }

    override fun createInitialState(savedState: Parcelable?) = CalendarScreenState()
}