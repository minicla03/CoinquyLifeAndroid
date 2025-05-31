package com.coinquyteam.authApplication.Utility

sealed class AuthStatus {
    object SUCCESS : AuthStatus()
    object USER_NOT_FOUND : AuthStatus()
    object INVALID_CREDENTIALS : AuthStatus()
    object INVALID_EMAIL : AuthStatus()
    object INVALID_PASSWORD : AuthStatus()
    object USER_ALREADY_EXISTS : AuthStatus()
    object UNAUTHORIZED : AuthStatus()
    object ERROR : AuthStatus()
}
