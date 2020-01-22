package dev.jozefowicz.springsecurity.methodsecurity.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DummyServiceTest {

    @Autowired
    private DummyService dummyService;

    @Test
    @WithMockUser(username = "user", roles = {"EDITOR"})
    public void shouldNotAllowNonAdminRoles() {
        assertThrows(AccessDeniedException.class, () -> {
            dummyService.admin();
        });
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void shouldAllowAdminRole() {
        dummyService.admin();
    }

    @Test
    @WithMockUser(username = "user", roles = {"EDITOR"})
    public void shouldNotAllowNonAdminRoles_IsAdminAnnotation() {
        assertThrows(AccessDeniedException.class, () -> {
            dummyService.admin2();
        });
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void shouldAllowAdminRole_IsAdminAnnotation() {
        dummyService.admin2();
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void shouldNotAllowNonEditorRoles() {
        assertThrows(AccessDeniedException.class, () -> {
            dummyService.editor();
        });
    }

    @Test
    @WithMockUser(username = "user", roles = {"EDITOR"})
    public void shouldAllowEditorRole() {
        dummyService.editor();
    }

    @Test
    @WithMockUser(username = "user", roles = {"ADMIN"})
    public void shouldNotAllowNonReaderRoles() {
        assertThrows(AccessDeniedException.class, () -> {
            dummyService.reader();
        });
    }

    @Test
    @WithMockUser(username = "user", roles = {"READER"})
    public void shouldAllowReaderRole() {
        dummyService.reader();
    }

    @Test
    @WithAnonymousUser
    public void shouldNotAllowAnonymousUserWhenAdminRoleRequired() {
        assertThrows(AccessDeniedException.class, () -> {
            dummyService.admin();
        });
    }

    @Test
    @WithAnonymousUser
    public void shouldAllowAnonymousUser() {
        dummyService.anyUser();
    }

}
