package com.semonemo.presentation.screen.login

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed interface LoginUiEvent {
    @Immutable
    data object AutoLogin : LoginUiEvent

    @Immutable
    data class RequiredRegister(
        val walletAddress: String,
    ) : LoginUiEvent
}
