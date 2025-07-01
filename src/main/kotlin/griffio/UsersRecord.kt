package griffio

import griffio.migrations.Users
import griffio.queries.UsersQueries

context(db: UsersQueries, log: Logger)
fun Users.create(): Users {
    log.info("Creating user: $this")
    return db.create(this).executeAsOne()
}

context(db: UsersQueries, log: Logger)
fun Users.update() : Users {
    log.info("Updating user: $this")
    return db.update(email, profile, preferences, id).executeAsOne()
}

context(db: UsersQueries, log: Logger)
fun Users.delete() : Long {
    log.info("Deleting user: $this")
    return db.delete(id).value
}

context(db: UsersQueries, log: Logger)
fun Users.get(): Users {
    log.info("Getting user: $this")
    return db.read(id).executeAsOne()
}

context(log: Logger)
fun transaction(tx: Transaction, block: context(UsersQueries) () -> Unit) {
    log.info("Starting transaction")
    tx.run(block)
    log.info("Committing transaction")
}
