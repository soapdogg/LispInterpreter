package com.soapdogg.lispInterpreter

import com.soapdogg.lispInterpreter.singleton.InterpreterSingleton
import java.util.*

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val interpreter = InterpreterSingleton.INSTANCE.interpreter
            val scanner = Scanner(System.`in`)
            val result = interpreter.interpret(scanner)
            println(result)
        } catch (e: Exception) {
            println(e.message)
        }
    }
}