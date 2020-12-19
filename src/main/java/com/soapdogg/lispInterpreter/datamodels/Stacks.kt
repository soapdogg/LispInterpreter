package com.soapdogg.lispInterpreter.datamodels

import java.util.*

data class Stacks(
    val evalStack: Stack<NodeV2>,
    val programStack: Stack<ProgramStackItem>
)
