package com.ordolabs.domain.usecase

/**
 * Basic use case. Takes one parameter of [Parameter] type and returns a [Result].
 */
interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(param: Parameter): Result
}