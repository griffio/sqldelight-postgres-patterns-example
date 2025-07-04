package griffio

import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import griffio.migrations.Users
import griffio.queries.Sample

import org.postgresql.ds.PGSimpleDataSource

private fun getSqlDriver() = PGSimpleDataSource().apply {
    setURL("jdbc:postgresql://localhost:5432/pattern-samples")
    applicationName = "App Main"
}.asJdbcDriver()

val driver = getSqlDriver()
val sample = Sample(driver)

fun main() = with(ConsoleLogger("Main")) {
    val dbTransaction = DbTransaction(sample.usersQueries)
    transaction(dbTransaction) {
        val user = Users(
            id = -1,
            email = "abc@example.com",
            profile = "{}",
            preferences = "{}",
            created_at = null, updated_at = null
        ).create()

        println(user)

        val updated = user.copy(
            email = "xyz@example.com",
            profile = """{"picture":"img/catface.png", "timezone":"America/Los_Angeles"}""",
            preferences = """{"marketing":false, "pizza-topping":"pineapple"}"""
        ).update()

        println(updated)

        println(updated.delete())
    }
}
