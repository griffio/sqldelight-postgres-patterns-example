
package griffio

import griffio.queries.UsersQueries

interface Transaction {
    fun <T> run(block: context(UsersQueries) () -> T): T
}

class DbTransaction(private val db: UsersQueries) : Transaction {
    override fun <T> run(block: context(UsersQueries) () -> T): T {
        return db.transactionWithResult {
            block(db)
        }
    }
}
