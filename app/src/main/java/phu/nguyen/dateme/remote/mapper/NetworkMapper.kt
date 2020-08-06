package phu.nguyen.dateme.remote.mapper

interface NetworkMapper<M,E> {
    fun mapFromRemote(type: M): E
    fun mapToRemote(type: E): M
}