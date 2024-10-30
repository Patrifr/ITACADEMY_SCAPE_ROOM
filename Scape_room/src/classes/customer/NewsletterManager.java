package classes.customer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NewsletterManager {
    private List<NewsletterObserver> subscribers = new ArrayList<>();

    public void subscribe(NewsletterObserver subscriber) {
        if (!subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
            System.out.println("The customer " + ((Customer)subscriber).getName() + " with email "
                    + ((Customer)subscriber).getEmail() + " has subscribed successfully to the newsletter");
        } else {
            System.out.println("Customer " + ((Customer)subscriber).getName() + " is already subscribed.");
        }
    }

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

    public void sendNewsletter(String content) {
        System.out.println("Sending newsletter: ");
        for (NewsletterObserver subscriber : subscribers) {
            subscriber.update(content);
        }
    }
}



