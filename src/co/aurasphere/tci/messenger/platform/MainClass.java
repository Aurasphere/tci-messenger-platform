package co.aurasphere.tci.messenger.platform;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.message.Message;

import com.contactlab.api.ws.ClabService;
import com.contactlab.api.ws.ClabService_Service;
import com.contactlab.api.ws.domain.AuthToken;
import com.contactlab.api.ws.domain.Subscriber;

public class MainClass {
	
	public static void main(String[] args) {
		// Mock
//		ClabService service = new ClabServicePortImpl();
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ClabService.class);
        factory.setAddress("https://soap.contactlab.it/soap/services");
        List<Interceptor<? extends Message>> interceptors = new ArrayList<Interceptor<? extends Message>>();
        interceptors.add(new HeaderOutInterceptor());
        factory.setOutInterceptors(interceptors);
        
        ClabService service = (ClabService) factory.create();
		AuthToken borrowToken = service.borrowToken("99999999642df19f605f16405302dc3b1c04d835", "00000000a890cc7a573282866db7e014af3a5757");
		System.out.println(borrowToken.getContent());
		
		Subscriber subscriber = new Subscriber();
		subscriber.setIdentifier(1);
		Subscriber addedSubscriber = service.addSubscriber(null, 1, subscriber);
		System.out.println(addedSubscriber.getIdentifier());
		
		Subscriber subscriberRetrieved = service.getSubscriber(borrowToken, 1, addedSubscriber.getIdentifier());
		System.out.println(subscriberRetrieved.getIdentifier());
	}

}
