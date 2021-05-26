package com.example.demomtls.controller

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus

@FeignClient(name = "httpsClient", url = "https://localhost:9443", configuration = [ HttpsEncoderConfig::class])
interface HttpsClient {
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(
        value = ["/hello"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getHello():String
}
