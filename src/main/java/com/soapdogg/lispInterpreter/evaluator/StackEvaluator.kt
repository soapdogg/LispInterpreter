package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.datamodels.TokenKind
import com.soapdogg.lispInterpreter.determiner.NumericStringDeterminer
import com.soapdogg.lispInterpreter.generator.NodeGenerator
import java.util.*

class StackEvaluator (
    private val nodeGenerator: NodeGenerator,
    private val numericStringDeterminer: NumericStringDeterminer
){

    fun evaluate(
        tokenStack: Stack<Token>
    ): Stack<Token> {
        val resultStack = Stack<Token>()
        while(tokenStack.isNotEmpty()) {
            val current = tokenStack.peek()
            if (current.tokenKind == TokenKind.CLOSE_TOKEN) {
                val innerStack = Stack<Token>()
                while(tokenStack.peek().tokenKind != TokenKind.OPEN_TOKEN) {
                    innerStack.push(tokenStack.pop())
                }
                tokenStack.pop() // remove OpenToken
                val evaluatedExpr = performOperation(innerStack)
                while(evaluatedExpr.isNotEmpty()) {
                    tokenStack.push(evaluatedExpr.pop())
                }
            } else {
                resultStack.push(tokenStack.pop())
            }
        }
        return resultStack
    }

    fun performOperation(
        s: Stack<Token>
    ): Stack<Token> {
        val addressValue = s.pop()
        when (addressValue.value) {
            FunctionNameConstants.ATOM -> {
                s.pop() // atom
                val tempStack = Stack<Token>()
                while(s.peek().tokenKind != TokenKind.CLOSE_TOKEN) {
                    tempStack.push(s.pop())
                }
                s.pop() //closeToken
                if (tempStack.isEmpty()) {
                    s.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.T))
                } else {
                    s.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.NIL))
                }
            }
            FunctionNameConstants.INT -> {
                val int = s.pop() // int
                val tempStack = Stack<Token>()
                while(s.peek().tokenKind != TokenKind.CLOSE_TOKEN) {
                    tempStack.push(s.pop())
                }
                s.pop() //closeToken
                if (tempStack.isEmpty() && int.tokenKind == TokenKind.NUMERIC_TOKEN) {
                    s.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.T))
                } else {
                    s.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.NIL))
                }
            }
            FunctionNameConstants.NULL -> {
                val nil = s.pop() // null
                val tempStack = Stack<Token>()
                while(s.peek().tokenKind != TokenKind.CLOSE_TOKEN) {
                    tempStack.push(s.pop())
                }
                s.pop() //closeToken
                if (tempStack.isEmpty() && nil.value == ReservedValuesConstants.NIL) {
                    s.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.T))
                } else {
                    s.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.NIL))
                }
            }
            FunctionNameConstants.QUOTE -> {
                val tempStack = Stack<Token>()
                while(s.peek().tokenKind != TokenKind.CLOSE_TOKEN) {
                    tempStack.push(s.pop())
                }
                s.pop() //closeToken
                while(tempStack.isNotEmpty()) {
                    s.push(tempStack.pop())
                }
            }
            FunctionNameConstants.EQ -> {
                val first = s.pop().value
                val second = s.pop().value
                s.pop() //closeToken
                val result = first == second
                if (result) {
                    s.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.T))
                } else {
                    s.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.NIL))
                }
            }
            FunctionNameConstants.GREATER -> {
                val first = s.pop().value.toInt()
                val second = s.pop().value.toInt()
                s.pop()
                val result = first > second
                s.push(Token(TokenKind.NUMERIC_TOKEN, result.toString()))
            }
            FunctionNameConstants.LESS -> {
                val first = s.pop().value.toInt()
                val second = s.pop().value.toInt()
                s.pop()
                val result = first < second
                s.push(Token(TokenKind.NUMERIC_TOKEN, result.toString()))
            }
            FunctionNameConstants.MINUS -> {
                val first = s.pop().value.toInt()
                val second = s.pop().value.toInt()
                s.pop() //closeToken
                val result = first - second
                s.push(Token(TokenKind.NUMERIC_TOKEN, result.toString()))
            }
            FunctionNameConstants.PLUS -> {
                val first = s.pop().value.toInt()
                val second = s.pop().value.toInt()
                s.pop()
                val result = first + second
                s.push(Token(TokenKind.NUMERIC_TOKEN, result.toString()))
            }
            FunctionNameConstants.TIMES -> {
                val first = s.pop().value.toInt()
                val second = s.pop().value.toInt()
                s.pop()
                val result = first * second
                s.push(Token(TokenKind.NUMERIC_TOKEN, result.toString()))
            }
            else -> {
                val tempStack = Stack<Token>()
                tempStack.push(addressValue)
                while(s.peek().tokenKind != TokenKind.CLOSE_TOKEN) {
                    tempStack.push(s.pop())
                }
                val close = s.pop() // closeToken
                while(tempStack.isNotEmpty()) {
                    s.push(tempStack.pop())
                }
            }
        }
        return s
    }

    fun extractParam() {

    }
}