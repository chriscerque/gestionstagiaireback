package net.ent.etrs.gestionstagiaire.model.facades.api;

import io.jsonwebtoken.SignatureException;
import net.ent.etrs.gestionstagiaire.config.JwtTokenUtil;
import net.ent.etrs.gestionstagiaire.model.entities.MyUser;
import net.ent.etrs.gestionstagiaire.model.facades.api.dto.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@RestController
//@CrossOrigin
public class JwtAuthenticationController {


    private AuthenticationManager authenticationManager;

    //    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    //    @Autowired
    private MyUserDetailService userDetailsService;

//    @Autowired
//    private PasswordEncoder bcryptEncoder;
//
//    @Autowired
//    private UserRepo userRepo;


    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, MyUserDetailService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO userDTO) {
        System.out.println("JwtAuthenticationController / createAuthenticationToken");
        System.out.println("authenticationRequest.getUsername() : " + userDTO.getUsername());
        System.out.println("authenticationRequest.getPassword() : " + userDTO.getPassword());

        try {
            this.authenticate(userDTO.getUsername(), userDTO.getPassword());

            System.out.println("JwtAuthenticationController / createAuthenticationToken 2");
            System.out.println("SecurityContextHolder.getContext().getAuthentication().getPrincipal() : " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());

            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(userDTO.getUsername());
            System.out.println("userDetails : " + userDetails);
            System.out.println("JwtAuthenticationController / createAuthenticationToken 3");
            final String token = jwtTokenUtil.generateToken(userDetails);
            System.out.println("JwtAuthenticationController / createAuthenticationToken 4");
//			return ResponseEntity.ok(new JwtResponse(token));
            MyUser myUser = new MyUser();
            myUser.setUsername(userDetails.getUsername());
//			return ResponseEntity.ok().header("Authorization",token).body(myUser);
            HttpHeaders responseHeaders = new HttpHeaders();
//			responseHeaders.setLocation(location);
            responseHeaders.set("Authorization", token);
            return new ResponseEntity<MyUser>(myUser, responseHeaders, HttpStatus.OK);
        } catch (UsernameNotFoundException | BadCredentialsException | LockedException | DisabledException |
                 SignatureException e) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>ERR");
            e.printStackTrace();
//			throw new Exception(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e1) {
//            e1.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
        System.out.println("JwtAuthenticationController / saveUser");
        MyUser myUser = userDetailsService.save(user);
        return ResponseEntity.ok(myUser);
    }


    private void authenticate(String username, String password) throws BadCredentialsException, DisabledException, LockedException {
//        try {
        System.out.println("JwtAuthenticationController / authenticate");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            e.printStackTrace();
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            e.printStackTrace();
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
    }
}
