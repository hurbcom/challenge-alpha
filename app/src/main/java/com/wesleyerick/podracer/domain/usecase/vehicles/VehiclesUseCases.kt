package com.wesleyerick.podracer.domain.usecase.vehicles

data class VehiclesUseCases(
    val getList: GetAllVehiclesUseCase,
    val getDetails: GetVehicleDetailsUseCase,
)
