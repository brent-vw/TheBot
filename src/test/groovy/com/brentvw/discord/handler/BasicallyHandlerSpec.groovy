package com.brentvw.discord.handler

import com.brentvw.discord.context.RequestContext
import spock.lang.Specification

class BasicallyHandlerSpec extends Specification {
    BasicallyHandler handler

    def setup() {
        handler = new BasicallyHandler()
    }

    def "BasicallyHandler should handle events starting with !basically"() {
        expect:
        handler.canHandle(getContext("!basically"))
        handler.canHandle(getContext("!basicallydsfsdfds"))
        handler.canHandle(getContext("!basically "))
    }

    def "BasicallyHandler should not handle events not starting with !basically"() {
        expect:
        !handler.canHandle(getContext("!basic"))
        !handler.canHandle(getContext("!"))
        !handler.canHandle(getContext(""))
        !handler.canHandle(getContext("!unrelated"))
    }

    def "Handle with !basically should return a message containing the count"() {
        given:
        handler.setCount(1337)

        when:
        def result = handler.handle(getContext("!basically"))

        then:
        result.contains("1337")
    }

    def "Handle with !basically++ should increment the count"() {
        given:
        handler.setCount(1336)

        when:
        handler.handle(getContext("!basically++"))

        then:
        handler.getCount() == 1337
    }

    def "Handle with !basically-- should increment the count"() {
        given:
        handler.setCount(1338)

        when:
        handler.handle(getContext("!basically--"))

        then:
        handler.getCount() == 1337
    }

    def "Handle with !basically set <num> should set the count"() {
        given:
        handler.setCount(0)

        when:
        handler.handle(getContext("!basically set 1337"))

        then:
        handler.getCount() == 1337
    }

    def getContext(def message) {
        return Mock(RequestContext) {
            getMessage() >> message
        }
    }
}
