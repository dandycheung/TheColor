package com.ordolabs.domain.repository.color

import com.ordolabs.domain.model.color.HistoryColor
import kotlinx.coroutines.flow.Flow

interface ColorsHistoryRepository {

    fun getColorsFromHistory(): Flow<List<HistoryColor>>
}