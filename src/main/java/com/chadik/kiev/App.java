package com.chadik.kiev;

import com.chadik.kiev.model.Trader;
import com.chadik.kiev.service.ITraderJpaService;
import com.chadik.kiev.service.impl.TraderJpaServiceImpl;
import com.chadik.kiev.view.FrameMain;


import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        
        ApplicationContext appContext = 
    	  new ClassPathXmlApplicationContext("applicationContext.xml");
 
    	TraderJpaServiceImpl traderJpaServiceImpl = (TraderJpaServiceImpl) appContext.getBean("traderJpaServiceImpl");
        System.out.println("hello traderJpaServiceImpl");
        
        Trader trader = new Trader();
        trader.setTraderName("ivan1");
        trader.setTraderAddress("ivan2");
        trader.setTraderBankAccount("ivan3");
        trader.setTraderBankName("ivan4");
        trader.setTraderRegistryNumber("pile5");        
        
        traderJpaServiceImpl.save(trader);
        
        trader.setTraderRegistryNumber("чадиковски");        
        
        traderJpaServiceImpl.save(trader);        

        System.out.println("bye " + trader.getTraderName());
        
        FrameMain frameMain = (FrameMain)appContext.getBean("frameMain");
        frameMain.initFrame(); 
        
        List<Trader> traderNo = new ArrayList<Trader>();
        traderNo = traderJpaServiceImpl.getAll();
        
        Trader trader0 = traderNo.get(0);
        System.out.println("ovovjv:" + trader0.getTraderBankName());
        
        System.out.println("no " + traderNo.size());
        
   	
    }
}
