package com.semonemo.spring_server.domain.coin.entity;

import com.semonemo.spring_server.domain.nft.entity.NFTMarket;
import com.semonemo.spring_server.domain.nft.entity.NFTTag;
import com.semonemo.spring_server.domain.user.entity.Users;
import com.semonemo.spring_server.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Set;

@Entity
@Table(name = "nfts")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoinLog extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @Column(name = "price")
    private Long price;

    @Column(name = "type")
    private String type;
}
