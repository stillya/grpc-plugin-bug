package dataflow.commons.dtos

import dataflow.commons.security.AuthorizationGrpcClient
import java.util.*

data class UserDto(val id: UUID, val username: String, val roles: List<String>) {
    fun hasPermission(
        authorizationGrpcClient: AuthorizationGrpcClient,
        permissionName: String
    ): Boolean =
        authorizationGrpcClient.getPermissionByName(permissionName).roles!!.any { p ->
            roles.contains(
                p
            )
        }

}