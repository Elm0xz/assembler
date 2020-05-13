package com.pretz.assembler

import java.io.File

fun main(args: Array<String>) {

    File(args[0].validate()).useLines { AsmParser().parse(it) }

    //TODO 3. translate assembly language commands into machine language
    //TODO 4. modify program to handle symbols
}

fun String.validate() =
    if (!this.matches(Regex(".*\\.asm\$")))
        throw IllegalArgumentException("Wrong file extension - only .asm files are allowed")
    else this
