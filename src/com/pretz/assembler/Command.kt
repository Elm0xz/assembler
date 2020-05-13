package com.pretz.assembler

sealed class Command
data class CCommand(val dest: String?,
                    val comp: String,
                    val jump: String?) : Command()
data class ACommand(val symbol: String) : Command()
