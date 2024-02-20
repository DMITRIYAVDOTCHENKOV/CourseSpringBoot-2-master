package com.example.hw_lesson4.configuration;

import com.example.hw_lesson4.model.Employee;
import com.example.hw_lesson4.servicec.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

//@Configuration
//public class IntegrationConfig {
//
//    @Bean
//    public MessageChannel textInputChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public MessageChannel fileWriterChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    @Transformer(inputChannel = "textInputChannel", outputChannel = "fileWriterChannel")
//    public GenericTransformer<String,String> mainTransformer() {
//        return text -> {
//            // любая логика
//            return text;
//        };
//    }
//
//    @Bean
//    @ServiceActivator(inputChannel = "fileWriterChannel")
//    public FileWritingMessageHandler messageHandler () {
//        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("D:/Java/java_Project/CourseSpringBoot-2-master/file/somefile.txt"));
//
//        handler.setExpectReply(false);
//        handler.setFileExistsMode(FileExistsMode.APPEND);
//        handler.setAppendNewLine(true);
//
//        return handler;
//    }
//}
//mport java.io.File;

@Configuration
public class IntegrationConfig {
    @Autowired
    DateService dateService;


    /** Входной канал
     * @return
     */
    @Bean
    public MessageChannel inChannel() {
        return new DirectChannel();
    }

    /** Выходной канал
     * @return
     */
    @Bean
    public MessageChannel logChannel() {
        return new DirectChannel();
    }

    /** Транслятор сообщений из канала inChannel в logChanel
     * преобразует сообщение типа User в строку с текущей датой в формате ГГГГ-ММ-ДД ЧЧ:мм<br>
     * id, name, login,<br>
     * пример: 2024-02-18 22:45:52:	id=5, name='Ivan', login='ivan78'
     * @return
     */
    @Bean
    @Transformer(inputChannel = "inChannel", outputChannel = "logChannel")
    public GenericTransformer<Employee, String> userToLogStringTranslator() {
        return user ->{
            String returnStr = dateService.get() + ":\tshowNewEmployeeForm{" +
                    "id=" + user.getId() +
                    ", name='" + user.getLastName() + "'" +
                    ", login='" + user.getFirstName() + "'}" +
                    ", login='" + user.getEmail() + "'}";
            return returnStr;
        };
    }


    /** Обработчик сообщения из канала logChannel.
     * Записывает строку из сообщения в файл, добавляя данные в конец файла.
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "logChannel")
    public FileWritingMessageHandler logWriteHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(
                new File("./log"));
        handler.setExpectReply(false);//отключение ожидания ответного сообщения после обработки входного сообщения
        handler.setFileExistsMode(FileExistsMode.APPEND);//добавление текста в конец файла
        handler.setAppendNewLine(true);//добавление перехода на новую строку в конце записи
        return handler;
    }

}