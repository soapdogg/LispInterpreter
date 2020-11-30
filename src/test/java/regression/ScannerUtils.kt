package regression

import java.nio.file.Paths
import java.util.Scanner

object ScannerUtils {

    @JvmStatic
    fun getScannerFromFilePath(programFilePath: String): Scanner {
        return Scanner(Paths.get(programFilePath))
    }

    @JvmStatic
    fun scanExpected(expectedFile: String): String {
        val builder = StringBuilder()
        val scanner = getScannerFromFilePath(expectedFile)

        while (scanner.hasNextLine()) {
            val next = scanner.nextLine()
            builder.append(next)
            builder.append('\n')
        }

        return builder.toString()
    }
}