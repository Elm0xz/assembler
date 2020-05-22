package com.pretz.assembler

internal fun String.isWhitespaceOrSymbol() =
    this.isWhitespace() || this.isSymbol()

internal fun String.isWhitespace() =
    this.matches(Regex("^//.*")) || this.isBlank()

internal fun String.isSymbol() =
    this.matches(Regex("^\\(.*"))

internal fun String.isLabel() =
    !this[1].isDigit()

internal fun String.trimTrailingWhitespace() =
    this.substringBeforeLast("//").trim()
