package com.objectModellingFile.codingame.services;

import java.util.List;

import com.objectModellingFile.codingame.dtos.ContestSummaryDto;
import com.objectModellingFile.codingame.entities.Contest;
import com.objectModellingFile.codingame.entities.Level;
import com.objectModellingFile.codingame.exceptions.ContestNotFoundException;
import com.objectModellingFile.codingame.exceptions.InvalidContestException;
import com.objectModellingFile.codingame.exceptions.QuestionNotFoundException;
import com.objectModellingFile.codingame.exceptions.UserNotFoundException;

public interface IContestService {
    public Contest create(String contestName, Level level, String contestCreator, Integer numQuestion) throws UserNotFoundException, QuestionNotFoundException;
    public List<Contest> getAllContestLevelWise(Level level);
    public ContestSummaryDto runContest(String contestId, String contestCreator) throws ContestNotFoundException, InvalidContestException;
}
