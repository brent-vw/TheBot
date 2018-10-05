package com.brentvw.discord.handler

class IkHebEenVraagjeHandlerSpec extends HandlerContext {
    IkHebEenVraagjeHandler handler

    def setup() {
        handler = new IkHebEenVraagjeHandler()
    }

    def "IkHebEenVraagjeHandler should handle events starting with !ikhebeenvraagje"() {
        expect:
        handler.canHandle(getContext("!ikhebeenvraagje"))
        handler.canHandle(getContext("!ikhebeenvraagjedsfsdfds"))
        handler.canHandle(getContext("!ikhebeenvraagje "))
    }

    def "IkHebEenVraagjeHandler should not handle events not starting with !ikhebeenvraagje"() {
        expect:
        !handler.canHandle(getContext("!vraagje"))
        !handler.canHandle(getContext("!"))
        !handler.canHandle(getContext(""))
        !handler.canHandle(getContext("!unrelated"))
    }

    def "Handle with !ikhebeenvraagje should return a message containing the count"() {
        given:
        handler.setCount(1337)

        when:
        def result = handler.handle(getContext("!ikhebeenvraagje"))

        then:
        result.contains("1337")
    }

    def "Handle with !ikhebeenvraagje++ should increment the count"() {
        given:
        handler.setCount(1336)

        when:
        handler.handle(getContext("!ikhebeenvraagje++"))

        then:
        handler.getCount() == 1337
    }

    def "Handle with !ikhebeenvraagje-- should decrement the count"() {
        given:
        handler.setCount(1338)

        when:
        handler.handle(getContext("!ikhebeenvraagje--"))

        then:
        handler.getCount() == 1337
    }

    def "Handle with !ikhebeenvraagje set <num> should set the count"() {
        given:
        handler.setCount(0)

        when:
        handler.handle(getContext("!ikhebeenvraagje set 1337"))

        then:
        handler.getCount() == 1337
    }

    def "Handle with too many arguments should return an error message"() {
        given:
        handler.setCount(0)

        when:
        def result = handler.handle(getContext("!ikhebeenvraagje a b c d"))

        then:
        result == "Invalid arguments" //groovy compiles this to equals
    }

    def "Handle with invalid number should throw an error"() {
        given:
        handler.setCount(0)

        when:
        def result = handler.handle(getContext("!ikhebeenvraagje set EWAEAW"))

        then:
        result.contains("RESPECT RULE 2")
    }
}
