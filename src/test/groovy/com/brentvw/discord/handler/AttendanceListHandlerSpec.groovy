package com.brentvw.discord.handler

class AttendanceListHandlerSpec extends HandlerContext {
    AttendanceListHandler handler

    def setup() {
        handler = new AttendanceListHandler()
        handler.clearNames()
    }

    def "AttendanceListHandler should handle events starting with !list"() {
        expect:
        handler.canHandle(getContext("!list"))
        handler.canHandle(getContext("!listsds"))
        handler.canHandle(getContext("!list "))
    }

    def "AttendanceListHandler should not handle events not starting with !list"() {
        expect:
        !handler.canHandle(getContext("!lis"))
        !handler.canHandle(getContext("!"))
        !handler.canHandle(getContext(""))
        !handler.canHandle(getContext("!unrelated"))
    }

    def "Using who without person responsible should return a message"() {
        when:
        def result = handler.handle(getContext("!list who"))
        then:
        result == "No name found."
    }

    def "Using who with person responsible should return the person"() {
        given:
        def name = "Theo Tester"
        handler.setName(name)

        when:
        def result = handler.handle(getContext("!list who"))

        then:
        result.contains(name)
    }

    def "using choose should pick a person and return a message"() {
        given:
        def name = "Theo Tester"
        handler.addName(name)

        when:
        def result = handler.handle(getContext("!list choose"))

        then:
        handler.currentResponsibleForList().contains(name)
        result.contains(name)
    }

    def "using any other arguments should return a message"() {
        when:
        def result = handler.handle(getContext("!list hack"))

        then:
        result == "Invalid arguments"
    }

}
