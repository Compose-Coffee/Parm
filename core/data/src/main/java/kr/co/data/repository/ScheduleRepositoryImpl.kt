package kr.co.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kr.co.data.mapper.ScheduleMapper
import kr.co.data.source.remote.ScheduleRemoteDataSource
import kr.co.domain.entity.ScheduleEntity
import kr.co.domain.repository.ScheduleRepository
import javax.inject.Inject

internal class ScheduleRepositoryImpl @Inject constructor(
    private val remote: ScheduleRemoteDataSource
) : ScheduleRepository {
    override suspend fun getSchedules(category: String, startDate: String): Flow<List<ScheduleEntity>> =
        remote.fetchList(category, startDate).transform {
            emit(
                it.map { scheduleData -> ScheduleMapper.convert(scheduleData) }
            )
        }

    override suspend fun getSchedules(
        category: String,
        year: Int,
        month: Int
    ): Flow<List<ScheduleEntity>> =
        remote.fetchList(category, year, month).transform {
            emit(
                it.map { scheduleData -> ScheduleMapper.convert(scheduleData) }
            )
        }

    override suspend fun getScheduleDetail(id: Long): ScheduleEntity =
        remote.fetchDetail(id).let { ScheduleMapper.convert(it) }

    override suspend fun createSchedule(
        category: String,
        title: String,
        startDate: String,
        endDate: String,
        memo: String
    ) {
        remote.create(
            category = category,
            title = title,
            startDate = startDate,
            endDate = endDate,
            memo = memo
        )
    }

    override suspend fun updateSchedule(
        id: Long,
        category: String,
        title: String,
        startDate: String,
        endDate: String,
        memo: String
    ) {
        remote.update(
            id = id,
            category = category,
            title = title,
            startDate = startDate,
            endDate = endDate,
            memo = memo
        )
    }

    override suspend fun deleteSchedule(id: Long) {
        remote.delete(id)
    }
}