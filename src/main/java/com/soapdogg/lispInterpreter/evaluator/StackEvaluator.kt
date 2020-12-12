package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
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
            FunctionNameConstants.EQ -> {
                val first = s.pop().value
                val second = s.pop().value
                s.pop()
                val result = first == second
                s.push(Token(TokenKind.NUMERIC_TOKEN, result.toString())) //todo
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
                s.pop()
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
        }
        return s
    }
}