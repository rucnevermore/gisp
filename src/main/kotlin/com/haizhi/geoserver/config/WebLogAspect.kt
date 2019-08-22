package com.haizhi.geoserver.config

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*
import org.slf4j.LoggerFactory

/**
 *
 * Created by gubaoer on 17/7/3.
 */


@Aspect
@Order(5)
@Component
class WebLogAspect {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    internal var startTime = ThreadLocal<Long>()

    @Pointcut("execution(public * com.rynnis.springboot_vue.controller..*.*(..))")
    fun webLog() {
    }

    @Before("webLog()")
    @Throws(Throwable::class)
    fun doBefore(joinPoint: JoinPoint) {

        startTime.set(System.currentTimeMillis())

        val attributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes

        val request = attributes.getRequest()

        logger.info("URL : " + request.getRequestURL().toString())

        logger.info("HTTP_METHOD : " + request.getMethod())

        logger.info("IP : " + request.getRemoteAddr())

        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName())

        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()))

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    @Throws(Throwable::class)
    fun doAfterReturning(ret: Any) {


        logger.info("RESPONSE : " + ret)

        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()))
    }


}

