package co.aurasphere.tci.messenger.platform;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class HeaderOutInterceptor extends AbstractPhaseInterceptor<Message>{
	
	public HeaderOutInterceptor(){
		super(Phase.SEND);
	}

	public void handleFault(Message message) {
		System.out.println(message.get(Message.CONTENT_TYPE));
	}

	public void handleMessage(Message message) throws Fault {
		message.put(Message.CONTENT_TYPE, "application/soap+xml");
		System.out.println(message.get(Message.CONTENT_TYPE));		
	}

}
