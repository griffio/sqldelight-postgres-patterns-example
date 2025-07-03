# SqlDelight 2.1.x Postgresql

https://github.com/cashapp/sqldelight

Version: 2.1.0

Active Record Pattern using Kotlin (2.2.0) context parameters 

Users data class is code generated and cannot be modified/subclassed 

* Cannot use constructor to pass in UserQueries
* Access db via static object?
* Pass db as a method parameter?
* Context parameters allow db to act like a member reference but passed in like a parameter via caller scope

```kotlin
context(db: UsersQueries)
fun Users.create(): Users = db.create(this).executeAsOne()

context(db: UsersQueries)
fun Users.update() : Users = db.update(email, profile, preferences, id).executeAsOne()

context(db: UsersQueries)
fun Users.delete() : Long = db.delete(id).value

context(db: UsersQueries)
fun Users.get(): Users = db.read(id).executeAsOne()
```

----

```shell
createdb pattern-samples &&
./gradlew build &&
./gradlew flywayMigrate
```

Flyway db migrations
https://documentation.red-gate.com/fd/gradle-task-184127407.html
