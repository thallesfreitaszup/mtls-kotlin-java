package com.example.demomtls.controller

import org.springframework.cloud.openfeign.EnableFeignClients

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@EnableFeignClients
class ClientController (
    val httpsClient: HttpsClient
        ){
    @PostMapping("/hello")
    fun makeHttpsHello(): String {
        return this.httpsClient.getHello()
    }

}