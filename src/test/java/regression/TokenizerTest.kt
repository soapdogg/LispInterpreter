package regression

import com.soapdogg.lispInterpreter.datamodels.ProcessedTokensResult
import com.soapdogg.lispInterpreter.datamodels.Token
import com.soapdogg.lispInterpreter.datamodels.TokenKind
import com.soapdogg.lispInterpreter.singleton.TokenizerSingleton
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import regression.ScannerUtils.getScannerFromFilePath
import regression.ScannerUtils.scanExpected
import java.util.*

class TokenizerTest {

    @Test
    fun project1ValidTest() {
        tokenizerTest("data/input/project1/valid.lisp", "data/expected/project1/valid.txt")
    }

    @Test
    fun project1Valid2Test() {
        tokenizerTest("data/input/project1/valid2.lisp", "data/expected/project1/valid2.txt")
    }

    @Test
    fun project1Valid3Test() {
        tokenizerTest("data/input/project1/valid3.lisp", "data/expected/project1/valid3.txt")
    }

    @Test
    fun project1Valid4Test() {
        tokenizerTest("data/input/project1/valid4.lisp", "data/expected/project1/valid4.txt")
    }

    @Test
    fun project1Valid5Test() {
        tokenizerTest("data/input/project1/valid5.lisp", "data/expected/project1/valid5.txt")
    }

    @Test
    fun project1Valid6Test() {
        tokenizerTest("data/input/project1/valid6.lisp", "data/expected/project1/valid6.txt")
    }

    @Test
    fun project1Valid7Test() {
        tokenizerTest("data/input/project1/valid7.lisp", "data/expected/project1/valid7.txt")
    }

    @Test
    fun project1Valid8Test() {
        tokenizerTest("data/input/project1/valid8.lisp", "data/expected/project1/valid8.txt")
    }

    @Test
    fun project1Invalid1Test() {
        tokenizerTest("data/input/project1/invalid1.lisp", "data/expected/project1/invalid1.txt")
    }

    @Test
    fun project1Invalid2Test() {
        tokenizerTest("data/input/project1/invalid2.lisp", "data/expected/project1/invalid2.txt")
    }

    @Test
    fun project1Invalid3Test() {
        tokenizerTest("data/input/project1/invalid3.lisp", "data/expected/project1/invalid3.txt")
    }

    companion object {
        private fun tokenizerTest(programFile: String, expectedFile: String) {
            val tokenizer = TokenizerSingleton.INSTANCE.tokenizer
            val actual = try {
                val `in` = getScannerFromFilePath(programFile)
                val tokens = tokenizer.tokenize(`in`)
                val processedTokensResult = processTokens(tokens)
                getTokenizedResults(
                    processedTokensResult
                )
            } catch (e: Exception) {
                e.message.toString()
            }
            val expected = scanExpected(expectedFile)
            Assertions.assertEquals(expected, actual)
        }

        private fun processTokens(tokens: List<Token>): ProcessedTokensResult {
            val literalAtoms: MutableList<String> = LinkedList()
            var openCount = 0
            var closingCount = 0
            var numericAtomsSum = 0
            var numericAtomsCount = 0
            for ((tokenKind, value) in tokens) {
                when (tokenKind) {
                    TokenKind.OPEN_TOKEN -> ++openCount
                    TokenKind.CLOSE_TOKEN -> ++closingCount
                    TokenKind.LITERAL_TOKEN -> literalAtoms.add(value)
                    TokenKind.NUMERIC_TOKEN -> {
                        ++numericAtomsCount
                        numericAtomsSum += value.toInt()
                    }
                }
            }
            return ProcessedTokensResult(
                literalAtoms,
                openCount,
                closingCount,
                numericAtomsSum,
                numericAtomsCount
            )
        }

        private fun getTokenizedResults(
            processedTokensResult: ProcessedTokensResult
        ): String {
            val sb = StringBuilder()
            sb.append("LITERAL ATOMS: ")
            sb.append(processedTokensResult.literalAtoms.size)
            for (s in processedTokensResult.literalAtoms) {
                sb.append(',')
                sb.append(' ')
                sb.append(s)
            }
            sb.append('\n')
            sb.append("NUMERIC ATOMS: ")
            sb.append(processedTokensResult.numericAtomsCount)
            sb.append(',')
            sb.append(processedTokensResult.numericAtomsSum)
            sb.append('\n')
            sb.append("OPEN PARENTHESES: ")
            sb.append(processedTokensResult.openCount)
            sb.append('\n')
            sb.append("CLOSING PARENTHESES: ")
            sb.append(processedTokensResult.closeCount)
            sb.append('\n')
            return sb.toString()
        }
    }
}