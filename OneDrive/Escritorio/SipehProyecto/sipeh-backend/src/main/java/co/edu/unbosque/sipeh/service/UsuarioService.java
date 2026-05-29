package co.edu.unbosque.sipeh.service;

import co.edu.unbosque.sipeh.dto.LoginDTO;
import co.edu.unbosque.sipeh.dto.UsuarioDTO;
import co.edu.unbosque.sipeh.dto.UsuarioRegistroDTO;
import co.edu.unbosque.sipeh.model.Usuario;
import co.edu.unbosque.sipeh.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO guardarUsuario(UsuarioRegistroDTO dto) {
        Usuario usuario = convertToEntity(dto);
        Usuario guardado = usuarioRepository.save(usuario);
        return convertToDTO(guardado);
    }

    public List<UsuarioDTO> listarDocentes() {
        return usuarioRepository.findByRol("DOCENTE").stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO guardarDocente(UsuarioRegistroDTO dto) {
        Usuario docente = convertToEntity(dto);
        docente.setRol("DOCENTE");
        Usuario guardado = usuarioRepository.save(docente);
        return convertToDTO(guardado);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }


    public UsuarioDTO login(LoginDTO datosLogin) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(datosLogin.getEmail());

        if (usuarioExistente.isPresent()) {
            Usuario user = usuarioExistente.get();
            if (user.getPassword().equals(datosLogin.getPassword())) {
                return convertToDTO(user);
            } else {
                throw new RuntimeException("Contraseña incorrecta");
            }
        }
        throw new RuntimeException("Usuario no encontrado");
    }


    private UsuarioDTO convertToDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail(),
                usuario.getRol(), usuario.getVinculacion(), usuario.getEscalafon(),
                usuario.getRestriccionesHorario());
    }

    private Usuario convertToEntity(UsuarioRegistroDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(dto.getRol());
        usuario.setVinculacion(dto.getVinculacion());
        usuario.setEscalafon(dto.getEscalafon());
        usuario.setRestriccionesHorario(dto.getRestriccionesHorario());
        return usuario;
    }
}