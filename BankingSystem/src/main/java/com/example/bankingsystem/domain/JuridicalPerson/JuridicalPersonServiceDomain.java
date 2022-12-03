package com.example.bankingsystem.domain.JuridicalPerson;

import com.example.bankingsystem.data.juridicalPerson.JuridicalPersonStorage;
import com.example.bankingsystem.data.role.RoleStorage;
import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import com.example.bankingsystem.domain.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class JuridicalPersonServiceDomain implements JuridicalPersonService {
    @Autowired
    @Lazy
    protected PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private JuridicalPersonStorage juridicalPersonStorage;

    @Autowired
    private RoleStorage roleStorage;
    @Override
    public List<JuridicalPerson> getPersonList(String personName) {
        return juridicalPersonStorage.getAllPerson(personName);
    }

    @Override
    public List<JuridicalPerson> getPersonBySurnameLike(String surname) {
        return juridicalPersonStorage.getAllPerson(surname);
    }

    @Override
    public int setPersonList(int branchId, int roleId, String surname, String name, String patronymic, String personName, String password, int sum) {
        return juridicalPersonStorage.setJuridicalPerson(branchId, roleId, surname, name, patronymic, personName, bCryptPasswordEncoder.encode(password), sum);
    }

    @Override
    public int deletePersonList(String surname) {
        return juridicalPersonStorage.deleteJuridicalPerson(surname);
    }

    @Override
    public int addSum(String personName, int sum) {
        return juridicalPersonStorage.addSum(personName, sum);
    }

    @Override
    public int transfer(String leftPerson, String rightPerson, int sum) {
        return juridicalPersonStorage.transfer(leftPerson, rightPerson, sum);
    }

    @Override
    public boolean saveClient(JuridicalPerson juridicalPerson) {
        return false;
    }

    /*@Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<JuridicalPerson> list = juridicalPersonStorage.getAllPerson(s);
        if (list.isEmpty()) throw new InternalAuthenticationServiceException("Person not found!");
        else {
            JuridicalPerson person = list.get(0);
            System.out.println(mapRolesToAuthorities(roleStorage.getRoleById(person.getRoleId())));
            return new User(person.getJuridicalPersonName(), person.getPassword(), mapRolesToAuthorities(roleStorage.getRoleById(person.getRoleId())));
        }
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roleById) {
        return roleById.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }*/
}
