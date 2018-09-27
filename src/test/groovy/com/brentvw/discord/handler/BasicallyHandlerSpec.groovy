package com.brentvw.discord.handler

import spock.lang.Specification

class BasicallyHandlerSpec extends Specification {
    BasicallyHandler handler

    def setup() {
        handler = new BasicallyHandler()
    }

    def "BasicallyHandler should handle events starting with !basically"() {
        expect:
        handler.canHandle("!basically")
        handler.canHandle("!basicallydsfsdfds")
        handler.canHandle("!basically ")
    }

    def "BasicallyHandler should not handle events not starting with !basically"() {
        expect:
        !handler.canHandle("!basic")
        !handler.canHandle("!")
        !handler.canHandle("")
        !handler.canHandle("!unrelated")
    }

    def "Handle with !basically should return a message containing the count"() {
        given:
        handler.AMOUNT = 1337

        when:
        def result = handler.handle("!basically")

        then:
        result.contains("1337")
    }

    def "Handle with !basically++ should increment the count"() {
        given:
        handler.AMOUNT = 1336

        when:
        handler.handle("!basically++")

        then:
        handler.AMOUNT == 1337
    }

    def "Handle with !basically-- should increment the count"() {
        given:
        handler.AMOUNT = 1338

        when:
        handler.handle("!basically--")

        then:
        handler.AMOUNT == 1337
    }

    def "Handle with !basically set <num> should set the count"() {
        given:
        handler.AMOUNT = 0

        when:
        handler.handle("!basically set 1337")

        then:
        handler.AMOUNT == 1337
    }
}
