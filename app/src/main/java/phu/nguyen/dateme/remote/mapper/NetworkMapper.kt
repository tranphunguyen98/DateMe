package phu.nguyen.dateme.remote.mapper

interface NetworkMapper<in M, out E> {
    fun mapFromRemote(type: M): E
}