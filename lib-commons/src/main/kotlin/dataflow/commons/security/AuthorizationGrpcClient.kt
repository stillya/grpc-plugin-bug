package dataflow.commons.security

import dataflow.commons.AuthorizationServiceGetRuleByNameRequest
import dataflow.commons.AuthorizationServiceGrpc
import dataflow.commons.PermissionServiceGetPermissionByNameRequest
import dataflow.commons.PermissionServiceGrpc
import dataflow.commons.dtos.PermissionDto
import dataflow.commons.dtos.abac.AbacRule
import dataflow.commons.mappers.toDto

class AuthorizationGrpcClient(
    private val authStub: AuthorizationServiceGrpc.AuthorizationServiceBlockingStub,
    private val permissionStub: PermissionServiceGrpc.PermissionServiceBlockingStub?
) {

    fun getRuleByName(name: String): AbacRule {
        val request: AuthorizationServiceGetRuleByNameRequest =
            AuthorizationServiceGetRuleByNameRequest.newBuilder()
                .setName(name)
                .build()
        return authStub.getRuleByName(request).rule.toDto()
    }

    fun getPermissionByName(name: String): PermissionDto {
        val request = PermissionServiceGetPermissionByNameRequest.newBuilder().setName(name).build()
        return permissionStub!!.getPermissionByName(request).permission.toDto()
    }

}