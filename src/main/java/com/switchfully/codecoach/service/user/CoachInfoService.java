package com.switchfully.codecoach.service.user;

import com.switchfully.codecoach.domain.user.CoachInfo;
import com.switchfully.codecoach.domain.user.User;
import com.switchfully.codecoach.repository.CoachInfoRepository;
import com.switchfully.codecoach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
// TODO move it into UserService ask the team
public class CoachInfoService {
    private final CoachInfoRepository coachInfoRepository;
    // TODO replace with UserService when done
    private final UserRepository userRepository;

    @Autowired
    public CoachInfoService(CoachInfoRepository coachInfoRepository, UserRepository userRepository) {
        this.coachInfoRepository = coachInfoRepository;
        this.userRepository = userRepository;
    }

    // TODO return user DTO?
    public void becomeACoach(UUID userId) {
        User user = userRepository.getById(userId);
        CoachInfo coachInfo = new CoachInfo(null, null);
        coachInfoRepository.save(coachInfo);
        user.setIsCoach(true);
        user.setCoachInfo(coachInfo);
        // TODO Update role in Keycloack
    }

}
