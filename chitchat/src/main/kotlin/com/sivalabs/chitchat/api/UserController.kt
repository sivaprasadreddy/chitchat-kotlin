package com.sivalabs.chitchat.api

import com.sivalabs.chitchat.domain.JwtTokenHelper
import com.sivalabs.chitchat.domain.UserService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
class UserController(
    private val userService: UserService,
    private val jowTokenHelper: JwtTokenHelper,
    private val userMapper: UserMapper,
) {
    @PostMapping("/api/login")
    @Operation(summary = "Authenticate user")
    @ApiResponses(
        ApiResponse(responseCode = "200", description = "Returns successful authentication response"),
        ApiResponse(responseCode = "401", description = "Invalid credentials"),
    )
    fun login(
        @Valid @RequestBody req: LoginRequest,
    ): LoginResponse {
        val user = userService.login(req.email!!, req.password!!) ?: throw BadCredentialsException("Invalid credentials")
        val userDto = userMapper.toUserDto(user)

        val jwtToken = jowTokenHelper.generateToken(userDto)
        return LoginResponse(
            jwtToken.token,
            jwtToken.expiresAt,
            user.name,
            user.email,
            user.role.name,
        )
    }
}

data class LoginRequest(
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Invalid email address")
    val email: String?,
    @field:NotBlank(message = "Password is required")
    val password: String?,
)

data class LoginResponse(
    val token: String,
    val expiresAt: Instant,
    val name: String,
    val email: String,
    val role: String,
)
