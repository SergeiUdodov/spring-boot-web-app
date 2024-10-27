package com.example.app.configuration;

import com.example.app.wsdl.QuadraticEquationSolver;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoapClientConfig {

    @Value("${soap.service.docker.url}")
    private String soapServiceUrl;

    @Bean
    public QuadraticEquationSolver quadraticEquationSolver() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(QuadraticEquationSolver.class);
        factory.setAddress(soapServiceUrl);
        return (QuadraticEquationSolver) factory.create();
    }
}
