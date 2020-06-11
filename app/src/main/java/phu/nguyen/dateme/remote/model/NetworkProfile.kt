package phu.nguyen.dateme.remote.model

data class NetworkProfile(
    val id: String,
    val name: String,
    val age: Int,
    val introduction: String,
    val images: List<String>,
    val latitude: Double,
    val longitude: Double
) {
    constructor() : this(
        "", "", 0, "", emptyList(), 0.0, 0.0
    )
}