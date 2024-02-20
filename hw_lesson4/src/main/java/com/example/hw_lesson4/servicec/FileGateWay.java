package com.example.hw_lesson4.servicec;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;
@MessagingGateway(defaultReplyChannel = "inChannel")
public interface FileGateWay {

    void writeToFile(@Header(FileHeaders.FILENAME) String filename, String date);
}
