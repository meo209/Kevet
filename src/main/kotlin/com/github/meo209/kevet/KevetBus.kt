package com.github.meo209.kevet

import kotlinx.coroutines.*
import kotlin.reflect.KClass

/**
 * listeners a map of event types to a list of event listeners
 */
private val listeners: MutableMap<KClass<out Any>, MutableList<KevetListener>> = HashMap()

/**
 * Registers an event listener for a specific event type
 *
 * @param eventClass the class of the event to listen for
 * @param listener the event listener to register
 */
fun register(eventClass: KClass<*>, listener: KevetListener) {
    val eventListeners = listeners.getOrPut(eventClass) { ArrayList() }
    eventListeners.add(listener)
    eventListeners.sortByDescending { it.priority }
}

/**
 * Notifies all registered event listeners of a new event
 *
 * @param event the event to notify listeners of
 */
suspend fun notify(event: KevetEvent) = coroutineScope {
    listeners[event::class]?.forEach {
        it.handle(event)
    }
}

/**
 * Notifies all registered event listeners of a new event in parallel
 *
 * @param event the event to notify listeners of
 */
fun notifyAsync(event: KevetEvent) {
    runBlocking {
        listeners[event::class]?.map { listener ->
            async {
                listener.handle(event)
            }
        }?.awaitAll()
    }
}


/**
 * Registers an event listener for a specific event type on an [EventBus].
 *
 * @param T the type of event to listen for
 * @param callback the function to call when an event of type [T] is received
 */
inline fun <reified T : Any> handler(crossinline callback: suspend (T) -> Unit, priority: Int = 0) {
    val listener = object : KevetListener {
        override suspend fun handle(event: KevetEvent) {
            if (event is T)
                callback(event)
        }

        override val priority: Int = priority
    }
    register(T::class, listener)
}
