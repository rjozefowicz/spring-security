package dev.jozefowicz.springsecurity.methodsecurity.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @BeforeEach
    public void setup() {
        paymentService.clear();
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void shouldRejectNewPaymentForNonAdminRole() {
        assertThrows(AccessDeniedException.class, () -> {
            paymentService.addPayment(new PaymentService.Payment("user"));
        });
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void shouldAddPaymentForAdminRole() {
        // given

        // when
        paymentService.addPayment(new PaymentService.Payment("user"));

        // then
        assertEquals(1, paymentService.myPayments().size());
    }


    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void shouldReturnOnlyUserPayments() {
        // given

        // when
        paymentService.addPayment(new PaymentService.Payment("user"));
        paymentService.addPayment(new PaymentService.Payment("user"));
        paymentService.addPayment(new PaymentService.Payment("user1"));

        // then
        assertEquals(2, paymentService.myPayments().size());
    }

    @Test
    public void shouldReturnOnlyUserPayments_programmaticUserLogin() {
        // given
        login("user", "password", "ROLE_ADMIN");

        // when
        paymentService.addPayment(new PaymentService.Payment("user1"));
        paymentService.addPayment(new PaymentService.Payment("user1"));
        paymentService.addPayment(new PaymentService.Payment("user1"));
        paymentService.addPayment(new PaymentService.Payment("user1"));

        // then
        login("user1", "password", "ROLE_USER");
        assertEquals(4, paymentService.myPayments().size());
    }

    private void login(String username, String password, String... roles) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User user = new User(username, password, roles(roles));

        Authentication auth = new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
    }

    private List<SimpleGrantedAuthority> roles(String... roles) {
        return Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(toList());
    }
}
