package hung.nguyen.itc.service;

import hung.nguyen.itc.dto.UserDto;
import hung.nguyen.itc.entity.User;
import io.netty.util.internal.StringUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {
    public List<User> userList() {
        return User.listAll();
    }

    @Transactional
    public User saveUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.persist();
        return user;
    }

    @Transactional
    public void updateUser(Long id, UserDto userDto) {
        User user = new User();
        Optional<User> userOptional = User.findByIdOptional(id);
        if (StringUtil.isNullOrEmpty(userOptional.toString())) {
            throw new WebApplicationException("User not found", 404);
        }
        user = userOptional.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(user.getPhoneNumber());
        user.persist();
    }

    public void removeUser(Long id) {
        Optional<User> userOptional = User.findByIdOptional(id);
        if (StringUtil.isNullOrEmpty(userOptional.toString())) {
            throw new WebApplicationException("User not found", 404);
        }
        User user = userOptional.get();
        user.delete();
    }
}
