package freeswitch.fsdemo.demo1;

import org.freeswitch.esl.client.IEslEventListener;
import org.freeswitch.esl.client.inbound.Client;
import org.freeswitch.esl.client.inbound.InboundConnectionFailure;
import org.freeswitch.esl.client.transport.event.EslEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lihongwen
 * @Date ${date} 08:41
 */
@RestController
public class text {

    private static String host = "106.12.118.138";//服务器的ip地址
    private static int port = 8021;//服务器的端口(在服务器上netstat -anp | grep 8021 查看正常)
    private static String password = "ClueCon1";//event_socket.conf.xml配置文件中的密码

    private static final Logger log = LoggerFactory.getLogger(text.class);

    @RequestMapping("hello")
    public Client isban() {
        //需要修改fs的配置文件
        // C:\Program Files\FreeSWITCH\conf\autoload_configs\event_socket.conf （linux上  fs配置文件的路径）
        // usr/local/freeswitch/conf/autoload_configs/event_socket.conf.xml （Windows上  fs配置文件的路径）
        // reload mod_event_socket (修改完敲命令)
        final Client client = new Client();
        try {
            client.connect(host, port, password, 2);
            log.info("连接成功");
        } catch (InboundConnectionFailure inboundConnectionFailure) {
            inboundConnectionFailure.printStackTrace();
        }

        client.addEventListener(new IEslEventListener() {
            @Override
            public void eventReceived(EslEvent event) {
                System.out.println("Event received [{}]" + event.getEventHeaders());
                //记录接听次数和时间
                if (event.getEventName().equals("CHANNEL_ANSWER")) {
                    //your code here
                }
                if (event.getEventName().equals("HEARTBEAT")) {
                    System.out.println("recieved Hearbeat event !" + event.getEventBodyLines());
                }
                if (event.getEventName().equals("CHANNEL_DESTROY")) {

                }
                if (event.getEventName().equals("CHANNEL_HANGUP_COMPLETE")) {
                    //挂断
                }

            }

            @Override
            public void backgroundJobResultReceived(EslEvent event) {
                String uuid = event.getEventHeaders().get("Job-UUID");
                log.info("Background job result received+:" + event.getEventName() + "/" + event.getEventHeaders());// +"/"+JoinString(event.getEventHeaders())+"/"+JoinString(event.getEventBodyLines()));
            }
        });
        client.setEventSubscriptions("plain", "all");

        String result = client.sendAsyncApiCommand("", "");//发送？？命令
        /*拨打电话*/
        //client.sendSyncApiCommand( "bgapi originate", "sofia/external/" + mobile + "@FreeSwitch的IP &playback(ivr/8000/mydoctestv2.wav)" );
        /*freeswitch 用ESL 查询sip用户注册状态*/
        //EslMessage response = client.sendSyncApiCommand("sofia xmlstatus", "profile internal reg 1002");

        return client;

    }


    private void text() {

    }

    /*@GetMapping("phone")
    public void call(String phone){
        Client client = isban();
        dialPhone(client,phone);
    }


    private void dialPhone(Client client,String phone){
        if(client != null){
            String response = client.sendAsyncApiCommand("bgapi originate", "sofia/external/" + phone + "@106.12.97.34 &echo");
            System.out.println("-----------------打电话------------------");
            System.out.println("-----------------打电话------------------");
            System.out.println("-----------------打电话------------------");
            System.out.println(response);
            System.out.println("-----------------打电话------------------");
            System.out.println("-----------------打电话------------------");
            System.out.println("-----------------打电话------------------");

            //String response = client.sendAsyncApiCommand( "originate", "sofia/external/" + mobile + "@192.168.188.222 &playback(ivr/8000/mydoctestv2.wav)" );  //mydoctestv2
            // System.err.println("reponse--->" + response);
        }else{
            log.info("！！！！！！！！！client为null！！！！！！！！");
        }
    }*/

}
