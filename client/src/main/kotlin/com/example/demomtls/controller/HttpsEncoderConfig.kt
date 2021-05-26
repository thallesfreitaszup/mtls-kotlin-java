package com.example.demomtls.controller

import feign.Client
import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.ssl.SSLContexts
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import java.io.InputStream
import java.security.KeyStore
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory

class HttpsEncoderConfig(
    @Value("\${server.ssl.trust-store-password}")
    val trustStorePassword: String,
    @Value("\${server.ssl.key-store}")
    val keyStore: Resource,
    @Value("\${server.ssl.key-store-password}")
    val keyStorePassword: String,
    private  var resourceLoader: ResourceLoader
){
    @Bean
    fun feignClient(
    ): Client {
        val trustSSLSockets = Client.Default(getSSLSocketFactory(),  NoopHostnameVerifier());
        return trustSSLSockets;
    }
    private fun getSSLSocketFactory(): SSLSocketFactory {
        val myStore = resourceLoader.getResource("classpath:clientstore.jks")
        val serverStore = resourceLoader.getResource("classpath:keystore.jks")
        val sslContexts =  SSLContexts.custom().loadTrustMaterial(serverStore.file, trustStorePassword.toCharArray())
            .loadKeyMaterial(myStore.file, keyStorePassword.toCharArray(),
                keyStorePassword.toCharArray()).build();
        return sslContexts.socketFactory
    }
}
