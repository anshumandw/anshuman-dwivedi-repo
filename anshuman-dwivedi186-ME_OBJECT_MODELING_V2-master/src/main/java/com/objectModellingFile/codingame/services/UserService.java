package com.objectModellingFile.codingame.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.objectModellingFile.codingame.dtos.UserRegistrationDto;
import com.objectModellingFile.codingame.entities.Contest;
import com.objectModellingFile.codingame.entities.ContestStatus;
import com.objectModellingFile.codingame.entities.RegisterationStatus;
import com.objectModellingFile.codingame.entities.ScoreOrder;
import com.objectModellingFile.codingame.entities.User;
import com.objectModellingFile.codingame.exceptions.ContestNotFoundException;
import com.objectModellingFile.codingame.exceptions.InvalidOperationException;
import com.objectModellingFile.codingame.exceptions.UserNotFoundException;
import com.objectModellingFile.codingame.repositories.IContestRepository;
import com.objectModellingFile.codingame.repositories.IUserRepository;

public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IContestRepository contestRepository;

    public UserService(IUserRepository userRepository, IContestRepository contestRepository) {
        this.userRepository = userRepository;
        this.contestRepository = contestRepository;
    }
    // TODO: objectModellingFile_TASK_MODULE_SERVICES
    // Create and store User into the repository.
    @Override
    public User create(String name) {
        User user = userRepository.save(new User("1", name , 0));
        return user;
    }

    // Get All Users in Ascending Order w.r.t scores if ScoreOrder ASC.
    // Or
    // Get All Users in Descending Order w.r.t scores if ScoreOrder DESC.

    @Override
    public List<User> getAllUserScoreOrderWise(ScoreOrder scoreOrder){
    List<User> allUsers = userRepository.findAll();
     if(ScoreOrder.ASC == scoreOrder) {
        Collections.sort(allUsers, new Comparator<User>() {

            @Override
            public int compare(User arg0, User arg1) {
                return arg0.getScore() - arg1.getScore();
            }
            
        });
     } else {
        Collections.sort(allUsers, new Comparator<User>() {

            @Override
            public int compare(User arg0, User arg1) {
                return arg1.getScore() - arg0.getScore();
            }
            
        });
     }
     return allUsers;
    }

    @Override
    public UserRegistrationDto attendContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new ContestNotFoundException("Cannot Attend Contest. Contest for given id:"+contestId+" not found!"));
        User user = userRepository.findByName(userName).orElseThrow(() -> new UserNotFoundException("Cannot Attend Contest. User for given name:"+ userName+" not found!"));
        if(contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is in progress!");
        }
        if(contest.getContestStatus().equals(ContestStatus.ENDED)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is ended!");
        }
        if(user.checkIfContestExists(contest)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is already registered!");
        }
        user.addContest(contest);
        userRepository.save(user);
        return new UserRegistrationDto(contest.getName(), user.getName(),RegisterationStatus.REGISTERED);
    }

    // Withdraw the user from the contest
    // Hint :- Refer Unit Testcases withdrawContest method

    @Override
    public UserRegistrationDto withdrawContest(String contestId, String userName) throws ContestNotFoundException, UserNotFoundException, InvalidOperationException {
        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new ContestNotFoundException("Cannot Attend Contest. Contest for given id:"+contestId+" not found!"));
        User user = userRepository.findByName(userName).orElseThrow(() -> new UserNotFoundException("Cannot Attend Contest. User for given name:"+ userName+" not found!"));
        if(contest.getContestStatus().equals(ContestStatus.IN_PROGRESS)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is in progress!");
        }
        if(contest.getContestStatus().equals(ContestStatus.ENDED)){
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is ended!");
        }
        if(user.checkIfContestExists(contest)){
            user.deleteContest(contest);
        } else {
            throw new InvalidOperationException("Cannot Attend Contest. Contest for given id:"+contestId+" is already registered!");
        }

        userRepository.save(user);
     
        return new UserRegistrationDto(contest.getName(), user.getName(),RegisterationStatus.NOT_REGISTERED);
    }
    
}
