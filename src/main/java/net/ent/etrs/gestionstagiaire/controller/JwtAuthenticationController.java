package net.ent.etrs.gestionstagiaire.controller;

import io.jsonwebtoken.SignatureException;
import lombok.extern.apachecommons.CommonsLog;
import net.ent.etrs.gestionstagiaire.controller.dto.UserDTO;
import net.ent.etrs.gestionstagiaire.model.entities.MyUserDetails;
import net.ent.etrs.gestionstagiaire.model.repo.UserRepo;
import net.ent.etrs.gestionstagiaire.security.jwt.JwtTokenUtil;
import net.ent.etrs.gestionstagiaire.security.services.MyUserDetailService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@CommonsLog(topic = "SOUT")
//@CrossOrigin
public class JwtAuthenticationController {


    private AuthenticationManager authenticationManager;

    //    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    //    @Autowired
    private MyUserDetailService userDetailsService;

    private UserRepo userRepo;


    private PasswordEncoder encoder;
//    @Autowired
//    private PasswordEncoder bcryptEncoder;
//
//    @Autowired
//    private UserRepo userRepo;


    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, MyUserDetailService userDetailsService, PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<MyUserDetails> createAuthenticationToken(@RequestBody UserDTO userDTO) {
        log.trace("JwtAuthenticationController / createAuthenticationToken");
        log.trace("authenticationRequest.getUsername() : " + userDTO.getUsername());
        log.trace("authenticationRequest.getPassword() : " + userDTO.getPassword());

        try {
            this.authenticate(userDTO.getUsername(), userDTO.getPassword());

            log.trace("JwtAuthenticationController / createAuthenticationToken 2");
            log.trace("SecurityContextHolder.getContext().getAuthentication().getPrincipal() : " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
            log.trace("SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass() : " + SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass());

//            UserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(userDTO.getUsername());

            log.trace("userDetails : " + userDetails);
            log.trace("JwtAuthenticationController / createAuthenticationToken 3");
            final String token = jwtTokenUtil.generateToken(userDetails);
            log.trace("JwtAuthenticationController / createAuthenticationToken 4");
//			return ResponseEntity.ok(new JwtResponse(token));
            MyUserDetails myUser = new MyUserDetails();
            myUser.setUsername(userDetails.getUsername());
//			return ResponseEntity.ok().header("Authorization",token).body(myUser);
            HttpHeaders responseHeaders = new HttpHeaders();
//			responseHeaders.setLocation(location);
            responseHeaders.set("Authorization", token);
            return new ResponseEntity<MyUserDetails>(myUser, responseHeaders, HttpStatus.OK);
        } catch (UsernameNotFoundException | BadCredentialsException | LockedException | DisabledException |
                 SignatureException e) {
            log.trace(">>>>>>>>>>>>>>>>>>>>>ERR");
//            e.printStackTrace();
//			throw new Exception(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e1) {
//            e1.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping(value = "/register")
    public ResponseEntity<MyUserDetails> saveUser(@RequestBody UserDTO user) throws Exception {
        log.trace("JwtAuthenticationController / saveUser");
        MyUserDetails myUserDetails = MyUserDetails.builder()
                .username(user.getUsername())
                .password(encoder.encode(user.getPassword()))
//                .roles(user.roles())
                .build();
        userRepo.save(myUserDetails);
        return ResponseEntity.ok(myUserDetails);
    }


    private Authentication authenticate(String username, String password) throws Exception {
        try {
            log.trace("JwtAuthenticationController / authenticate");
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
//            e.printStackTrace();
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
//            e.printStackTrace();
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
