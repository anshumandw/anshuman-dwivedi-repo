package com.objectModellingFile.codingame.services;

import java.util.List;

import com.objectModellingFile.codingame.dtos.UserRegistrationDto;
import com.objectModellingFile.codingame.entities.ScoreOrder;
import com.objectModellingFile.codingame.entities.User;
import com.objectModellingFile.codingame.exceptions.ContestNotFoundException;
import com.objectModellingFile.codingame.exceptions.InvalidOperationException;
import com.objectModellingFile.codingame.exceptions.UserNotFoundException;

public interface IUserService {
    public User create(String name);
    public List<User> getAllUserScoreOrderWise(ScoreOrder scoreOrder);
    public UserRegistrationDto attendContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException;
    public UserRegistrationDto withdrawContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException;
}
