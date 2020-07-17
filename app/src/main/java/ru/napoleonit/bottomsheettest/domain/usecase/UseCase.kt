package ru.napoleonit.bottomsheettest.domain.usecase

interface UseCase<C, P> {

    suspend operator fun invoke(params: P): C
}