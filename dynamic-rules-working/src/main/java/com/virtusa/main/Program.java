package com.virtusa.main;

import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import com.virtusa.event.Event;
import com.virtusa.event.OrderEvent;
import com.virtusa.rule.Condition;
import com.virtusa.rule.Rule;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Program {

    static public void main(String[] args) throws Exception {

        // Create an event that will be tested against the rule. In reality, the event would be read from some external store.
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setTransfer_count(14);
        orderEvent.setOperation_type("Withdrawl");
        orderEvent.setBankATM("Parent");
        orderEvent.setAmount(567891);
        orderEvent.setTax((float) 0.2);

        Rule highValueOrderWidgetsIncRule = new Rule();

        Condition highValueOrderCondition = new Condition();
        highValueOrderCondition.setField("transfer_count");
        highValueOrderCondition.setOperator(Condition.Operator.GREATER_THAN);
        highValueOrderCondition.setValue(12);

        Condition widgetsIncCustomerCondition = new Condition();
        widgetsIncCustomerCondition.setField("operation_type");
        widgetsIncCustomerCondition.setOperator(Condition.Operator.EQUAL_TO);
        widgetsIncCustomerCondition.setValue("Withdrawl");
        
        Condition widgetsIncCustomerCondition2 = new Condition();
        widgetsIncCustomerCondition2.setField("bankATM");
        widgetsIncCustomerCondition2.setOperator(Condition.Operator.EQUAL_TO);
        widgetsIncCustomerCondition2.setValue("Parent");


        // In reality, you would have multiple rules for different types of events.
        // The eventType property would be used to find rules relevant to the event
        highValueOrderWidgetsIncRule.setEventType(Rule.eventType.RETAILBANKING);

        highValueOrderWidgetsIncRule.setConditions(Arrays.asList(highValueOrderCondition, widgetsIncCustomerCondition,widgetsIncCustomerCondition2));

        String drl = applyRuleTemplate(orderEvent, highValueOrderWidgetsIncRule);
        AlertDecision alertDecision = evaluate(drl, orderEvent);



        // doAlert is false by default
        if (alertDecision.getDoAlert()) {
            System.out.println(orderEvent.getTax()*orderEvent.getAmount());
        }

    }

    static private AlertDecision evaluate(String drl, Event event) throws Exception {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write("src/main/resources/rule.drl", drl);
        kieServices.newKieBuilder(kieFileSystem).buildAll();

        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        StatelessKieSession statelessKieSession = kieContainer.getKieBase().newStatelessKieSession();

        AlertDecision alertDecision = new AlertDecision();
        statelessKieSession.getGlobals().set("alertDecision", alertDecision);
        statelessKieSession.execute(event);

        return alertDecision;
    }

    static private String applyRuleTemplate(Event event, Rule rule) throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        ObjectDataCompiler objectDataCompiler = new ObjectDataCompiler();

        data.put("rule", rule);
        data.put("eventType", event.getClass().getName());

        return objectDataCompiler.compile(Arrays.asList(data), Thread.currentThread().getContextClassLoader().getResourceAsStream("rule-template.drl"));
    }
}
