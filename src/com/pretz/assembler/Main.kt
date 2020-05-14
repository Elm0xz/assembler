package com.pretz.assembler

import java.io.BufferedWriter
import java.io.File

fun main(args: Array<String>) {

    File(args[0].validate())
        .readLines()
        .let { AsmParser().parse(it) }
        .let { CodeTranslator().translate(it) }
        .let { File(outputFileName(args[0])).bufferedWriter().use { out -> printToFile(it, out) } }
    //TODO 4. modify program to handle symbols
}

fun String.validate() =
    if (!this.matches(Regex(".*\\.asm\$")))
        throw IllegalArgumentException("Wrong file extension - only .asm files are allowed")
    else this

private fun outputFileName(inputFileName: String) =
    inputFileName.substringBeforeLast('.') + "1.hack"

private fun printToFile(code: List<String>, out: BufferedWriter) =
    code.forEach { printLine(out, it) }

private fun printLine(out: BufferedWriter, line: String) =
    out.write(line + "\n")
