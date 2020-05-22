package com.pretz.assembler

class AsmParser {

    private var varCount = 16

    fun parse(lines: List<String>) =
        lines.filterNot { it.isWhitespaceOrSymbol() }
            .map { it.trimTrailingWhitespace() }
            .map { parseToCommand(it) }

    private fun parseToCommand(line: String) =
        when {
            line.matches(Regex("^@.*")) -> parseToACommand(line)
            else -> parseToCCommand(line)
        }

    private fun parseToACommand(line: String) =
        ACommand(convertLabelToAddress(line))

    private fun parseToCCommand(line: String): CCommand {
        val dest = line.substringBeforeLast('=', String())
        val comp = line.substringAfterLast('=', line.substringBeforeLast(';'))
        val jump = line.substringAfterLast(';', String())
        return CCommand(dest, comp, jump)
    }

    private fun convertLabelToAddress(line: String) =
        if (line.isLabel())
            convertToAddress(line)
        else line.substring(1)

    private fun convertToAddress(line: String): String {
        if (!SymbolTable.symbolTable.containsKey(line.substring(1)))
            SymbolTable.symbolTable[line.substring(1)] = varCount++
        return SymbolTable.symbolTable[line.substring(1)].toString()
    }
}
