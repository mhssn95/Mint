# Mint

Mint is an Android Library to make your key-value Store beautiful.

### Installation

download the latest [AAR][1] or get it from maven repository

```groovy
repositories {
    google()
    jcenter()
    maven {
        url = "https://maven.pkg.github.com/mhssn95/mint"
    }
}

dependencies {
    implementation 'io.mhssn:mint:1.0.0'
}
```

### Usage

first step implement **Store**

```kotlin
class SharedPreferences(context: Context) : Store
```

then create your values interface
```kotlin
@Mint
interface UserValues {

    @Key("pref_username")
    var username: String

    @Key("pref_age")
    var age: String

    @Key("pref_gender")
    var gender: String

    //key is optional the default will be pref_your_property_name
    var isAdmin: Boolean

    var healthInfo: HealthInfo

}

data class HealthInfo(
    val bloodType: String,
    val weight: Float,
    val height: Float
)
```

Finally, create a Mint object that receives your Store and create your values interface.

```kotlin
val userValues = Mint(store).create(UserValues::class)
```

then if you want to store data, call the property setter

```kotlin
userValues.username = "mhssn"
```

and if you want to receive from your Store call the property getter

```kotlin
val username = userValues.username
```

## License

```
Copyright 2021 mhssn

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

[1]: https://github.com/mhssn95/Mint/packages/711301
