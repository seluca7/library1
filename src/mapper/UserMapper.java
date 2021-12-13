package mapper;

import dto.NameIdDTO;
import dto.UserDTO;
import model.User;

public class UserMapper {

    public NameIdDTO toNameIdDTO(User user) {
        NameIdDTO nameIdDTO = new NameIdDTO();

        nameIdDTO.setName(user.getUsername());
        nameIdDTO.setId(user.getId());

        return nameIdDTO;
    }

    public UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setAdmin(user.isAdmin());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());

        return userDTO;

    }
}


