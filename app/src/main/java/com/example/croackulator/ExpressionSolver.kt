package com.example.croackulator

private const val SUPPORTED_OPERATORS = "+-/*"
private const val MAX_LENGTH_OF_EXPRESSION = 64
private const val MAX_LENGTH_OF_SINGLE_NUMBER = 15

class ExpressionSolver() {
    var expression: String = "0"
    private set

    constructor(_expression: String): this(){
        expression=_expression
    }

    fun solveExpression(): String {
        try {
            isCorrectExpression()
        }
        catch (e: Exception) {
            throw e
        }
        val componentsOfExpression = splitExpression()
        var i = 0
        while (i<componentsOfExpression.count()) {
            if (componentsOfExpression[i]=="*" || componentsOfExpression[i]=="/") {
                try {
                    componentsOfExpression[i] = executeOperator(
                        componentsOfExpression[i].first(),
                        componentsOfExpression[i - 1], componentsOfExpression[i + 1]
                    )
                }
                catch (e: Exception) {
                    throw e
                }
                componentsOfExpression.removeAt(i + 1)
                componentsOfExpression.removeAt(i - 1)
                continue
            }
            i++
        }
        i=0
        while (i<componentsOfExpression.count()) {
            if (componentsOfExpression[i] == "+" || componentsOfExpression[i] == "-") {
                componentsOfExpression[i] = executeOperator(
                    componentsOfExpression[i].first(),
                    componentsOfExpression[i - 1], componentsOfExpression[i + 1] )
                componentsOfExpression.removeAt(i + 1)
                componentsOfExpression.removeAt(i - 1)
                continue
            }
            i++
        }
        if ((String.format("%.0f", componentsOfExpression[0].toDouble()).length <= MAX_LENGTH_OF_SINGLE_NUMBER))
            expression=componentsOfExpression[0]
        else
            throw NumberFormatException("Result exceeds max")
        return expression
    }

    fun appendExpression(inputSymbol: Char) : String {
        if (expression.length>= MAX_LENGTH_OF_EXPRESSION)
            return expression
        expression = if (SUPPORTED_OPERATORS.contains(inputSymbol))
            appendExpressionOperator(inputSymbol)
        else if (inputSymbol.isDigit())
            appendExpressionDigit(inputSymbol)
        else if (inputSymbol=='(')
            appendExpressionBracket()
        else if (inputSymbol=='.')
            appendExpressionPoint()
        else
            expression
        return expression
    }

    fun removeLast(): String {
        expression = if (expression.length>1)
            expression.dropLast(1)
        else
            "0"
        return expression
    }

    fun clearExpression(): String {
        expression = "0"
        return expression
    }


    private fun isCorrectExpression(): Boolean {
        if (numberOfUnclosedBrackets()>0)  throw IllegalArgumentException("Unclosed bracket detected")
        if (SUPPORTED_OPERATORS.contains(expression.last())) throw IllegalArgumentException("Unfinished operation detected")
        if (expression.last()=='.') throw IllegalArgumentException("Missing decimal detected")
        return true
    }

    private fun splitExpression(): ArrayList<String> {
        var currentExpression = expression
        val elementsInExpression: ArrayList<String> = arrayListOf()
        var lastKnownOperatorIndex = -1
        var i =0
        while (i<currentExpression.length) {
            if (SUPPORTED_OPERATORS.contains(currentExpression[i]) && i!=0) {
                elementsInExpression.add(currentExpression.substring(lastKnownOperatorIndex+1, i))
                elementsInExpression.add(currentExpression[i].toString())
                lastKnownOperatorIndex=i
            }
            if (currentExpression[i]=='(') {
                val temporarySolver = ExpressionSolver(currentExpression.substring(i+1, findIndexOfClosingBracket(i)))
                currentExpression=currentExpression.removeRange(i, findIndexOfClosingBracket(i)+1)
                currentExpression=currentExpression.substring(0,i)+temporarySolver.solveExpression()+currentExpression.substring(i, currentExpression.length)
            }
            i++
        }
        elementsInExpression.add(currentExpression.substring(lastKnownOperatorIndex+1, currentExpression.length))
        return elementsInExpression
    }

    private fun numberOfUnclosedBrackets(): Int {
        var numberOfOpenBrackets = 0
        for (i in expression.indices) {
            numberOfOpenBrackets = when(expression[i]) {
                '(' -> numberOfOpenBrackets+1
                ')' -> numberOfOpenBrackets-1
                else -> numberOfOpenBrackets
            }
        }
        return numberOfOpenBrackets
    }

    private fun findIndexOfClosingBracket(indexOfOpenBracket: Int): Int {
        if (expression[indexOfOpenBracket]!='(') {
            return -1
        }
        val expressionFromOpenBracket = expression.substring(indexOfOpenBracket, expression.length)
        var numberOfOpenBrackets = 0
        for(i in expressionFromOpenBracket.indices) {
            numberOfOpenBrackets = when (expressionFromOpenBracket[i]) {
                '(' -> numberOfOpenBrackets + 1
                ')' -> numberOfOpenBrackets - 1
                else -> numberOfOpenBrackets
            }
            if (numberOfOpenBrackets==0) {
                return indexOfOpenBracket+i
            }
        }
        return -1
    }

    private fun executeOperator(operator: Char, numberOne: String, numberTwo: String): String {
        val result = when (operator) {
            '+' -> numberOne.toDouble()+numberTwo.toDouble()
            '-' -> numberOne.toDouble()-numberTwo.toDouble()
            '*' -> numberOne.toDouble()*numberTwo.toDouble()
            '/' -> if (numberTwo!="0") numberOne.toDouble()/numberTwo.toDouble()
            else throw ArithmeticException("Division by zero detected")
            else -> throw NoSuchMethodException("Unknown operator")
        }
        if (result%1==0.0)
            return String.format("%.0f", result)
        return result.toBigDecimal().toPlainString()
    }

    private fun appendExpressionPoint(): String {
        if (expression.isBlank() || expression=="0")
            return "0."
        if (SUPPORTED_OPERATORS.contains(expression.last()))
            return expression+"0."
        if ((expression.last().isDigit())) {
            for (i in expression.reversed()) {
                if (i=='.')
                    return expression
                if (!i.isDigit())
                    return "$expression."
            }
            return "$expression."
        }
        return expression
    }

    private fun appendExpressionBracket(): String {
        return if (expression.isBlank() || expression=="0" || (expression.length==1 && SUPPORTED_OPERATORS.contains(expression[0])))
            "("
        else if (expression.last().isDigit() || expression.last()==')'){
            if (numberOfUnclosedBrackets()>0)
                "$expression)"
            else
                expression
        } else
            "$expression("
    }

    private fun appendExpressionDigit(inputSymbol: Char): String {
        if (expression.isBlank() || expression=="0")
            return inputSymbol.toString()
        if (expression.last().isDigit()) {
            if (expression.last()=='0' && (!expression[expression.length-1].isDigit() && expression[expression.length-1] != '.'))
                return expression.dropLast(1)+inputSymbol
            var digitsInLastNumber = 0
            for (i in expression.reversed()) {
                if (i.isDigit())
                    digitsInLastNumber++
                else
                    return expression+inputSymbol
                if (digitsInLastNumber >= MAX_LENGTH_OF_SINGLE_NUMBER)
                    return expression
            }
            return expression+inputSymbol
        }
        if (!expression.last().isDigit() && expression.last()!=')') {
            return expression+inputSymbol
        }
        return expression
    }

    private fun appendExpressionOperator(inputSymbol: Char): String {
        return if ((expression=="0" || expression.isBlank()) && inputSymbol!='-')
            "0$inputSymbol"
        else if ((expression=="0" || expression.isBlank()) && inputSymbol=='-')
            "-"
        else if ((inputSymbol=='-' && expression.last()=='(') || expression.last().isDigit() || expression.last()==')')
            expression+inputSymbol
        else if (SUPPORTED_OPERATORS.contains(expression.last()) && expression.length>1 && expression[expression.length-2]!='(')
            expression.dropLast(1)+inputSymbol
        else
            expression
    }
}