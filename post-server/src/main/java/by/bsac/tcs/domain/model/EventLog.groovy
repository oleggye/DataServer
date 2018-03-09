package by.bsac.tcs.domain.model

import groovy.transform.Canonical

@Canonical
class EventLog {
    private long id
    private long postBoxId
    private Event event
    private String state

    EventLog(long id, long postBoxId, Event event, state) {
        this.id = id
        this.postBoxId = postBoxId
        this.event = event
        this.state = state
    }

    EventLog(long postBoxId, Event event, String state) {
        this.postBoxId = postBoxId
        this.event = event
        this.state = state
    }
}
