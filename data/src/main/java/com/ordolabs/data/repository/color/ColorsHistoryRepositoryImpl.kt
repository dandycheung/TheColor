package com.ordolabs.data.repository.color

import com.ordolabs.data_local.database.dao.ColorsHistoryDao
import com.ordolabs.data_local.database.mapper.toDomain
import com.ordolabs.domain.model.color.HistoryColor
import com.ordolabs.domain.repository.color.ColorsHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ColorsHistoryRepositoryImpl(
    private val colorsHistoryDao: ColorsHistoryDao
) : ColorsHistoryRepository {

    override fun getColorsFromHistory(): Flow<List<HistoryColor>> = flow {
        val colors = colorsHistoryDao.getAll()
        emit(colors.map { it.toDomain() })
    }
}