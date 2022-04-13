package knz.mathknigt.restApi.game;

import knz.mathknigt.database.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class Game {
    List<User>      users;
    List<Knight>    knights;
    boolean roundIsLeft;
    long    potential;

    public Game(Long potential){
        roundIsLeft     = true;
        this.users      = new ArrayList<>();
        this.knights    = new ArrayList<>();
        this.potential  = potential;
    }

    public boolean join(User newUser){
        if (users.contains(newUser) || users.size() > 1)
            return false;
        users.add(newUser);
        return true;
    }

    public boolean leave(User user){
        if (!users.contains(user))
            return false;
        users.remove(user);
        return true;
    }

    public boolean leaveById(int id){
        if (id < 0 || id > 1)
            return false;
        users.remove(id);
        return true;
    }

}
