package kr.co.domain.usecase.user

import kotlinx.coroutines.flow.first
import kr.co.domain.entity.UserEntity
import kr.co.domain.repository.UserRepository
import kr.co.domain.usecase.SuspendUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterInfoUseCase @Inject constructor(
    private val userRepository: UserRepository,
) : SuspendUseCase<UserEntity, Unit>() {
    override suspend fun build(params: UserEntity?) {
        checkNotNull(params)

        userRepository.fetchLocal().first().let {
            it.copy(
                address = params.address ?: it.address,
                bjdCode = params.bjdCode ?: it.bjdCode,
                longitude = params.longitude ?: it.longitude,
                latitude = params.latitude ?: it.latitude,
                crops = params.crops ?: it.crops
            )
        }.also { user ->
            runCatching {
                userRepository.update(user)
            }.onSuccess {
                userRepository.save(user)
            }
        }
    }
}