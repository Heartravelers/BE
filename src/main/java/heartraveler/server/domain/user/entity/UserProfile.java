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

    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @Column(name = "intro")
    private String intro;

    @Enumerated(EnumType.STRING)
    @Column(name = "badge1")
    private Badge badge1;

    @Enumerated(EnumType.STRING)
    @Column(name = "badge2")
    private Badge badge2;

    @Enumerated(EnumType.STRING)
    @Column(name = "badge3")
    private Badge badge3;

    @Column(name = "login_status", nullable = false, columnDefinition = "boolean default 0")
    private boolean loginStatus;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "total_likes")
    private int totalLikes;

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

    public void setBadges(List<Badge> badges) {
        this.badge1 = badges.get(0);
        this.badge2 = badges.get(1);
        this.badge3 = badges.get(2);
    }

}
