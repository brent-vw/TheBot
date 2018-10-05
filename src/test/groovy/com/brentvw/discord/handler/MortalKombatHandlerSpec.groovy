package com.brentvw.discord.handler

class MortalKombatHandlerSpec extends HandlerContext {
    MortalKombatHandler handler

    def setup() {
        handler = new MortalKombatHandler()
    }

    def "MortalKombatHandler should handle events starting with !fight"() {
        expect:
        handler.canHandle(getContext("!fight"))
        handler.canHandle(getContext("!fightawea"))
        handler.canHandle(getContext("!fight "))
    }

    def "MortalKombatHandler should not handle events not starting with !fight"() {
        expect:
        !handler.canHandle(getContext("!fig"))
        !handler.canHandle(getContext("!"))
        !handler.canHandle(getContext(""))
        !handler.canHandle(getContext("!unrelated"))
    }

    def "MortalKombatHandler should not handle malformed input"() {
        when:
        def result = handler.handle(getContext("!fight sjorsj"))

        then:
        result == "Invalid arguments" //groovy compiles this to equals
    }

    def "MortalKombatHandler should not handle wrong user combination"() {
        given:
        handler.addCombattant("TEST1")

        when:
        def result = handler.handle(getContext("!fight TEST1 DASD"))
        def result2 = handler.handle(getContext("!fight DASD TEST1"))
        def result3 = handler.handle(getContext("!fight TEST1 TEST1"))

        then:
        result == "Invalid arguments" //groovy compiles this to equals
        result2 == "Invalid arguments"
        result3 == "Invalid arguments"
    }

    def "MortalKombatHandler should handle correct user combination"() {
        given:
        handler.addCombattant("TEST1")
        handler.addCombattant("TEST2")

        when:
        def result = handler.handle(getContext("!fight TEST1 TEST2"))

        then:
        result != "Invalid arguments"
    }
}
