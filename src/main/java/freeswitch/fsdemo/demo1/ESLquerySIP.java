package freeswitch.fsdemo.demo1;

import org.freeswitch.esl.client.inbound.InboundConnectionFailure;
import org.freeswitch.esl.client.transport.message.EslMessage;

/**
 * @Author lihongwen
 * @Date ${date} 16:56
 */
public class ESLquerySIP {



/*


*//*
    Java代码  收藏代码*//*

    public void sofia_contact(){
        Client client = new Client();
        try
        {
            client.connect( host, port, password, 2 );
        }
        catch ( InboundConnectionFailure e )
        {
            log.error( "Connect failed", e );
            return;
        }

        //     EslMessage response = client.sendSyncApiCommand( "sofia_contact", "internal/102@192.168.100.201" );
        //回拨两方通话  {origination_caller_id_number=95126,hangup_after_bridge=true,continue_on_fail=true,originate_timeout=60}
        //     EslMessage response = client.sendSyncApiCommand( "originate", " {origination_caller_id_number=95126,hangup_after_bridge=true,continue_on_fail=true,originate_timeout=60}sofia/gateway/pstn/1118601323147 1003 XML default"  );

        EslMessage response = client.sendSyncApiCommand("sofia xmlstatus", "profile internal reg 1002");
        // client.
        // 会议回拨
        // originate user/18601323147 conference:conf_uuid-TEST_CON inline


        log.info( "Response to 'sofia_contact': [{}]", response );
        for ( Entry<Name, String> header : response.getHeaders().entrySet() )
        {
            log.info( " * header [{}]", header );
        }
        for ( String bodyLine : response.getBodyLines() )
        {
            log.info( " * body [{}]", bodyLine );
        }
        client.close();
    }*/

}
