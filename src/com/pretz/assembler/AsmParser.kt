package com.pretz.assembler

class AsmParser {
    fun parse(lines: Sequence<String>) =
        lines.filterNot { isWhitespace(it) }
            .map { parseToCommand(it) }
            .forEach { println(it) }

    private fun parseToCommand(line: String) =
        //TODO cut additional comment at the end of line
        when {
            line.matches(Regex("^@.*")) -> parseToACommand(line)
            //TODO line matches symbol - to be implemented
            else -> parseToCCommand(line)
        }

    private fun parseToACommand(line: String) =
        ACommand(line.substring(1))

    private fun parseToCCommand(line: String): CCommand {
        val dest = line.substringBeforeLast('=', String())
        val comp = line.substringAfterLast('=', line.substringBeforeLast(';'))
        val jump = line.substringAfterLast(';', String())
        return CCommand(dest, comp, jump)
    }

    private fun isWhitespace(line: String) =
        line.matches(Regex("^//.*")) || line.isBlank()

    // debug function
    fun printLines(lines: Sequence<String>) {
        lines.forEach { println(it) }
    }
}
