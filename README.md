# SqlDelight 2.1.x Postgresql

https://github.com/cashapp/sqldelight

Version: 2.1.0

Active Record Pattern using Kotlin (2.2.0) context parameters 

```kotlin
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
```

----

```shell
createdb pattern-samples &&
./gradlew build &&
./gradlew flywayMigrate
```

Flyway db migrations
https://documentation.red-gate.com/fd/gradle-task-184127407.html
