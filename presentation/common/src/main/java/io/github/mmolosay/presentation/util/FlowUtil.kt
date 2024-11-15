package io.github.mmolosay.presentation.util

import io.github.mmolosay.presentation.util.struct.Resource
import io.github.mmolosay.presentation.util.struct.empty
import io.github.mmolosay.presentation.util.struct.success
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

@Suppress("FunctionName")
fun <V : Any> MutableSharedResourceFlow() =
    MutableSharedFlow<Resource<V>>()

@Suppress("FunctionName")
fun <V : Any> MutableStateResourceFlow(value: Resource<V>) =
    MutableStateFlow(value)

@Suppress("FunctionName")
fun <V : Any> MutableStateResourceFlow(value: V) =
    MutableStateFlow(Resource.success(value))

@Suppress("FunctionName")
fun <V : Any> MutableCommandFlow() =
    MutableStateFlow<Resource<V>>(Resource.empty())

@Suppress("FunctionName")
fun <V : Any> MutableCommandFlow(value: V) =
    MutableStateFlow(Resource.success(value))