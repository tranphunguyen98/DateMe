package phu.nguyen.dateme.data.mapper

interface UIMapper<in M, out E> {
    fun mapFromUI(type: M): E
}