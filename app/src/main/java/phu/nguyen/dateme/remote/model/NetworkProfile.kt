package phu.nguyen.dateme.remote.model

data class NetworkProfile(
    var uid: String = "",
    val name: String = "",
    val birthday: String = "",
    val introduction: String = "",
    var images: List<String> = emptyList(),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
) {
    constructor() : this(
        "", "", "", "", emptyList(), 0.0, 0.0
    )
}