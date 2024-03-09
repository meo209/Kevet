package com.github.meo209.kevet

/**
 * An interface for objects that want to be notified of events.
 *
 * @param T the type of event that will be handled
 */
interface KevetListener {

    /**
     * Handles the given event.
     *
     * @param event the event to handle
     */
    suspend fun handle(event: KevetEvent)

    /**
     * The priority of the eventListener
     */
    val priority: Int
}