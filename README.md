# Kevet
Simple event system written in kotlin.
<br>

# Getting Started
To use Kevet, you first need to include it in your project.
You can do this using jitpack. First add the jitpack repository:
```kotlin
repositories { 
    maven { url = uri("https://jitpack.io") }
}
```
Then add the Kevet dependency:
```kotlin
implementation("com.github.meo209:Kevet:version")
```
Replace ```version``` with the latest version found on GitHub.

First you need to import the necessary components from `com.github.meo209.kevet`:
```kotlin
import com.github.meo209.kevet.*
```

# Event Handling
In Kevet, events are handled by creating a val/var with the `EventBus.handler` function:
```kotlin
val testEventHandler = handler<TestEvent> { event ->
    println("Event handled: $event")
}
```

To call an event you need to call the `EventBus.notify` function:
```kotlin
notify(ExampleEvent())
```

You can also use the `EventBus.notifyAsync` function to call the events asynchronously:
```kotlin
notifyAsync(ExampleEvent())
```
Warning: This function uses parallel programming and is therefore NOT thread safe.