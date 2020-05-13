package com.pretz.assembler

class AsmParser {
    fun parse(lines: Sequence<String>): Sequence<Command> =
        lines.filter { isWhitespace(it) }
            .map { Command() }

    private fun isWhitespace(line: String) = true
}
