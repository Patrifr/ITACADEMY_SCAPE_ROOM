package customer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NewsletterManager {
    private List<NewsletterObserver> subscribers = new ArrayList<>();


    // Suscripció per nous customers
    public void subscribe(NewsletterObserver subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
            System.out.println("The customer " + ((Customer)subscriber).getName() + " with email "
                    + ((Customer)subscriber).getEmail() + " has subscribed successfully to the newsletter");
        } else {
            System.out.println("Customer " + ((Customer)subscriber).getName() + " is already subscribed.");
        }
    }

    // Donar de baixa customer
    public void unsubscribe(NewsletterObserver subscriber) {
        if (subscribers.isEmpty()) {
            System.out.println("There's no customers subscribed to the newsletter.");
        } else {
            Iterator<NewsletterObserver> iterator = subscribers.iterator();
            while (iterator.hasNext()) {
                NewsletterObserver customer = iterator.next();
                if (customer.equals(subscriber)) {
                    iterator.remove();
                    System.out.println("Customer " + ((Customer) customer).getName() + " removed from the newsletter list.");
                }
            }
        }
    }

    // Enviar newsletter
    public void sendNewsletter(String content) {
        System.out.println("Sending newsletter: " + content);
        for (NewsletterObserver subscriber : subscribers) {
            subscriber.update(content);
        }
    }

    // Métodes per notificar noticies concretes?
    public void clueNews(String cluedetails) {
        sendNewsletter("New clue added! : " + cluedetails);
    }

    public void roomNews(String roomName) {
        sendNewsletter("There's a new escape room! : " + roomName + "!");
    }

    public void decoNews(String roomName) {
        sendNewsletter("There's a new decoration in the escape room " + roomName + "!");
    }


}



