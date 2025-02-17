package jpabook.springbootjpa.service.StoreService;


import jpabook.springbootjpa.converter.StoreConverter;
import jpabook.springbootjpa.domain.Mission;
import jpabook.springbootjpa.domain.Review;
import jpabook.springbootjpa.repository.MemberRepository;
import jpabook.springbootjpa.repository.MissionRepository;
import jpabook.springbootjpa.repository.ReviewRepository;
import jpabook.springbootjpa.repository.StoreRepository;
import jpabook.springbootjpa.web.dto.StoreRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
//@Transactional(readOnly=true)
public class StoreCommandServiceImpl implements StoreCommandService{
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Override
    public Review createReview (Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request){
        Review review = StoreConverter.toReview(request);

        review.setMember(memberRepository.findById(memberId).get());
        review.setStore(storeRepository.findById(storeId).get());
        return reviewRepository.save(review);
    }

    @Override
    public Mission createMission(Long storeId, StoreRequestDTO.MissionDTO request){
        Mission mission = StoreConverter.toMission(request);

        mission.setStore(storeRepository.findById(storeId).get());
        return missionRepository.save(mission);

    }
}
