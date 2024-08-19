package heartraveler.server.domain.user.entity;

import heartraveler.server.domain.reservation.entity.Reservation;
import heartraveler.server.domain.review.entity.PlaceReview;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "social_id")
    private String socialId;

    @Column(name = "sum_mileage")
    private int sumMileage;

    @Column(name = "name")
    private String name;

    @Column(name = "intro")
    private String intro;

    @Enumerated(EnumType.STRING)
    @Column(name = "badges")
    private Badge badges;

    @Column(name = "login_status", nullable = false, columnDefinition = "boolean default 0")
    private boolean loginStatus;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "userProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MileageEarning> mileageEarnings= new ArrayList<>();

    @OneToMany(mappedBy = "userProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaceReview> placeReviews= new ArrayList<>();

    @OneToMany(mappedBy = "userProfile", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations= new ArrayList<>();

    @OneToMany(mappedBy = "following", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserFollow> following= new ArrayList<>();

    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserFollow> followers= new ArrayList<>();

    @OneToMany(mappedBy = "blocker", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserBlock> blocking= new ArrayList<>();

    @OneToMany(mappedBy = "blocked", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserBlock> blocked= new ArrayList<>();


}
