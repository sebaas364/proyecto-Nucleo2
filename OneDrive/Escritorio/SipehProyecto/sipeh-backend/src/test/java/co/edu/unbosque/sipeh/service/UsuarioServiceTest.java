package co.edu.unbosque.sipeh.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.edu.unbosque.sipeh.dto.LoginDTO;
import co.edu.unbosque.sipeh.dto.UsuarioDTO;
import co.edu.unbosque.sipeh.model.Usuario;
import co.edu.unbosque.sipeh.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

	@Mock
	private UsuarioRepository usuarioRepository;

	@InjectMocks
	private UsuarioService usuarioService;

	@Test
	void testLogin_Exitoso() {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("froid@unbosque.edu.co");
		loginDTO.setPassword("1234");

		Usuario usuarioMock = new Usuario();
		usuarioMock.setId(1L);
		usuarioMock.setNombre("Freud David");
		usuarioMock.setEmail("froid@unbosque.edu.co");
		usuarioMock.setPassword("1234");
		usuarioMock.setRol("ESTUDIANTE");

		when(usuarioRepository.findByEmail("froid@unbosque.edu.co")).thenReturn(Optional.of(usuarioMock));

		UsuarioDTO resultado = usuarioService.login(loginDTO);

		assertNotNull(resultado);
		assertEquals("Freud David", resultado.getNombre());
		assertEquals("ESTUDIANTE", resultado.getRol());
	}

	@Test
	void testLogin_ContrasenaIncorrecta_DeberiaLanzarExcepcion() {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail("froid@unbosque.edu.co");
		loginDTO.setPassword("claveMala");

		Usuario usuarioMock = new Usuario();
		usuarioMock.setEmail("froid@unbosque.edu.co");
		usuarioMock.setPassword("1234");

		when(usuarioRepository.findByEmail("froid@unbosque.edu.co")).thenReturn(Optional.of(usuarioMock));

		Exception exception = assertThrows(RuntimeException.class, () -> {
			usuarioService.login(loginDTO);
		});

		assertEquals("Contraseña incorrecta", exception.getMessage());
	}
}