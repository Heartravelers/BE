package heartraveler.server.domain.user.service;

import heartraveler.server.domain.user.dto.UpdateBadgeDto;
import heartraveler.server.domain.user.entity.Badge;
import heartraveler.server.domain.user.entity.UserProfile;
import heartraveler.server.domain.user.repository.UserRepository;
import heartraveler.server.global.common.BaseResponseCode;
import heartraveler.server.global.common.ErrorCode;
import heartraveler.server.global.common.SuccessCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    UserRepository userRepository;

    @Override
    public BaseResponseCode updateName(UserProfile userProfile, String name) {
        if(name == null) {
            return ErrorCode.BAD_REQUEST;
        }
        Optional<UserProfile> conflict = userRepository.findByName(name);
        if(conflict.isPresent()) {
            return ErrorCode.NAME_CONFLICT;
        }
        userProfile.setName(name);
        userRepository.save(userProfile);
        return SuccessCode.UPDATE_PROFILE_SUCCESS;
    }

    @Override
    public SuccessCode updateIntro(UserProfile userProfile, String intro) {
        userProfile.setIntro(intro);
        userRepository.save(userProfile);
        return SuccessCode.UPDATE_PROFILE_SUCCESS;
    }

    @Override
    public SuccessCode updateBadges(UserProfile userProfile, UpdateBadgeDto updateBadgeDto) throws Exception {
        try {
            List<Badge> badges = new ArrayList<>();
            badges.add(Badge.fromString(updateBadgeDto.getBadge1()));
            badges.add(Badge.fromString(updateBadgeDto.getBadge2()));
            badges.add(Badge.fromString(updateBadgeDto.getBadge3()));
            userProfile.setBadges(badges);
            userRepository.save(userProfile);
            return SuccessCode.UPDATE_PROFILE_SUCCESS;
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
