package net.ent.etrs.gestionstagiaire.security.services;


import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.model.entities.MyUserDetails;
import net.ent.etrs.gestionstagiaire.model.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NonUniqueResultException;

@Service
@CommonsLog(topic = "SOUT")
public class MyUserDetailService implements UserDetailsService {

    //    @Autowired
    private UserRepo userRepo;


    public MyUserDetailService(UserRepo userRepo) {
        //TODO SOUT
        log.trace("MyUserDetailService constructor");
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.trace(">>>>>>>>>>MyUserDetailService/loadUserByUsername");

        try {
            log.trace("username: " + username);
//            MyUser user = userRepo.findUserByUsername(username).orElseThrow(() ->new NonUniqueResultException("tutututututu"));
            MyUserDetails myUser = userRepo.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("tototototototototototo"));
            log.trace("user : " + myUser);
//            if (user == null) {
//                throw new UsernameNotFoundException("User not found with username: " + username);
//            }
//            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                    new ArrayList<>());
            return myUser;
        } catch (UsernameNotFoundException e) {
//            e.printStackTrace();
            throw new UsernameNotFoundException(e.getMessage());
        } catch (NonUniqueResultException e) {
            e.printStackTrace();
            throw new NonUniqueResultException(e.getMessage());
        }

//        try {
//
//            log.trace("username : " + username);
//            Objects.requireNonNull(username);
//            UserDetails user = userRepo.findUserByUsername(username)
//                    .orElseThrow(() -> new UsernameNotFoundException(String.format("Username[%s] not found",username)));
//
//            log.trace("user : " + user);
//            return user;
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }
//        log.trace("return nullllllllllll");
//        return null;
    }

//    public MyUserDetails save(UserDTO userDTO) {
//
//
//        MyUserDetails newUser = new MyUserDetails();
//        newUser.setUsername(userDTO.getUsername());
//        newUser.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
//        newUser.setAccountNonExpired(userDTO.isAccountNonExpired());
//        newUser.setEnabled(userDTO.isEnabled());
//        newUser.setCredentialsNonExpired(userDTO.isCredentialsNonExpired());
//        newUser.setAccountNonLocked(userDTO.isAccountNonLocked());
//
//        try {
//            log.trace(">>>>>>>>>>MyUserDetailService/save try");
//
//            MyUserDetails u = this.loadUserByUsername(newUser.getUsername());
//            return u;
//        } catch (UsernameNotFoundException | NonUniqueResultException e) {
//            log.trace(">>>>>>>>>>MyUserDetailService/save catch");
//            return userRepo.save(newUser);
//        }
//
//
//    }

//    private List<GrantedAuthority> getGrantedAuthorities(MyUser user) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        List<Role> roles = user.getRoles();
//        roles.forEach(r->authorities.add(new SimpleGrantedAuthority(r.toString())));
////        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        return authorities;
//    }
}
