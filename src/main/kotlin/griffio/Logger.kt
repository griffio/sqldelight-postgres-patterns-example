
package griffio

interface Logger {
    fun info(message: String)
}

class ConsoleLogger(private val name: String) : Logger {
    override fun info(message: String) {
        println("[$name] $message")
    }
}
