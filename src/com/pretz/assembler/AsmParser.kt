package com.pretz.assembler

class AsmParser {

    private var varCount = 16

    fun parse(lines: List<String>) =
        lines.filterNot { isWhitespaceOrSymbol(it) }
            .map { trimTrailingWhitespace(it) }
            .map { parseToCommand(it) }

    private fun isWhitespace(line: String) =
        line.matches(Regex("^//.*")) || line.isBlank()

    private fun isWhitespaceOrSymbol(line: String) =
        line.matches(Regex("^//.*")) || line.isBlank()
                || line.matches(Regex("^\\(.*"))

    private fun trimTrailingWhitespace(line: String) = line.substringBeforeLast("//").trim()

    private fun parseToCommand(line: String) =
        when {
            line.matches(Regex("^@.*")) -> parseToACommand(line)
            else -> parseToCCommand(line)
        }

    private fun parseToACommand(line: String) =
        ACommand(convertLabelToAddress(line))

    private fun convertLabelToAddress(line: String) =
        if (isLabel(line))
            convertToAddress(line)
        else line.substring(1)

    private fun isLabel(line: String) =
        !line[1].isDigit()

    private fun convertToAddress(line: String): String {
        if (!SymbolTable.symbolTable.containsKey(line.substring(1)))
            SymbolTable.symbolTable[line.substring(1)] = varCount++
        return SymbolTable.symbolTable[line.substring(1)].toString()
    }

    private fun parseToCCommand(line: String): CCommand {
        val dest = line.substringBeforeLast('=', String())
        val comp = line.substringAfterLast('=', line.substringBeforeLast(';'))
        val jump = line.substringAfterLast(';', String())
        return CCommand(dest, comp, jump)
    }
}
