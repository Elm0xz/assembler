package com.pretz.assembler

class CodeTranslator {
    fun translate(commands: List<Command>) =
        commands.map { translate(it) }

    private fun translate(command: Command) =
        when (command) {
            is ACommand -> translateA(command)
            is CCommand -> translateC(command)
        }

    private fun translateA(aCommand: ACommand): String {
        val address = aCommand.symbol.toInt()
            .toString(2)
        return String(
            address.toCharArray(
                destination = CharArray(16) { '0' },
                destinationOffset = 16 - address.length
            )
        )
    }

    private fun translateC(cCommand: CCommand): String {
        val prefix = "111"
        val comp = comp(cCommand.comp)
        val dest = dest(cCommand.dest)
        val jump = jump(cCommand.jump)
        return prefix + comp + dest + jump
    }

    private fun comp(comp: String) =
        Fields.comp[comp]

    private fun dest(dest: String?) =
        if (dest == "") "000"
        else
            Fields.dest[dest]

    private fun jump(jump: String?) =
        if (jump == "") "000"
        else
            Fields.jump[jump]
}
