package mbean;


import java.text.SimpleDateFormat;
import java.util.Properties;
import javax.management.*;

public class RemoteMessageListener implements NotificationListener {
	
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd - H:mm:ss");

    public RemoteMessageListener() {
        super();
    }

    private void trace (String message) {
            System.out.println(message);
    }
    
    public void handleNotification(Notification notification, Object handback) {
    	if(notification.getType().equals("jmx.attribute.change")){
	    	try {
	        	Properties props = (Properties)notification.getUserData();
	        	String response = (String)props.get("attribute");
	            String response2 = (String)props.get("value");
	        	System.out.println("<<Remote>> "+notification.getType() + " number "+ notification.getSequenceNumber() + " in MBean " + notification.getSource() + " with attribute = "+ response + " value = "+response2 + " at "+ formatter.format(notification.getTimeStamp()));
	        } catch (Exception e) {
	        	trace(e.toString());
	        }
    	}else
    		System.out.println("<<Remote>> "+notification.getType() + " in MBean " + notification.getSource() + " at "+ formatter.format(notification.getTimeStamp()));
    }
}