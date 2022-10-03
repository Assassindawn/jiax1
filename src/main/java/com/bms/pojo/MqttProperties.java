package com.bms.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mqtt")
public class MqttProperties {

    private String brokerUrl;
    private String clientID;
    private String username;
    private String password;

    @Override
    public String toString() {
        return "MqttProperties{" +
                "brokerUrl='" + brokerUrl + '\'' +
                ", clientID='" + clientID + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void setBrokerUrl(String brokerUrl) {
        this.brokerUrl = brokerUrl;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getBrokerUrl() {
        return brokerUrl;
    }

    public String getClientID() {
        return clientID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
