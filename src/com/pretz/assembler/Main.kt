package com.pretz.assembler

import java.io.BufferedWriter
import java.io.File

fun main(args: Array<String>) {

    val inputFileName = args[0]

    File(inputFileName.validate())
        .readLines()
        .also { SymbolTable.addCustom(it) }
        .let { AsmParser().parse(it) }
        .let { CodeTranslator().translate(it) }
        .let { File(outputFileNameFrom(inputFileName)).bufferedWriter().use { out -> printToFile(it, out) } }
}

private fun String.validate() =
    if (!this.matches(Regex(".*\\.asm\$")))
        throw IllegalArgumentException("Wrong file extension - only .asm files are allowed")
    else this

private fun outputFileNameFrom(inputFileName: String) =
    inputFileName.substringBeforeLast('.') + "1.hack"

private fun printToFile(machineCode: List<String>, out: BufferedWriter) =
    machineCode.forEach { printLine(out, it) }

private fun printLine(out: BufferedWriter, line: String) =
    out.write(line + "\n")
