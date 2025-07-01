package griffio

import griffio.migrations.Users
import griffio.queries.UsersQueries

context(db: UsersQueries)
fun Users.create(): Users = db.create(this).executeAsOne()

context(db: UsersQueries)
fun Users.update() : Users = db.update(email, profile, preferences, id).executeAsOne()

context(db: UsersQueries)
fun Users.delete() : Long = db.delete(id).value

context(db: UsersQueries)
fun Users.get(): Users = db.read(id).executeAsOne()
