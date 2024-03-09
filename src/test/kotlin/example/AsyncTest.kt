package example

import com.github.meo209.kevet.*
import example.events.TestEvent
import kotlinx.coroutines.*


fun main(args: Array<String>) {
    println("Thread: ${Thread.currentThread().name}")
    println("Notifying...")
    notifyAsync(TestEvent("Hello World"))
    println("Notified")
}

val testEventHandler1 = handler<TestEvent>({ event ->
    println("Starting test for event 1")
    println("Event 1 Thread: ${Thread.currentThread().name}")
    println("Event 1 Content: ${event.content}")
    delay(2500)
    println("Test ended for event 1 (5000ms Wait)")
})

val testEventHandler2WithPriority = handler<TestEvent>({ event ->
    println("Event 2 Thread: ${Thread.currentThread().name}")
    println("Event 2 Content: ${event.content}")
    println("Test 2 succeeded")
}, priority = 5)
