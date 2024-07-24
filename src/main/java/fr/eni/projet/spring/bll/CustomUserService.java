package fr.eni.projet.spring.bll;

import fr.eni.projet.spring.bo.Utilisateur;
import fr.eni.projet.spring.dal.Utilisateur.UtilisateurRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserService implements UserDetailsService {

    private UtilisateurRepository utilisateurRepository;

    public CustomUserService(UtilisateurRepository utilisateurRepository) {
        super();
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utilisateur utilisateur = utilisateurRepository.findByEmail(username);

        if (utilisateur == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return utilisateur;
    }


}
