package com.lambdaschool.medcabinet.services;

import com.lambdaschool.medcabinet.exceptions.ResourceFoundException;
import com.lambdaschool.medcabinet.exceptions.ResourceNotFoundException;
import com.lambdaschool.medcabinet.handlers.HelperFunctions;
import com.lambdaschool.medcabinet.models.User;
import com.lambdaschool.medcabinet.models.Useremail;
import com.lambdaschool.medcabinet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Userservice Interface
 */
@Transactional
@Service(value = "userService")
public class UserServiceImpl
        implements UserService
{
    /**
     * Connects this service to the User table.
     */
    @Autowired
    private UserRepository userrepos;


    /**
     * Connects this service to the auditing service in order to get current user name
     */
    @Autowired
    private UserAuditing userAuditing;

    /**
     * Connects this service to the helper functions for this application
     */
    @Autowired
    private HelperFunctions helper;

    public User findUserById(long id)
            throws
            ResourceNotFoundException
    {
        return userrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public List<User> findByNameContaining(String username)
    {
        return userrepos.findByUsernameContainingIgnoreCase(username.toLowerCase());
    }

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        userrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);
    }

    @Override
    public User findByName(String name)
    {
        User uu = userrepos.findByUsername(name.toLowerCase());
        if (uu == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();

        if (user.getUserid() != 0)
        {
            User oldUser = userrepos.findById(user.getUserid())
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found!"));
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername()
                                    .toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail()
                                        .toLowerCase());

        newUser.getUseremails()
                .clear();
        for (Useremail ue : user.getUseremails())
        {
            newUser.getUseremails()
                    .add(new Useremail(newUser,
                                       ue.getUseremail()));
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(
            User user,
            long id)
    {
        User currentUser = findUserById(id);

        if (helper.isAuthorizedToMakeChange(currentUser.getUsername()))
        {
            if (user.getUsername() != null)
            {
                currentUser.setUsername(user.getUsername()
                                                .toLowerCase());
            }

            if (user.getPassword() != null)
            {
                currentUser.setPasswordNoEncrypt(user.getPassword());
            }

            if (user.getPrimaryemail() != null)
            {
                currentUser.setPrimaryemail(user.getPrimaryemail()
                                                    .toLowerCase());
            }

            if (user.getUseremails()
                    .size() > 0)
            {
                currentUser.getUseremails()
                        .clear();
                for (Useremail ue : user.getUseremails())
                {
                    currentUser.getUseremails()
                            .add(new Useremail(currentUser,
                                               ue.getUseremail()));
                }
            }

            return userrepos.save(currentUser);
        } else
        {
            {
                // note we should never get to this line but is needed for the compiler
                // to recognize that this exception can be thrown
                throw new ResourceNotFoundException("This user is not authorized to make change");
            }
        }
    }
}
