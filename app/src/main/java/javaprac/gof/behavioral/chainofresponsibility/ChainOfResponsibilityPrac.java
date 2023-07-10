package javaprac.gof.behavioral.chainofresponsibility;

import javaprac.Prac;


public class ChainOfResponsibilityPrac implements Prac {

    @Override
    public void runPrac() {
        ServerErrorReceiver serverErrorReceiver = new ServerErrorReceiver();
        EmailErrorReceiver emailErrorReceiver = new EmailErrorReceiver(serverErrorReceiver);
        IssueRaiser issueRaiser = new IssueRaiser(emailErrorReceiver);
        issueRaiser.raiseMessage("Email is not working as expected.");
        issueRaiser.raiseMessage("Server went down?");
        issueRaiser.raiseMessage("should fail as no receiver processes such message.");

        issueRaiser = new IssueRaiser();
        issueRaiser.raiseMessage("won't be processed by any receiver");
    }

    @Override
    public String getDescription() {
        return "Design Pattern - [behavioral] Chain of Responsibility";
    }
}
