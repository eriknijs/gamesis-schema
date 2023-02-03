package gamesis.mail.server;

import gamesis.mail.model.MailRequest;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.jms.annotation.JmsListener;

/**
 *
 */
@Server
public class MailRequestConsumer implements IOnMailRequest{

    @JmsListener(destination = "mailrequest", containerFactory = "jmsConnectioFactory")
    @Override
    public void onMailRequest(MailRequest value) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
