package com.kerosenelabs.billtracker.argumentresolver

import com.kerosenelabs.billtracker.entity.UserEntity
import com.kerosenelabs.billtracker.exception.AuthException
import com.kerosenelabs.billtracker.service.UserService
import org.springframework.core.MethodParameter
import org.springframework.lang.Nullable
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import java.util.*

@Component
class UserArgumentResolver(private val userService: UserService) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return (parameter.parameterType == UserEntity::class.java)
                && (parameter.parameterName != null && parameter.parameterName == "user")
    }

    @Throws(Exception::class)
    override fun resolveArgument(
        parameter: MethodParameter,
        @Nullable mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        @Nullable binderFactory: WebDataBinderFactory?
    ): Any {
        // parse auth jwt
        var authHeader = webRequest.getHeader("Authorization")
            ?: throw AuthException("Authorization header is required")
        authHeader = authHeader.replace("Bearer ", "")
        val userId = userService.getIdFromJwt(authHeader)
        return userService.getUserById(UUID.fromString(userId))
    }
}
