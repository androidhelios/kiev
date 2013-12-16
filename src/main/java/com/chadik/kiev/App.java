package com.chadik.kiev;

import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.ITraderJpaService;
import com.chadik.kiev.service.impl.TraderJpaServiceImpl;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        
        ApplicationContext appContext = 
    	  new ClassPathXmlApplicationContext("applicationContext.xml");
 
    	TraderJpaServiceImpl traderJpaServiceImpl = (TraderJpaServiceImpl)appContext.getBean("traderJpaServiceImpl");
        System.out.println("Hello World!");

        Trader trader = new Trader();
        trader.setTraderName("ivan");
        trader.setTraderAddress("ivan");
        trader.setTraderBankAccount("ivan");
        trader.setTraderBankName("ivan");
        trader.setTraderRegistryNumber("pile");        
        
        traderJpaServiceImpl.save(trader);
        
        trader.setTraderRegistryNumber("chadikovski");        
        
        traderJpaServiceImpl.save(trader);
        

        System.out.println("Bye " + trader.getTraderName() + "!");
    }
}
