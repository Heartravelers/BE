package heartraveler.server.domain.user.service;

import heartraveler.server.domain.user.dto.UpdateBadgeDto;
import heartraveler.server.domain.user.entity.UserProfile;
import heartraveler.server.global.common.BaseResponseCode;
import heartraveler.server.global.common.SuccessCode;

public interface ProfileService {

    BaseResponseCode updateName(UserProfile userProfile, String name);

    SuccessCode updateIntro(UserProfile userProfile, String intro);

    SuccessCode updateBadges(UserProfile userProfile, UpdateBadgeDto updateBadgeDto) throws Exception;

}
