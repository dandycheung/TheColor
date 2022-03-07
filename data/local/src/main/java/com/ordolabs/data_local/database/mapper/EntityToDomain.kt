package com.ordolabs.data_local.database.mapper

import com.ordolabs.data_local.database.model.ColorHistoryEntity
import com.ordolabs.domain.model.color.HistoryColor

fun ColorHistoryEntity.toDomain() = HistoryColor(
    a = "Fill me"
)