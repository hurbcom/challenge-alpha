package br.com.gstoduto.starwars.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.gstoduto.starwars.model.Vehicle

@Entity(tableName = "vehicles")
class VehicleEntity(
    @PrimaryKey
    @ColumnInfo(name = "id", defaultValue = "0") var id: Long,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "model") var model: String,
    @ColumnInfo(name = "manufacturer") var manufacturer: String,
    @ColumnInfo(name = "vehicle_class") var vehicleClass: String?  = null,
    @ColumnInfo(name = "cost_in_credits") var costInCredits: String?  = null,
    @ColumnInfo(name = "max_atmosphering_speed") var maxAtmospheringSpeed: String?  = null,
    @ColumnInfo(name = "length") var length: String,
    @ColumnInfo(name = "cargo_capacity", defaultValue = "") var cargoCapacity: String? = null,
    @ColumnInfo(name = "consumables", defaultValue = "") var consumables: String,
    @ColumnInfo(name = "passengers") var passengers: String,
    @ColumnInfo(name = "url") var url: String,
    @ColumnInfo(name = "inMyList") var inMyList: Boolean = false,
)

fun VehicleEntity.toVehicle() = Vehicle(
    id = id,
    name = name,
    model = model,
    manufacturer = manufacturer,
    vehicleClass = vehicleClass,
    costInCredits = costInCredits,
    maxAtmospheringSpeed = maxAtmospheringSpeed,
    length = length,
    cargoCapacity = cargoCapacity,
    consumables = consumables,
    passengers = passengers,
    url = url,
    inMyList = inMyList
)
