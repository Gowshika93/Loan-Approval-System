/*
 * package com.bank.loan.auth_service;
 * 
 * import java.util.Optional;
 * 
 * import org.junit.jupiter.api.Assertions; import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.api.extension.ExtendWith; import
 * org.mockito.InjectMocks; import org.mockito.Mock; import org.mockito.Mockito;
 * import org.mockito.junit.jupiter.MockitoExtension;
 * 
 * import com.bank.loan.auth_service.entity.User; import
 * com.bank.loan.auth_service.repository.UserRepository; import
 * com.bank.loan.auth_service.service.AuthService;
 * 
 * @ExtendWith(MockitoExtension.class) class AuthServiceTest {
 * 
 * @Mock private UserRepository userRepository;
 * 
 * @InjectMocks private AuthService authService;
 * 
 * @Test void shouldAuthenticateUser() {
 * 
 * User user = new User(1L,"admin","password");
 * 
 * Mockito.when(userRepository.findByUsername("admin"))
 * .thenReturn(Optional.of(user));
 * 
 * boolean result = authService.validateUser("admin","password");
 * 
 * Assertions.assertTrue(result); } }
 */