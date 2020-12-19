package com.soapdogg.lispInterpreter.evaluator

import com.soapdogg.lispInterpreter.constants.FunctionNameConstants
import com.soapdogg.lispInterpreter.datamodels.ProgramStackItem
import com.soapdogg.lispInterpreter.exceptions.NotAListException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class CondProgramStackItemEvaluatorTest {

    private val topProgramStackItemUpdater = Mockito.mock(TopProgramStackItemUpdater::class.java)
    private val condChildStackItemBuilder = Mockito.mock(CondChildStackItemBuilder::class.java)

    private val condProgramStackItemEvaluator = CondProgramStackItemEvaluator(
        topProgramStackItemUpdater,
        condChildStackItemBuilder
    )

    @Test
    fun currentParameterIndexIsZeroTest() {
        val firstChildValue = FunctionNameConstants.COND
        val top = Mockito.mock(ProgramStackItem::class.java)
        val programStack = Stack<ProgramStackItem>()

        val currentParameterIndex = 0
        Mockito.`when`(top.currentParameterIndex).thenReturn(currentParameterIndex)

        val updatedProgramStack = Stack<ProgramStackItem>()
        Mockito.`when`(topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(programStack)).thenReturn(updatedProgramStack)

        val expected = Stack<ProgramStackItem>()
        Mockito.`when`(
            condChildStackItemBuilder.buildCondChildStackItems(
                top,
                updatedProgramStack
            )
        ).thenReturn(expected)

        val actual = condProgramStackItemEvaluator.evaluateCondProgramStackItem(
            firstChildValue,
            top,
            programStack
        )

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun currentParameterIndexIsTwoTest() {
        val firstChildValue = FunctionNameConstants.COND
        val top = Mockito.mock(ProgramStackItem::class.java)
        val programStack = Stack<ProgramStackItem>()

        val currentParameterIndex = 2
        Mockito.`when`(top.currentParameterIndex).thenReturn(currentParameterIndex)

        val updatedProgramStack = Stack<ProgramStackItem>()
        Mockito.`when`(topProgramStackItemUpdater.updateTopProgramStackItemToNextChild(programStack)).thenReturn(updatedProgramStack)

        val actual = condProgramStackItemEvaluator.evaluateCondProgramStackItem(
            firstChildValue,
            top,
            programStack
        )

        Assertions.assertEquals(updatedProgramStack, actual)

        Mockito.verifyNoInteractions(condChildStackItemBuilder)
    }

    @Test
    fun noCondConditionsEvaluatedToTrueTest() {
        val firstChildValue = FunctionNameConstants.COND
        val top = Mockito.mock(ProgramStackItem::class.java)
        val programStack = Stack<ProgramStackItem>()

        val currentParameterIndex = 1
        Mockito.`when`(top.currentParameterIndex).thenReturn(currentParameterIndex)

        Assertions.assertThrows(
            NotAListException::class.java
        ) {
            condProgramStackItemEvaluator.evaluateCondProgramStackItem(
                firstChildValue,
                top,
                programStack
            )
        }

        Mockito.verifyNoInteractions(topProgramStackItemUpdater)
        Mockito.verifyNoInteractions(condChildStackItemBuilder)
    }

    @Test
    fun firstChildValueIsNotCondTest() {
        val firstChildValue = FunctionNameConstants.CAR
        val top = Mockito.mock(ProgramStackItem::class.java)
        val programStack = Stack<ProgramStackItem>()

        val actual = condProgramStackItemEvaluator.evaluateCondProgramStackItem(
            firstChildValue,
            top,
            programStack
        )

        Assertions.assertEquals(programStack, actual)
        Mockito.verifyNoInteractions(topProgramStackItemUpdater)
        Mockito.verifyNoInteractions(condChildStackItemBuilder)
    }
}