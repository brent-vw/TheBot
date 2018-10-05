package com.brentvw.discord.handler

import com.brentvw.discord.context.RequestContext
import spock.lang.Specification


abstract class HandlerContext extends Specification {
    def getContext(String message) {
        return Mock(RequestContext) {
            getMessage() >> message
        }
    }
}