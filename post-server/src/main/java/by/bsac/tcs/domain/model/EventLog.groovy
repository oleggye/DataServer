package by.bsac.tcs.domain.model

import groovy.transform.Canonical

import java.time.LocalDateTime

@Canonical
class EventLog {
    long id
    long postBoxId
    Event event
    int quantity
    long epochTime

    EventLog(long id, long postBoxId, Event event, int quantity, long epochTime) {
        this.id = id
        this.postBoxId = postBoxId
        this.event = event
        this.quantity = quantity
    }

    EventLog(long postBoxId, Event event, int quantity, long epochTime) {
        this.postBoxId = postBoxId
        this.event = event
        this.quantity = quantity
    }
}
