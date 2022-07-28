package dataflow.commons.dtos

data class PermissionDto(
    val name: String? = "",
    val description: String? = "",
    val roles: List<String>? = mutableListOf()
)