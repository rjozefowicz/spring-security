package dev.jozefowicz.springsecurity.methodsecurity.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class PaymentService {

    private final List<Payment> payments = new CopyOnWriteArrayList<>();

    @Secured("ROLE_ADMIN")
    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostFilter("filterObject.username == authentication.name")
    public List<Payment> myPayments() {
        return payments;
    }

    public void clear() {
        payments.clear();
    }

    public static class Payment {
        private final String username;

        public Payment(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }
}
