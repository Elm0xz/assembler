package com.pretz.assembler

class AsmParser {

    fun parse(lines: List<String>) =
        lines.filterNot { isWhitespace(it) }
            .map { trimTrailingWhitespace(it) }
            .map { parseToCommand(it) }

    private fun isWhitespace(line: String) =
        line.matches(Regex("^//.*")) || line.isBlank()

    private fun trimTrailingWhitespace(line: String) = line.substringBeforeLast("//").trim()

    private fun parseToCommand(line: String) =
        when {
            line.matches(Regex("^@.*")) -> parseToACommand(line)
            else -> parseToCCommand(line)
        }

    private fun parseToACommand(line: String) =
        //TODO if address is symbol present in table, translate it according to symbol table
        //TODO if address in symbol not present in table, add it to table with next number (starting at address 16)
        ACommand(line.substring(1))

    private fun parseToCCommand(line: String): CCommand {
        val dest = line.substringBeforeLast('=', String())
        val comp = line.substringAfterLast('=', line.substringBeforeLast(';'))
        val jump = line.substringAfterLast(';', String())
        return CCommand(dest, comp, jump)
    }
}
