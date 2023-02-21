package ru.lenivtsev.market.orders.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class OrderEventListener {

    @EventListener(condition = "#event.status==2L")
    public void handleOrderEvent(OrderEvent event){
        String to = "geekshop@gmail.com";
        String from = event.getOrder().getEmail();
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Заказ успешно оформлен!");
            message.setText("Ваш заказ оформлен./n" +
                    "Дата доставки: " + event.getOrder().getDeliveryDate() +
                    "Адрес доставки: " + event.getOrder().getDeliveryAddress());
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
