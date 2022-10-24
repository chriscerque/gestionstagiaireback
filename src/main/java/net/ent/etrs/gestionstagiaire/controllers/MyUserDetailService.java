package net.ent.etrs.gestionstagiaire.controllers;


import net.ent.etrs.gestionstagiaire.controllers.dto.UserDTO;
import net.ent.etrs.gestionstagiaire.model.entities.MyUser;
import net.ent.etrs.gestionstagiaire.model.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;

@Service
public class MyUserDetailService implements UserDetailsService {

    //    @Autowired
    private UserRepo userRepo;

    //    @Autowired
    private PasswordEncoder bcryptEncoder;

    public MyUserDetailService(UserRepo userRepo, PasswordEncoder bcryptEncoder) {
        //TODO SOUT
        System.out.println("MyUserDetailService constructor");
        this.userRepo = userRepo;
        this.bcryptEncoder = bcryptEncoder;
    }

    @Override
    public MyUser loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(">>>>>>>>>>MyUserDetailService/loadUserByUsername");

        try {
            System.out.println("username: " + username);
//            MyUser user = userRepo.findUserByUsername(username).orElseThrow(() ->new NonUniqueResultException("tutututututu"));
            MyUser myUser = userRepo.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("tototototototototototo"));
            System.out.println("user : " + myUser);
//            if (user == null) {
//                throw new UsernameNotFoundException("User not found with username: " + username);
//            }
//            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                    new ArrayList<>());
            return myUser;
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(e.getMessage());
        } catch (NonUniqueResultException e) {
            e.printStackTrace();
            throw new NonUniqueResultException(e.getMessage());
        }

//        try {
//
//            System.out.println("username : " + username);
//            Objects.requireNonNull(username);
//            UserDetails user = userRepo.findUserByUsername(username)
//                    .orElseThrow(() -> new UsernameNotFoundException(String.format("Username[%s] not found",username)));
//
//            System.out.println("user : " + user);
//            return user;
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }
//        System.out.println("return nullllllllllll");
//        return null;
    }

    public MyUser save(UserDTO userDTO) {


        MyUser newUser = new MyUser();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        newUser.setAccountNonExpired(userDTO.isAccountNonExpired());
        newUser.setEnabled(userDTO.isEnabled());
        newUser.setCredentialsNonExpired(userDTO.isCredentialsNonExpired());
        newUser.setAccountNonLocked(userDTO.isAccountNonLocked());

        try {
            System.out.println(">>>>>>>>>>MyUserDetailService/save try");

            MyUser u = this.loadUserByUsername(newUser.getUsername());
            return u;
        } catch (UsernameNotFoundException | NonUniqueResultException e) {
            System.out.println(">>>>>>>>>>MyUserDetailService/save catch");
            return userRepo.save(newUser);
        }


    }

//    private List<GrantedAuthority> getGrantedAuthorities(MyUser user) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        List<Role> roles = user.getRoles();
//        roles.forEach(r->authorities.add(new SimpleGrantedAuthority(r.toString())));
////        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        return authorities;
//    }
}
