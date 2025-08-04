package com.m1gl.soap.endpoints;

import com.m1gl.soap.services.ParametrageService;

import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/ws/parametrage", new ParametrageService());
        System.out.println("SOAP Service running on http://localhost:8888/ws/parametrage?wsdl");
    }
}
