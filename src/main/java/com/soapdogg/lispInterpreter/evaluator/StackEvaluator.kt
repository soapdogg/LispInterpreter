package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.constants.ReservedValuesConstants
import com.soapdogg.lispInterpreter.constants.TokenValueConstants
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
        var ss = s
        when (addressValue.value) {
            FunctionNameConstants.CAR -> {
                val firstParamPair = extractParam(ss)
                val firstParamValue = firstParamPair.second
                ss.clear()
                while(firstParamValue.isNotEmpty()) {
                    ss.push(firstParamValue.pop())
                }
            }
            FunctionNameConstants.CDR -> {
                val firstParamPair = extractParam(ss)
                val result = extractParamUntilClose(firstParamPair.first)
                ss = result.second
            }

            FunctionNameConstants.CONS -> {
                val tempStack = Stack<Token>()
                while (ss.peek().tokenKind != TokenKind.CLOSE_TOKEN) {
                    tempStack.push(ss.pop())
                }

                ss.pop() // closeToken

                if (tempStack.peek().value != ReservedValuesConstants.NIL) {
                    tempStack.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.NIL))
                }

                while(tempStack.isNotEmpty()) {
                    ss.push(tempStack.pop())
                }
            }
            TokenValueConstants.CLOSE_PARENTHESES.toString() -> {
                ss.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.NIL))
            }
            FunctionNameConstants.ATOM -> {
                ss.pop() // atom
                val tempStack = Stack<Token>()
                while(ss.peek().tokenKind != TokenKind.CLOSE_TOKEN) {
                    tempStack.push(ss.pop())
                }
                ss.pop() //closeToken
                if (tempStack.isEmpty()) {
                    ss.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.T))
                } else {
                    ss.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.NIL))
                }
            }
            FunctionNameConstants.INT -> {
                val int = ss.pop() // int
                val tempStack = Stack<Token>()
                while(ss.peek().tokenKind != TokenKind.CLOSE_TOKEN) {
                    tempStack.push(ss.pop())
                }
                ss.pop() //closeToken
                if (tempStack.isEmpty() && int.tokenKind == TokenKind.NUMERIC_TOKEN) {
                    ss.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.T))
                } else {
                    ss.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.NIL))
                }
            }
            FunctionNameConstants.NULL -> {
                val nil = ss.pop() // null
                val tempStack = Stack<Token>()
                while(ss.peek().tokenKind != TokenKind.CLOSE_TOKEN) {
                    tempStack.push(ss.pop())
                }
                ss.pop() //closeToken
                if (tempStack.isEmpty() && nil.value == ReservedValuesConstants.NIL) {
                    ss.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.T))
                } else {
                    ss.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.NIL))
                }
            }
            FunctionNameConstants.QUOTE -> {
                val tempStack = Stack<Token>()
                while(ss.peek().tokenKind != TokenKind.CLOSE_TOKEN) {
                    tempStack.push(ss.pop())
                }
                ss.pop() //closeToken
                while(tempStack.isNotEmpty()) {
                    ss.push(tempStack.pop())
                }
            }
            FunctionNameConstants.EQ -> {
                val firstParamPair = extractParam(s)
                val firstParamValue = firstParamPair.second
                if (firstParamValue.size != 1) throw Exception("SHIT DICK")
                val firstToken = firstParamValue[0]
                val first = firstToken.value
                val secondParamPair = extractParam(firstParamPair.first)
                val secondParamValue = secondParamPair.second
                if (secondParamValue.size != 1) throw Exception("SHIT DICK 2")
                val secondToken = secondParamValue[0]
                val second = secondToken.value
                ss = secondParamPair.first
                if (ss.peek().tokenKind != TokenKind.CLOSE_TOKEN) throw Exception("Woah was not expecting this")
                ss.pop() //closeToken
                val result = first == second
                if (result) {
                    ss.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.T))
                } else {
                    ss.push(Token(TokenKind.LITERAL_TOKEN, ReservedValuesConstants.NIL))
                }
            }
            FunctionNameConstants.GREATER -> {
                val firstParamPair = extractParam(s)
                val firstParamValue = firstParamPair.second
                if (firstParamValue.size != 1) throw Exception("SHIT DICK")
                val firstToken = firstParamValue[0]
                if (firstToken.tokenKind != TokenKind.NUMERIC_TOKEN) throw Exception("OOOOH SHIT BUDDY")
                val first = firstToken.value.toInt()
                val secondParamPair = extractParam(firstParamPair.first)
                val secondParamValue = secondParamPair.second
                if (secondParamValue.size != 1) throw Exception("SHIT DICK 2")
                val secondToken = secondParamValue[0]
                if (secondToken.tokenKind != TokenKind.NUMERIC_TOKEN) throw Exception("OOOOOH SHIY BUDDY 2.0")
                val second = secondToken.value.toInt()
                ss = secondParamPair.first
                ss.pop()
                val result = first > second
                ss.push(Token(TokenKind.NUMERIC_TOKEN, result.toString()))
            }
            FunctionNameConstants.LESS -> {
                val firstParamPair = extractParam(s)
                val firstParamValue = firstParamPair.second
                if (firstParamValue.size != 1) throw Exception("SHIT DICK")
                val firstToken = firstParamValue[0]
                if (firstToken.tokenKind != TokenKind.NUMERIC_TOKEN) throw Exception("OOOOH SHIT BUDDY")
                val first = firstToken.value.toInt()
                val secondParamPair = extractParam(firstParamPair.first)
                val secondParamValue = secondParamPair.second
                if (secondParamValue.size != 1) throw Exception("SHIT DICK 2")
                val secondToken = secondParamValue[0]
                if (secondToken.tokenKind != TokenKind.NUMERIC_TOKEN) throw Exception("OOOOOH SHIY BUDDY 2.0")
                val second = secondToken.value.toInt()
                ss = secondParamPair.first
                ss.pop() //closeToken
                val result = first < second
                ss.push(Token(TokenKind.NUMERIC_TOKEN, result.toString()))
            }
            FunctionNameConstants.MINUS -> {
                val firstParamPair = extractParam(s)
                val firstParamValue = firstParamPair.second
                if (firstParamValue.size != 1) throw Exception("SHIT DICK")
                val firstToken = firstParamValue[0]
                if (firstToken.tokenKind != TokenKind.NUMERIC_TOKEN) throw Exception("OOOOH SHIT BUDDY")
                val first = firstToken.value.toInt()
                val secondParamPair = extractParam(firstParamPair.first)
                val secondParamValue = secondParamPair.second
                if (secondParamValue.size != 1) throw Exception("SHIT DICK 2")
                val secondToken = secondParamValue[0]
                if (secondToken.tokenKind != TokenKind.NUMERIC_TOKEN) throw Exception("OOOOOH SHIY BUDDY 2.0")
                val second = secondToken.value.toInt()
                ss = secondParamPair.first
                ss.pop() //closeToken
                val result = first - second
                ss.push(Token(TokenKind.NUMERIC_TOKEN, result.toString()))
            }
            FunctionNameConstants.PLUS -> {
                val firstParamPair = extractParam(s)
                val firstParamValue = firstParamPair.second
                if (firstParamValue.size != 1) throw Exception("SHIT DICK")
                val firstToken = firstParamValue[0]
                if (firstToken.tokenKind != TokenKind.NUMERIC_TOKEN) throw Exception("OOOOH SHIT BUDDY")
                val first = firstToken.value.toInt()
                val secondParamPair = extractParam(firstParamPair.first)
                val secondParamValue = secondParamPair.second
                if (secondParamValue.size != 1) throw Exception("SHIT DICK 2")
                val secondToken = secondParamValue[0]
                if (secondToken.tokenKind != TokenKind.NUMERIC_TOKEN) throw Exception("OOOOOH SHIY BUDDY 2.0")
                val second = secondToken.value.toInt()
                ss = secondParamPair.first
                ss.pop()
                val result = first + second
                ss.push(Token(TokenKind.NUMERIC_TOKEN, result.toString()))
            }
            FunctionNameConstants.TIMES -> {
                val firstParamPair = extractParam(s)
                val firstParamValue = firstParamPair.second
                if (firstParamValue.size != 1) throw Exception("SHIT DICK")
                val firstToken = firstParamValue[0]
                if (firstToken.tokenKind != TokenKind.NUMERIC_TOKEN) throw Exception("OOOOH SHIT BUDDY")
                val first = firstToken.value.toInt()
                val secondParamPair = extractParam(firstParamPair.first)
                val secondParamValue = secondParamPair.second
                if (secondParamValue.size != 1) throw Exception("SHIT DICK 2")
                val secondToken = secondParamValue[0]
                if (secondToken.tokenKind != TokenKind.NUMERIC_TOKEN) throw Exception("OOOOOH SHIY BUDDY 2.0")
                val second = secondToken.value.toInt()
                ss = secondParamPair.first
                ss.pop()
                val result = first * second
                ss.push(Token(TokenKind.NUMERIC_TOKEN, result.toString()))
            }
            else -> {
                val tempStack = Stack<Token>()
                tempStack.push(addressValue)
                while(ss.peek().tokenKind != TokenKind.CLOSE_TOKEN) {
                    tempStack.push(ss.pop())
                }
                val close = ss.pop() // closeToken
                while(tempStack.isNotEmpty()) {
                    ss.push(tempStack.pop())
                }
            }
        }
        return ss
    }

    fun extractParam(
        s: Stack<Token>
    ): Pair<Stack<Token>, Stack<Token>> {
        var openClose = 0
        val param = Stack<Token>()
        do {
            val token = s.pop()
            if (token.tokenKind == TokenKind.OPEN_TOKEN) openClose++
            else if (token.tokenKind == TokenKind.CLOSE_TOKEN) openClose--
            param.push(token)
        } while(openClose != 0)
        return Pair(s, param)
    }

    fun extractParamUntilClose(
        s: Stack<Token>
    ): Pair<Stack<Token>, Stack<Token>> {
        val param = Stack<Token>()
        do {
            val token = s.pop()
            if (token.value != ReservedValuesConstants.NIL) {
                param.push(token)
            }
        } while(s.peek().tokenKind != TokenKind.CLOSE_TOKEN)
        s.pop()
        return Pair(s, param)
    }
}