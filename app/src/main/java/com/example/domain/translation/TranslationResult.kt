package com.example.domain.translation

data class TranslationResult(
    val tamilText: String,
    val isSuccess: Boolean = true,
    val source: TranslationSource = TranslationSource.FALLBACK,
    val errorMessage: String? = null
)
