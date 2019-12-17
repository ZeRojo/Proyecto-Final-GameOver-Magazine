package gameover.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gameover.dao.iface.IntUsuarioDAO;
import gameover.models.Usuario;

@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {

	@Autowired
	private IntUsuarioDAO usuarioDAO;
	
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
		Usuario usuario=usuarioDAO.findUserByUserName(nombre);
		UserBuilder builder=null;
		if (usuario!=null) {
			builder=User.withUsername(nombre);
			builder.disabled(!usuario.isActivo());
			builder.password(usuario.getPassword());
			String[] rangos=usuario.getRangos().stream().map(a -> a.getRango()).toArray(String[]::new);
			builder.authorities(rangos);
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		return builder.build();
	}
}
