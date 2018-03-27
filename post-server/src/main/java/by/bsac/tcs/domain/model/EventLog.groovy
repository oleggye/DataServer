package by.bsac.tcs.domain.model

import groovy.transform.Canonical

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
        this.epochTime = epochTime
    }

    EventLog(long postBoxId, Event event, int quantity, long epochTime) {
        this.postBoxId = postBoxId
        this.event = event
        this.quantity = quantity
        this.epochTime = epochTime
    }

    @Override
    String toString() {
        return "EventLog{" +
                "id=" + id +
                ", postBoxId=" + postBoxId +
                ", event=" + event +
                ", quantity=" + quantity +
                ", epochTime=" + epochTime +
                '}'
    }
}
